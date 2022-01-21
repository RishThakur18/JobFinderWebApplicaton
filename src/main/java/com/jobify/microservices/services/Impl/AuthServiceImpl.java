package com.jobify.microservices.services.Impl;

import com.jobify.microservices.entities.dtos.LoginRequestDto;
import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.entities.mappers.UserMapper;
import com.jobify.microservices.entities.models.SignUpInfo;
import com.jobify.microservices.entities.models.User;
import com.jobify.microservices.exceptionHandling.customExceptions.UserAlreadyExistsException;
import com.jobify.microservices.exceptionHandling.customExceptions.UserNotFoundException;
import com.jobify.microservices.exceptionHandling.customExceptions.WrongCredentialsException;
import com.jobify.microservices.repositories.SignupOtpVerificationRepo;
import com.jobify.microservices.repositories.UserRepo;
import com.jobify.microservices.configurations.OtpGeneratorConfig;
import com.jobify.microservices.services.AuthService;
import com.jobify.microservices.security.JwtTokenUtil;
import com.jobify.microservices.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepo userRepo;
    private final SignupOtpVerificationRepo signupOtpVerificationRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final OtpGeneratorConfig otpGenerator;
    private final EmailService emailService;


    @Override
    public Mono<Boolean> sendVerificationEmail(String email) {
        return Mono.just(SignUpInfo
                        .builder()
                        .signupOtp(otpGenerator.generateOtp())
                        .noOfAttempts(1)
                        .email(email)
                        .created(new Date().getTime())
                        .build())
                .doOnNext(signUpInfo -> {
                    log.info("sending verification mail to [{}]",signUpInfo.getEmail());
                    emailService.sendEmail(signUpInfo.getEmail(), "Email Verification", signUpInfo.getSignupOtp());
                    log.info("mail sent successfully to [{}]",signUpInfo.getEmail());
                })
                .flatMap(signupOtpVerificationRepo::save)
                .doOnNext(signUpInfo -> log.info("signing info persisted successfully"))
                .map(s-> true);
    }

    public Mono<Boolean> verifyMail(String email, String otp) {
       return signupOtpVerificationRepo.findSignUpInfoByEmail(email)
               .map(signupInfo -> signupInfo.getSignupOtp().equals(otp));
    }

    @Override
    public Mono<String> login(LoginRequestDto loginRequestDto) {
        return Mono.just(loginRequestDto)
                .doOnNext(loginCredentials -> log.info("incoming login request : {}",loginCredentials))
                .flatMap(this::isValidUser)
                .doOnNext(user -> log.info("login authentication successful, generating token for {}",user))
                .flatMap(jwtTokenUtil::generateToken)
                .doOnNext(token -> log.info("token generated successfully : token {}",token));
    }


    @Override
    public Mono<UserDto> signup(UserDto userDto) {
            final boolean newUser = true;
            return Mono.just(userDto)
                    .doOnNext(user -> log.info("Searching for user in the system having email: {}",userDto.getEmail()))
                    .map(UserDto::getEmail)
                    .flatMap(userRepo::findUserByEmail)
                    .map(user -> !newUser)
                    .switchIfEmpty(Mono.just(newUser))
                    .doOnNext(isNewUser -> log.info("new user : {}",isNewUser))
                    .filter(user -> user.equals(newUser))
                    .switchIfEmpty(Mono.error(new UserAlreadyExistsException("User Already Exists!")))
                    .map(user -> userDto)
                    .doOnNext(user -> log.info("user : {}",user))
                    .map(UserMapper.INSTANCE::dtoToModel)
                    .doOnNext(user -> log.info("user : {}",user))
                    .doOnNext(user -> {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        user.setCreated(new Date().getTime());
                        user.setUpdated(new Date().getTime());
                        log.info("saving user into system : {}",user);
                    })
                    .flatMap(userRepo::save)
                    .doOnNext(user -> log.info("SignUp successful : {}",user))
                    .map(UserMapper.INSTANCE::modelToDto);
        }


    private Mono<User> isValidUser(LoginRequestDto loginRequestDto) {
        return userRepo.findUserByEmail(loginRequestDto.getEmail())
                .switchIfEmpty(Mono.error(new UserNotFoundException("login authentication failed")))
                .filter(savedUser -> passwordEncoder.matches(loginRequestDto.getPassword(),savedUser.getPassword()))
                .switchIfEmpty(Mono.error(new WrongCredentialsException("login authentication failed")));
    }
}

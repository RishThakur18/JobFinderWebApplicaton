package com.jobify.microservices.utilities;

public class StaticConstantsUtil {

    //javax validation messages
    public static final String NOT_BLANK = " can not be blank";
   // public static final String NOT_EMPTY = " can not be empty";
    public static final String NOT_NULL = " can not be null";

    //jwt util properties
    public static final String ISSUER = "rishthakur18@gmail.com";
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 600;
    public static final byte[] JWT_SECRET_KEY = "rishi1234".getBytes();

}

import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
//import axios from 'axios';


function Loginpage() {

    const [user, setUser] = useState({
        userEmail: "",
        userPassword: ""
    });

    const history = useHistory();
    const [isLoggedIn, setLoginStatus] = useState(false);
    const [errorStatus, setErrorStatus] = useState(false);

    function handleChange(event) {
        console.log(event.target);
        const addUser = { ...user };
        addUser[event.target.name] = event.target.value;
        setUser(addUser);
    }

    function handleSubmit(event) {
        event.preventDefault();
        // axios.post("viewAllJobs", {
        //     userRole: user.userRole,
        //     userEmail: user.userEmail,
        //     userPassword: user.userPassword
        // }).then(
        //     (res) => console.log(res.data)
        // );

        if (user.userEmail === "a@a.com" && user.userPassword === "qwerty") {
            localStorage.setItem("key", "rishi");
            setLoginStatus(true);
        }
        else {
            setErrorStatus(true);
        }
    }

    useEffect(() => {
        if (isLoggedIn) {
            return history.push("/homepage");
        }
    });



    return (
        <div className="ui centered cards">
            <div className="ui card">
                <div className="ui segment">
                    <form className="ui form" onSubmit={(e) => handleSubmit(e)} >
                        <div className="field">
                            {errorStatus && <p style={{ color: "red", fontWeight: "bold" }}>Invalid Credentials!Please try again.</p>}
                        </div>
                        <div className="field" >
                            <label>Email</label>
                            <input placeholder="Email" name="userEmail" onChange={(e) => handleChange(e)} />
                        </div>
                        <div className="field" >
                            <label>Password</label>
                            <input placeholder="Password" name="userPassword" onChange={(e) => handleChange(e)} />
                        </div>
                        <button type="submit" className="ui button primary fluid">Login</button>

                    </form>
                    <div className="ui horizontal divider">Or</div>
                    <button type="submit" className="ui button secondry fluid" onClick={() => history.push('/register')}>Sign In</button>
                </div>

            </div>
        </div >

    );

}

export default Loginpage;



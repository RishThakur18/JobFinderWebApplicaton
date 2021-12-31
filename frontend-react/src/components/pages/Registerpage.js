import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
//import axios from 'axios';

function Mainpage() {
    const history = useHistory();
    const [user, setUser] = useState({
        userName: "",
        userEmail: "",
        userPassword: "",
        userRole: "candidate"
    });
    const [errorStatus, setErrorStatus] = useState(false);

    function handleChange(event) {
        const addUser = { ...user };
        addUser[event.target.name] = event.target.value;
        setUser(addUser);
    }

    function checkPassword(event) {
        if (user.userPassword === event.target.value) {
            setErrorStatus(false);
        }
        else
            setErrorStatus(true);
    }

    function handleSubmit(event) {
        event.preventDefault();
        // if (isValidPass) {
        //     axios.post("saveUser", {
        //         userName: user.userName,
        //         userEmail: user.userEmail,
        //         userPassword: user.userPassword,
        //         userRole: user.userRole
        //     }).then((res) => {
        //         console.log(res);
        //     });
        // }
    }



    return (
        <div className="ui centered cards">
            <div className="ui card">
                <div className="ui segment">
                    <form className="ui form" onSubmit={(e) => handleSubmit(e)} >
                        <div className="field">
                            {errorStatus && <p style={{ color: "red", fontWeight: "bold" }}>Invalid Credentials!Please try again.</p>}
                        </div>
                        <div className="field" >
                            <label>UserRole</label>
                            <input placeholder="Email" type="hidden" name="userRole" onChange={(e) => handleChange(e)} required />
                        </div>
                        <div className="field" >
                            <label>Email</label>
                            <input placeholder="Email" name="userEmail" onChange={(e) => handleChange(e)} required />
                        </div>
                        <div className="field" >
                            <label>Password</label>
                            <input placeholder="Password" name="userPassword" onChange={(e) => handleChange(e)} required />
                        </div>
                        <div className="field" >
                            <label>Confirm Password</label>
                            <input placeholder="Confirm Password" name="userPasswordConfirm" onChange={(e) => checkPassword(e)} required />
                        </div>
                        <button type="submit" className="ui button primary fluid">Login</button>

                    </form>
                    <div className="ui horizontal divider">Or</div>
                    <button type="submit" className="ui button secondry fluid" onClick={() => history.push('/')}>Sign In</button>
                </div>

            </div>
        </div >
    );

}

export default Mainpage;

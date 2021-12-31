import React from "react";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
// import JobListPage from "../pages/JobListPage.js";
import Loginpage from "../pages/Loginpage.js";
// import JobPostingPage from "../pages/JobPostingPage.js";
import Registerpage from "../pages/Registerpage";
import Homepage from "../pages/Homepage.js";

function Body() {
    return (
        <Router>
            <Switch>
                <Route path="/" component={Loginpage} exact />
                <Route path="/homepage" component={Homepage} exact />
                <Route path="/register" component={Registerpage} exact />

                {/* 
                    <Route path="/viewJobs" component={JobListPage} exact />
                    <Route path="/vewJobDetails" component={ViewJobPage} exact />
                    <Route path="/recruiter/viewJobApplicants" component={ViewJobApplicantsPage} exact />
                    <Route path="/postJob" component={JobPostingPage} exact /> */}
            </Switch>
        </Router>
    );
}

export default Body;

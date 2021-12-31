import React, { useState } from 'react';
//import { useHistory } from 'react-router-dom';
import axios from 'axios';
import { Form } from 'semantic-ui-react';



function JobPostingPage() {
    //cl const history = useHistory();

    const [logoPreview, setLogoPreview] = useState("");

    const [job, saveJob] = useState({
        companyName: "",
        companyLogo: "",
        jobTitle: "",
        jobDescription: "",
    });

    function handleChangeJobInfo(event) {
        const addJobInfo = { ...job };
        if (event.target.name !== "companyLogo")
            addJobInfo[event.target.name] = event.target.value;
        else {
            addJobInfo[event.target.name] = event.target.files[0];
            const filePreview = URL.createObjectURL(event.target.files[0]);
            setLogoPreview(filePreview);
        }
        saveJob(addJobInfo);
    }
    function handleSubmit(event) {
        event.preventDefault();

        const fd = new FormData();
        fd.append("companyName", job.companyName);
        fd.append("companyLogo", job.companyLogo);
        fd.append("jobTitle", job.jobTitle);
        fd.append("jobDescription", job.jobDescription);
        console.log(fd);
        axios.post("postJob", fd)
            .then((res) => console.log(res)).catch(err => console.log(err));
        // history.push("/recruiter/viewJobApplicants");
    }


    return (
        <form method="post" onSubmit={(event) => handleSubmit(event)}>
            <div className="job_posting_container">
                <h1>Register</h1>
                <p>Please fill in this form to post a Job.</p>
                <hr /> <br />
                <div>

                    <label><b>Company Name</b></label>
                    <input type="text" placeholder="Company Name" name="companyName" onChange={(event) => handleChangeJobInfo(event)} required />

                    <label><b>Job Title</b></label>
                    <input type="text" placeholder="Job Title" name="jobTitle" onChange={(event) => handleChangeJobInfo(event)} required />


                    <label><b>Job Description</b></label>
                    <textarea name="jobDescription" rows="4" cols="50" onChange={(event) => handleChangeJobInfo(event)} />

                    <label><b>Upload Company logo(Add logo URL)</b></label>
                    <input type="file" placeholder="Compant Logo URL" name="companyLogo" accept="image/*" onChange={(event) => handleChangeJobInfo(event)} />
                    {job.companyLogo && <img src={logoPreview} className="company_logo_preview" alt="companyLogo" />}
                </div>
                <Form.TextArea label='About' placeholder='Tell us more about you...' />
                <Form.Checkbox label='I agree to the Terms and Conditions' />
                <Form.Button>Submit</Form.Button>

                <p>By posting a job, you agree to our <button>Terms and Privacy.</button></p>
                <button type="submit" className="job_posting_btn">Register</button>
            </div>
        </form>
    );
};

export default JobPostingPage;
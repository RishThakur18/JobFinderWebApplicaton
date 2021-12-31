import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import axios from 'axios';
import { Button } from 'semantic-ui-react'





function JobListPage() {
  const history = useHistory();
  const [jobsList, getJobsList] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("key");
    //    console.log(token);
    if (token === null) {
      return history.push("/");
    }
    else {
      axios.get("viewAllJobs").then
        ((response) => {
          getJobsList(response.data);
          console.log(response.data);
        });
    }
  }, [history]);

  return (

    <div className="cards-container" id="job-cards-container">
      <Button>Click Heasfre</Button>
      {
        jobsList.map((job, index) => (
          <div className="card" id="job-card">
            <div className="card-title">
              {job.companyName}
            </div>
            <div className="card-body" >
              <div id="card_bg_image">
                <img src={job.companyLogoUrl} alt={job.companyName} id="card-img" />
              </div>
              <div id="job_title">
                {job.jobTitle}
              </div>
              <div id="job_description">
                {job.jobDescription}
              </div>
            </div>
            <button id="job-card-apply-btn" onClick={() => history.push('/viewJobDetails')}> View Full Description </button>
          </div>))
      }

      <div class="ui grid"><div class="column">
        <img src="/images/wireframe/image.png" class="ui image" />
      </div>
        <div class="column">
          <img src="/images/wireframe/image.png" class="ui image" />
        </div><div class="column">
          <img src="/images/wireframe/image.png" class="ui image" />
        </div>
        <div class="column">
          <img src="/images/wireframe/image.png" class="ui image" />
        </div>
        <div class="column">
          <img src="/images/wireframe/image.png" class="ui image" /></div>
        <div class="column"><img src="/images/wireframe/image.png" class="ui image" /></div>
        <div class="column"><img src="/images/wireframe/image.png" class="ui image" /></div>
        <div class="column"><img src="/images/wireframe/image.png" class="ui image" /></div>
        <div class="column"><img src="/images/wireframe/image.png" class="ui image" /></div>
        <div class="column"><img src="/images/wireframe/image.png" class="ui image" /></div>
        <div class="column"><img src="/images/wireframe/image.png" class="ui image" /></div><div class="column">
          <img src="/images/wireframe/image.png" class="ui image" /></div><div class="column">
          <img src="/images/wireframe/image.png" class="ui image" /></div><div class="column">
          <img src="/images/wireframe/image.png" class="ui image" /></div><div class="column">
          <img src="/images/wireframe/image.png" class="ui image" /></div><div class="column">
          <img src="/images/wireframe/image.png" class="ui image" /></div></div>
    </div >

  );
};

export default JobListPage;
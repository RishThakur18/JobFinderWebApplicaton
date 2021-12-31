import React, { useState, useEffect } from 'react';
import { render } from 'react-dom';

import Head from './components/default/Head';
import Body from './components/default/Body';
import Sidebar from './components/default/Sidebar'

import axios from 'axios';

import './index.css';
import 'semantic-ui-css/semantic.min.css'

axios.defaults.baseURL = "http://localhost:8080/";
function App() {

    const [toggle, setToggle] = useState(false);
    const [isDimmed, setIsDimmed] = useState("");


    function toggleSideBar() {
        setToggle(!toggle);
    }

    useEffect(() => {
        if (toggle === true) {
            setIsDimmed("dimmed");
        }
        else {
            setIsDimmed("");
        }
    }, [toggle])

    const classes = "ui pusher bottom " + isDimmed;
    return (
        <div className="ui container fluid">
            <Head toggleSideBarState={toggleSideBar} />
            <div className="ui attached pushable" style={{ height: "100vh" }}>
                <Sidebar isVisibleSidebar={toggle} />
                <div className={classes}>
                    <Body />
                </div>
            </div>
        </div >
    );
}


render(<App />, document.getElementById('root'));


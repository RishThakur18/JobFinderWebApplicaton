import React, { useEffect, useState } from 'react';

function SideBar(props) {
    const [isVisible, setIsVisible] = useState("");
    const classes = "ui inverted vertical labeled icon overlay left thin sidebar menu " + isVisible;

    useEffect(() => {
        toggleSidebar();
    })

    function toggleSidebar() {
        if (props.isVisibleSidebar === true) {
            setIsVisible("visible");
        }
        else {
            setIsVisible("");
        }

    }


    return (
        <div className={classes}>
            <div className="item">
                <div>
                    {/* <i aria-hidden="true" className="home icon" /> */}
                    Home
                </div>
            </div>
            <div className="item">
                <div>
                    {/* <i aria-hidden="true" className="gamepad icon" /> */}
                    Games
                </div>

            </div>
            <div className="item">
                <div>
                    {/* <i aria-hidden="true" className="camera icon" /> */}
                    Channels
                </div>
            </div>
        </div>
    );
}

export default SideBar;

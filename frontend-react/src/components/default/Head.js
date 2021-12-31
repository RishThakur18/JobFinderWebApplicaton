import React from "react";
import { ReactComponent as Logo } from '../../resources/logo.svg';
import logoSvg from '../../resources/logo.svg';

function Head(props) {
    return (
        <div className="ui top inverted  attached menu">
            <div className="item">
                <button onClick={props.toggleSideBarState} className="ui item icon button link grey">
                    <i aria-hidden="true" className="sidebar icon left" />
                </button>
            </div>
            <div className="header" > <img width="200rem" height="200rem" src={logoSvg} /> </div>
            <div className="item header">FreeSHOP</div>
        </div>
    );
}

export default Head;


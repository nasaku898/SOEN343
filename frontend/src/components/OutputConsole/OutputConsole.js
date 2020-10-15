import { Divider } from '@material-ui/core';
import React, { useEffect } from 'react';
import './OutputConsole.css';
import { animateScroll } from "react-scroll";

const OutputConsole = (props) => {

  const scrollToBottom = () => {
    animateScroll.scrollToBottom({
      containerId: "outputConsoleContainer"
    });
  }

  useEffect(() => {
    // Scroll to bottom of output console every time a new item is added
    scrollToBottom()
  });

  return (
    <div id="outputConsoleContainer">
      <h4 className="outputConsoleTitle">Output Console</h4>
      <ul className="outputConsoleList">
        {
          props.outputData.map(item => {
            return <li className="outputConsoleListItem">{`Log[${item.date.getHours()}:${item.date.getMinutes()}]: ${item.data}`}</li>
          })
        }
      </ul>
    </div>
  )
}

export default OutputConsole;
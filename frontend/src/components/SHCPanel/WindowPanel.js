import React from "react";
import FormControl from "@material-ui/core/FormControl";
import { FormLabel } from "@material-ui/core";
import WindowOpenSwitch from "./WindowOpenSwitch";
import WindowBlockedSwitch from "./WindowBlockedSwitch";

const WindowPanel = ({ windows, classes }) => {
  return (
    <>
      <FormControl component="fieldset" className={classes.checkboxFormControl}>
        <FormLabel component="legend">Open</FormLabel>
        {windows.map((window) => {
          return <WindowOpenSwitch window={window} />;
        })}
        <FormLabel component="legend">Blocked</FormLabel>
        {windows.map((window) => {
          return <WindowBlockedSwitch window={window} />;
        })}
      </FormControl>
    </>
  );
};

export default WindowPanel;

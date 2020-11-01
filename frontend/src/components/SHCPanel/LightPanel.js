import React from "react";
import FormControl from "@material-ui/core/FormControl";
import LightSwitch from "./LightSwitch";
import { FormLabel } from "@material-ui/core";

const LightPanel = ({ lights, classes }) => {
  return (
    <>
      <FormControl component="fieldset" className={classes.checkboxFormControl}>
        <FormLabel component="legend">On</FormLabel>
        {lights.map((light) => {
          return <LightSwitch light={light} />;
        })}
      </FormControl>
    </>
  );
};

export default LightPanel;

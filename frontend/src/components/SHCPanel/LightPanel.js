import React from "react";
import FormControl from "@material-ui/core/FormControl";
import LightSwitch from "./LightSwitch";

import { modifyLightState } from "../../modules/HouseOverview/HouseService";
import {
  Checkbox,
  Switch,
  FormGroup,
  FormLabel,
  FormControlLabel,
} from "@material-ui/core";

const LightPanel = ({ lights, handleItemChange, classes }) => {
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

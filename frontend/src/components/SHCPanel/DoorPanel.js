import React from "react";
import FormControl from "@material-ui/core/FormControl";
import { Checkbox, FormGroup, FormLabel } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import OpenDoorSwitch from "./OpenDoorSwitch";
import LockDoorSwitch from "./LockDoorSwitch";

const DoorPanel = ({ doors, handleItemChange, classes }) => {
  console.log(doors);
  return (
    <>
      <FormControl component="fieldset" className={classes.checkboxFormControl}>
        <FormLabel component="legend">Open</FormLabel>

        {doors.map((door) => {
          return <OpenDoorSwitch door={door} />;
        })}
        <FormLabel component="legend">Lock</FormLabel>

        {doors
          .filter((door) => door.locked !== undefined)
          .map((door) => {
            return <LockDoorSwitch door={door} />;
          })}
      </FormControl>
    </>
  );
};

export default DoorPanel;

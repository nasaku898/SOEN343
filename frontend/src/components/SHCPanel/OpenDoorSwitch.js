import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import {
  modifyExteriorDoorState,
  modifyInteriorDoorState,
} from "../../modules/HouseOverview/HouseService";

const OpenDoorSwitch = ({ door }) => {
  const [desiredState, setDesiredState] = useState(false);
  useEffect(() => {
    setDesiredState(door.open);
  }, [door]);

  const lockDoor = async () => {
    if (door.locked === undefined) {
      await modifyInteriorDoorState(door.id, !desiredState);
    } else {
      await modifyExteriorDoorState(door.id, true, !desiredState);
    }
    setDesiredState(!desiredState);
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={
          <Switch
            checked={desiredState}
            m
            onChange={lockDoor}
            name={`${door.id}`}
          />
        }
        label={door.id}
      />
    </FormGroup>
  );
};

export default OpenDoorSwitch;

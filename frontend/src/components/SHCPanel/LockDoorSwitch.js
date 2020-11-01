import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import { modifyExteriorDoorState } from "../../modules/HouseOverview/HouseService";

const LockDoorSwitch = ({ door }) => {
  const [desiredState, setDesiredState] = useState(false);
  useEffect(() => {
    setDesiredState(door.locked);
  }, [door]);

  const lockDoor = async () => {
    await modifyExteriorDoorState(door.id, false, !desiredState);
    setDesiredState(!desiredState);
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={
          <Switch
            key={`lock-door-${door.id}`}
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

export default LockDoorSwitch;

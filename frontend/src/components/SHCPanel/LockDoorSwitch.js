import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import { modifyExteriorDoorState } from "../../modules/HouseOverview/HouseService";
import { useOutputData } from "../../context/OutputData";

const LockDoorSwitch = ({ door }) => {
  const [desiredState, setDesiredState] = useState(false);
  const { setOutputData } = useOutputData();

  useEffect(() => {
    setDesiredState(door.locked);
  }, [door]);

  const lockDoor = async () => {
    try {
      await modifyExteriorDoorState(door.id, false, !desiredState);
      setDesiredState(!desiredState);
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `Door ${door.id} was ${!desiredState === true ? 'locked' : 'unlocked'}`}])
    } catch (error) {
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `There was an error ${!desiredState === true ? 'locking' : 'unlocking'} Door ${door.id}`}])
    }
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

import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import {
  modifyExteriorDoorState,
  modifyInteriorDoorState,
} from "../../modules/HouseOverview/HouseService";
import { useOutputData } from "../../context/OutputData";

const OpenDoorSwitch = ({ door }) => {
  const [desiredState, setDesiredState] = useState(false);
  const { outputData, setOutputData } = useOutputData();

  useEffect(() => {
    setDesiredState(door.open);
  }, [door]);

  const lockDoor = async () => {
    try {
      if (door.locked === undefined) {
        await modifyInteriorDoorState(door.id, !desiredState);
        setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `Door ${door.id} was ${!desiredState === true ? 'opened' : 'closed'}`}])
      } else {
        await modifyExteriorDoorState(door.id, true, !desiredState);
        setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `Door ${door.id} was ${!desiredState === true ? 'opened' : 'closed'}`}])
      }
      setDesiredState(!desiredState);
    } catch (error) {
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `There was an error ${!desiredState === true ? 'opening' : 'closing'} Door ${door.id}`}])
    }
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

import React, { useState, useEffect } from "react";
import { getHouse, modifyLightState } from "../../modules/HouseOverview/HouseService";
import { Switch, FormGroup, FormControlLabel } from "@material-ui/core";
import { useCurrentHouse } from "../../context/CurrentHouse";
import { useOutputData } from "../../context/OutputData";

const LightSwitch = ({ light }) => {
  const [desiredState, setDesiredState] = useState(false);
  const {setHouse} = useCurrentHouse();
  const { setOutputData } = useOutputData();
  
  useEffect(() => {
    setDesiredState(light.lightOn);
  }, [light]);

  const updateLight = async () => {
    try {
      await modifyLightState(light.id, !desiredState);
      setDesiredState(!desiredState);
      getHouse(localStorage.getItem("houseID")).then((data) => {
        setHouse(data)
        setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `Light ${light.id} was turned ${!desiredState === true ? 'on' : 'off'}`}])
      })
    } catch (error) {
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `There was an error ${!desiredState === true ? 'turning on' : 'turning off'} Light ${light.id}`}])
    }
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={
          <Switch
            checked={desiredState}
            onChange={updateLight}
            name={`${light.id}`}
          />
        }
        label={light.id}
      />
    </FormGroup>
  );
};

export default LightSwitch;

import React, { useState, useEffect } from "react";
import { getHouse, modifyLightState } from "../../modules/HouseOverview/HouseService";
import { Switch, FormGroup, FormControlLabel } from "@material-ui/core";
import { useCurrentHouse } from "../../context/CurrentHouse";

const LightSwitch = ({ light }) => {
  const [desiredState, setDesiredState] = useState(false);
  const {setHouse} = useCurrentHouse();

  useEffect(() => {
    setDesiredState(light.lightOn);
  }, [light]);

  const updateLight = async () => {
    await modifyLightState(light.id, !desiredState);
    setDesiredState(!desiredState);
    getHouse(localStorage.getItem("houseID")).then((data) => {
      setHouse(data)
    })
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

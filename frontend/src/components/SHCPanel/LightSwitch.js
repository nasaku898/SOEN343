import React, { useState, useEffect } from "react";
import { modifyLightState } from "../../modules/HouseOverview/HouseService";
import { Switch, FormGroup, FormControlLabel } from "@material-ui/core";

const LightSwitch = ({ light }) => {
  const [desiredState, setDesiredState] = useState(false);

  useEffect(() => {
    setDesiredState(light.lightOn);
  }, [light]);

  const updateLight = async () => {
    await modifyLightState(light.id, !desiredState);
    setDesiredState(!desiredState);
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

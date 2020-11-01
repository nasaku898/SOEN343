import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import { modifyWindowState } from "../../modules/HouseOverview/HouseService";

const WindowOpenSwitch = ({ window }) => {
  const [desiredState, setDesiredState] = useState(false);
  useEffect(() => {
    setDesiredState(window.open);
  }, [window]);

  const openWindow = async () => {
    await modifyWindowState(window.id, true, !desiredState);
    setDesiredState(!desiredState);
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={
          <Switch
            checked={desiredState}
            m
            onChange={openWindow}
            name={`${window.id}`}
          />
        }
        label={window.id}
      />
    </FormGroup>
  );
};

export default WindowOpenSwitch;

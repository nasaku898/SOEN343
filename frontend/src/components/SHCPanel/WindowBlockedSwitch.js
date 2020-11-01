import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import { modifyWindowState } from "../../modules/HouseOverview/HouseService";

const WindowBlockedSwitch = ({ window }) => {
  const [desiredState, setDesiredState] = useState(false);
  useEffect(() => {
    setDesiredState(window.blocked);
  }, [window]);

  const blockWindow = async () => {
    await modifyWindowState(window.id, false, !desiredState);
    setDesiredState(!desiredState);
  };

  return (
    <FormGroup>
      <FormControlLabel
        control={
          <Switch
            key={`lock-window-${window.id}`}
            checked={desiredState}
            m
            onChange={blockWindow}
            name={`${window.id}`}
          />
        }
        label={window.id}
      />
    </FormGroup>
  );
};

export default WindowBlockedSwitch;

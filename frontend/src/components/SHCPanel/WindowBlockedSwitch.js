import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import { modifyWindowState } from "../../modules/HouseOverview/HouseService";
import { useOutputData } from "../../context/OutputData";

const WindowBlockedSwitch = ({ window }) => {
  const [desiredState, setDesiredState] = useState(false);
  const { setOutputData } = useOutputData();
  useEffect(() => {
    setDesiredState(window.blocked);
  }, [window]);

  const blockWindow = async () => {
    try {
      await modifyWindowState(window.id, false, !desiredState);
      setDesiredState(!desiredState);
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `Window ${window.id} was ${!desiredState === true ? 'blocked' : 'unblocked'}`}])
    } catch (error) {
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `There was an error ${!desiredState === true ? 'blocking' : 'unblocking'} Window ${window.id}`}])
    }
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

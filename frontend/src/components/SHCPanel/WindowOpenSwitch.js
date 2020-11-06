import React, { useState, useEffect } from "react";
import { Switch, FormGroup } from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import { modifyWindowState } from "../../modules/HouseOverview/HouseService";
import { useOutputData } from "../../context/OutputData";

const WindowOpenSwitch = ({ window }) => {
  const [desiredState, setDesiredState] = useState(false);
  const { setOutputData } = useOutputData();

  useEffect(() => {
    setDesiredState(window.open);
  }, [window]);

  const openWindow = async () => {
    try {
      await modifyWindowState(window.id, true, !desiredState);
      setDesiredState(!desiredState);
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `Window ${window.id} was ${!desiredState === true ? 'opened' : 'closed'}`}])
    } catch (error) {
      setOutputData(outputData => [...outputData, {id: outputData.length + 1, date: new Date(), data: `There was an error ${!desiredState === true ? 'opening' : 'closing'} Window ${window.id}`}])
    }
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

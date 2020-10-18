import React, { Fragment, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import axios from 'axios';

import sampleData from './sampleDataFormat.json';
import { Checkbox, FormGroup, FormLabel } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  selectFormControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
  checkboxFormControl: {
    margin: theme.spacing(3),
  },
}));

const HOUSE_ID = 1;

const ITEMS = [
  {
    value: "windows",
    title: "Windows"
  },
  {
    value: "lights",
    title: "Lights"
  },
  {
    value: "doors",
    title: "Doors"
  }
]

const SHCPanel = (props) => {
  const classes = useStyles();
  const [itemSelected, setItemSelected] = React.useState('');
  const [data, setData] = React.useState([]);

  useEffect(() => {
    getLightsDoorsWindows();
  }, []);

  const getLightsDoorsWindows = async () => {
    // Get data
    let responseData = [];
    try {
      const response = await axios.get(`http://localhost:8080/api/simulation/house/${HOUSE_ID}/roomState/all`);
      responseData = response.data;
    } catch (error) {
      console.log(`ERROR - to be handled laterrrr:\n ${error}`);
    }

    // Set data from backend to state
    let lightsDoorsWindows = {lights: [], doors: [], houseWindows: []};
    responseData.forEach(dataObject => {
      lightsDoorsWindows.lights = lightsDoorsWindows.lights.concat(dataObject.lights)
      lightsDoorsWindows.doors = lightsDoorsWindows.doors.concat(dataObject.doors);
      lightsDoorsWindows.houseWindows = lightsDoorsWindows.houseWindows.concat(dataObject.houseWindows)
    })

    setData(lightsDoorsWindows);
  }

  const handleItemSelectedChange = (event) => {
    setItemSelected(event.target.value);
  };

  // TODO
  const handleLightChange = (event) => {
    const { checked, name } = event.target;

    //send request to backend to set light with lightID -> name & lightStatus -> status
    console.log(checked, name);

    // following this method, the useEffect will be called which will update all the data :)
  }

  // TODO
  const handleDoorChange = (event, doorEventType) => {
    const { checked, name } = event.target;

    if (doorEventType === "open") {
      // OPEN / CLOSE DOOR
      //send request to backend to set door with doorID -> name & doorStatus -> status
      console.log("open/close", checked, name);
    }
    else if (doorEventType === "lock") {
      // LOCK / UNLOCK DOOR
      //send request to backend to set door with doorID -> name & doorStatus -> status
      console.log("lock/unlock", checked, name);
    }

    // following this method, the useEffect will be called which will update all the data :)
  }

  // TODO
  const handleWindowChange = (event, windowEventType) => {
    const { checked, name } = event.target;

    if (windowEventType === "open") {
      // OPEN / CLOSE WINDOW
      //send request to backend to set door with windowID -> name & windowStatus -> status
      console.log("open/close", checked, name);
    }
    else if (windowEventType === "block") {
      // BLOCK / UNBLOCK WINDOW
      //send request to backend to set door with window -> name & windowStatus -> status
      console.log("block/unblock", checked, name);
    }

    // following this method, the useEffect will be called which will update all the data :)
  }

  return (
    <div>
      <FormControl className={classes.selectFormControl}>
        <Fragment>
          <InputLabel id="demo-simple-select-label">Item</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={itemSelected}
            onChange={handleItemSelectedChange}
          >
            {
              ITEMS.map(item => {
                return <MenuItem key={item.value} value={item.value}>{item.title}</MenuItem>
              })
            }
          </Select>
        </Fragment>
      </FormControl>

      {/* Lights */}
      {
        itemSelected === "lights" &&
        <FormControl component="fieldset" className={classes.checkboxFormControl}>
          <FormLabel component="legend">On (checked) / Off (unchecked)</FormLabel>
          {
            data.lights.map(light => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`on-light-${light.id}`} checked={light.lightOn} onChange={handleLightChange} name={`${light.id}`} />}
                    label={light.id}
                  />
                </FormGroup>
              )
            })
          }
        </FormControl>
      }

      {/* Doors */}
      {
        itemSelected === "doors" &&
        <FormControl component="fieldset" className={classes.checkboxFormControl}>
          <FormLabel component="legend">Open (checked) / Closed (unchecked)</FormLabel>
          {
            data.doors.map(door => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`open-door-${door.id}`} checked={door.open} onChange={(event) => handleDoorChange(event, 'open')} name={`${door.id}`} />}
                    label={door.id}
                  />
                </FormGroup>
              )
            })
          }
          {/* <FormLabel component="legend">Locked (checked) / Unlocked (unchecked)</FormLabel>
          {
            data.doors.map(door => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`lock-door-${door.id}`} checked={door.locked} onChange={(event) => handleDoorChange(event, 'lock')} name={`${door.id}`} />}
                    label={door.id}
                  />
                </FormGroup>
              )
            })
          } */}
        </FormControl>
      }

      {/* Windows */}
      {
        itemSelected === "windows" &&
        <FormControl component="fieldset" className={classes.checkboxFormControl}>
          <FormLabel component="legend">Open (checked) / Closed (unchecked)</FormLabel>
          {
            data.houseWindows.map(window => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`open-window-${window.id}`} checked={window.open} onChange={(event) => handleWindowChange(event, 'open')} name={`${window.id}`} />}
                    label={window.id}
                  />
                </FormGroup>
              )
            })
          }
          <FormLabel component="legend">Blocked (checked) / Unblocked (unchecked)</FormLabel>
          {
            data.houseWindows.map(window => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`block-window-${window.id}`} checked={window.blocked} onChange={(event) => handleWindowChange(event, 'block')} name={`${window.id}`} />}
                    label={window.id}
                  />
                </FormGroup>
              )
            })
          }
        </FormControl>
      }
    </div>
  )
}

export default SHCPanel;
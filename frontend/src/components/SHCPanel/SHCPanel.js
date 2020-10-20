import React, { Fragment, useEffect } from 'react';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import { Checkbox, FormGroup, FormLabel } from '@material-ui/core';
import useStyles from './SHCPanelStyle';
import { fetchLightsDoorsAndWindows } from '../../modules/SHCPanel/LightsDoorsAndWindows';
import { setLightDoorOrWindow } from '../../modules/SHCPanel/setLightDoorOrWindow';
import { HOUSE_ID, ITEMS } from './SHCPanelConstants';

const SHCPanel = (props) => {
  const classes = useStyles();
  const [itemSelected, setItemSelected] = React.useState('');
  const [data, setData] = React.useState({});

  useEffect(() => {
    handleUpdateLightDoorsAndWindows();
  }, []);

  const handleUpdateLightDoorsAndWindows = async () => {
    fetchLightsDoorsAndWindows(HOUSE_ID).then(response => {
      setData(response);
    });
  }

  const handleItemSelectedChange = (event) => {
    setItemSelected(event.target.value);
  };

  const handleItemChange = async (event, itemType) => {
    await setLightDoorOrWindow(event, itemType);
    handleUpdateLightDoorsAndWindows();
  }

  return (
    <div>
      <FormControl className={classes.selectFormControl}>
        <Fragment>
          <InputLabel id="item-simple-select-label">Item</InputLabel>
          <Select
            labelId="item-simple-select-label"
            id="item-simple-select"
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
          <FormLabel component="legend">On</FormLabel>
          {
            data.lights.map(light => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`on-light-${light.id}`} checked={light.lightOn} onChange={(event) => handleItemChange(event, 'light')} name={`${light.id}`} />}
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
          <FormLabel component="legend">Open</FormLabel>
          {
            data.doors.map(door => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`open-door-${door.id}`} checked={door.open} onChange={(event) => handleItemChange(event, 'exteriorDoorOpen')} name={`${door.id}`} />}
                    label={door.id}
                  />
                </FormGroup>
              )
            })
          }
          <FormLabel component="legend">Locked</FormLabel>
          {
            data.doors.map(door => {
              if (door.locked != null) {
                return (
                  <FormGroup>
                    <FormControlLabel
                      control={<Checkbox key={`lock-door-${door.id}`} checked={door.locked} onChange={(event) => handleItemChange(event, 'exteriorDoorLock')} name={`${door.id}`} />}
                      label={door.id}
                    />
                  </FormGroup>
                )
              }
              return <></>
            })
          }
        </FormControl>
      }

      {/* Windows */}
      {
        itemSelected === "windows" &&
        <FormControl component="fieldset" className={classes.checkboxFormControl}>
          <FormLabel component="legend">Open</FormLabel>
          {
            data.houseWindows.map(window => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`open-window-${window.id}`} checked={window.open} onChange={(event) => handleItemChange(event, 'windowOpen')} name={`${window.id}`} />}
                    label={window.id}
                  />
                </FormGroup>
              )
            })
          }
          <FormLabel component="legend">Blocked</FormLabel>
          {
            data.houseWindows.map(window => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox key={`block-window-${window.id}`} checked={window.blocked} onChange={(event) => handleItemChange(event, 'windowBlock')} name={`${window.id}`} />}
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
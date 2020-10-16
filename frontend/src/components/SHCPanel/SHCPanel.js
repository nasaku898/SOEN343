import React, { Fragment, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

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
    // TODO - Backend call to get all windows, lights and doors
    // store this data in the data state variable
    // assume there will always be an attribute for Windows, Lights, Doors
    setData(sampleData);
  });

  const handleChange = (event) => {
    setItemSelected(event.target.value);
  };

  return (
    <div>
      <FormControl className={classes.selectFormControl}>
        <Fragment>
          <InputLabel id="demo-simple-select-label">Item</InputLabel>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={itemSelected}
            onChange={handleChange}
          >
            {
              ITEMS.map(item => {
                return <MenuItem value={item.value}>{item.title}</MenuItem>
              })
            }
          </Select>
        </Fragment>
      </FormControl>

      {
        itemSelected === "lights" &&
        <FormControl component="fieldset" className={classes.checkboxFormControl}>
          <FormLabel component="legend">Open/Close</FormLabel>
          {
            data.lights.map(light => {
              return (
                <FormGroup>
                  <FormControlLabel
                    control={<Checkbox checked={light.lightOn} onChange={() => null} name={light.id} />}
                    label={light.id}
                  />
                </FormGroup>
              )
            })
          }

        </FormControl>
      }



      {/* <FormControl component="fieldset" className={classes.formControl}>
        <FormLabel component="legend">Assign responsibility</FormLabel>
        <FormGroup>
          <FormControlLabel
            control={<Checkbox checked={gilad} onChange={handleChange} name="gilad" />}
            label="Gilad Gray"
          />
          <FormControlLabel
            control={<Checkbox checked={jason} onChange={handleChange} name="jason" />}
            label="Jason Killian"
          />
          <FormControlLabel
            control={<Checkbox checked={antoine} onChange={handleChange} name="antoine" />}
            label="Antoine Llorca"
          />
        </FormGroup>
        <FormHelperText>Be careful</FormHelperText>
      </FormControl> */}
    </div>
  )
}

export default SHCPanel;
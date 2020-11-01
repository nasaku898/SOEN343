import React, { useState } from "react";
import { Select, FormControl, InputLabel, MenuItem } from "@material-ui/core";

const LocationSelector = ({ rooms, handleChange, name = "location" }) => {
  return (
    <div>
      <InputLabel>Location</InputLabel>
      <FormControl>
        <Select
          labelId="demo-customized-select-label"
          id="demo-customized-select"
          name={name}
          onChange={handleChange}
        >
          {rooms.map((room, index) => (
            <MenuItem key={index} value={room}>
              {room.name}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  );
};

export default LocationSelector;

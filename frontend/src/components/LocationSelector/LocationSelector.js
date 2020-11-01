import React, { useState } from "react";
import { Select, FormControl, InputLabel, MenuItem } from "@material-ui/core";

const LocationSelector = ({ rooms, name = "location", handleChange }) => {
  console.log(rooms);
  return (
    <div>
      <InputLabel>Location</InputLabel>
      <FormControl>
        <Select
          labelId="demo-customized-select-label"
          id="demo-customized-select"
          defaultValue={rooms[0].name}
          onChange={handleChange}
          name={name}
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

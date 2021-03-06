import React from "react";
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";

export const LocationChooser = ({
  name = "location",
  disabled,
  locationName,
  rooms = [],
}) => {
  return (
    <div>
      <InputLabel>Location:</InputLabel>
      <FormControl>
        <select name={name} disabled={disabled} defaultValue={locationName}>
          {rooms.map((room, index) => (
            <option key={index} value={room.name}>
              {room.name}
            </option>
          ))}
          ;
        </select>
      </FormControl>
    </div>
  );
};

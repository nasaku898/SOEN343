import React from "react";
import { FormControl, InputLabel, MenuItem, Select } from "@material-ui/core";

const RoleSelector = ({ role, name, handleChange }) => {
  return (
    <div>
      <FormControl>
        <InputLabel>Role</InputLabel>
        <Select
          labelId="demo-customized-select-label"
          id="demo-customized-select"
          value={role}
          name={name}
          onChange={handleChange}
        >
          <MenuItem value={"PARENT"}>Parent</MenuItem>
          <MenuItem value={"CHILD"}>Child</MenuItem>
          <MenuItem value={"GUEST"}>Guest</MenuItem>
        </Select>
      </FormControl>
    </div>
  );
};

export default RoleSelector;

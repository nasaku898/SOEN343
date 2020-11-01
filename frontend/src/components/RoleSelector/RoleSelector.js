import React from "react";
import { FormControl, InputLabel, MenuItem, Select } from "@material-ui/core";

const RoleSelector = ({ role, name, handleChange }) => {
  return (
    <div>
      <InputLabel>Role</InputLabel>
      <Select
        labelId="demo-customized-select-label"
        id="demo-customized-select"
        defaultValue="PARENT"
        onChange={handleChange}
        name="role"
      >
        <MenuItem value={"PARENT"}>Parent</MenuItem>
        <MenuItem value={"CHILD"}>Child</MenuItem>
        <MenuItem value={"GUEST"}>Guest</MenuItem>
      </Select>
    </div>
  );
};

export default RoleSelector;

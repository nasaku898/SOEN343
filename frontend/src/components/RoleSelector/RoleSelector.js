import React from 'react'
import { Select, FormControl, InputLabel, MenuItem } from '@material-ui/core'
const RoleSelector = ({ role, setRole }) => {

    const handleChange = (event) => {
        setRole(event.target.value);
    };

    return (
        <div>
            <FormControl>
                <InputLabel>Role</InputLabel>
                <Select
                    labelId="demo-customized-select-label"
                    id="demo-customized-select"
                    value={role}
                    onChange={handleChange}
                >
                    <MenuItem value={"PARENT"}>Parent</MenuItem>
                    <MenuItem value={"CHILD"}>Child</MenuItem>
                    <MenuItem value={"GUEST"}>Guest</MenuItem>
                </Select>
            </FormControl>
        </div>
    )
}

export default RoleSelector

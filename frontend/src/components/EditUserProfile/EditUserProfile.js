import React from 'react'

const EditUserProfile = () => {
    return (
        <div>
            <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}>

                <MenuItem >
                    <form>
                        <TextField id="standard-basic" label="Name" />
                    </form>
                </MenuItem>
                <MenuItem>
                    <FormControl>
                        <InputLabel id="demo-customized-select-label">Role</InputLabel>
                        <Select
                            labelId="demo-customized-select-label"
                            id="demo-customized-select"
                            value={role}
                            onChange={handleChange}
                        >
                            <MenuItem value={"Parent"}>Parent</MenuItem>
                            <MenuItem value={"Child"}>Child</MenuItem>
                            <MenuItem value={"Guest"}>Guest</MenuItem>
                        </Select>
                    </FormControl>
                </MenuItem>
                <MenuItem>
                    <form>
                        <TextField id="standard-basic" label="Location" />
                    </form>
                </MenuItem>
            </Menu>
        </div>
    )
}

export default EditUserProfile

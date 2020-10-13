import { Box, Grid, Menu, Typography, MenuItem, Button, TextField, FormControl, InputLabel, Select } from '@material-ui/core'
import React, { useState } from 'react'
import useStyles from './UserProfileStyle'

const UserProfile = ({ userProfile, editMode }) => {

    const classes = useStyles()

    const [anchorEl, setAnchorEl] = useState(null);
    const [role, setRole] = useState('')
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleChange = (event) => {
        setRole(event.target.value);
    };


    //Add handle name, role, and location changes

    return (
        <div>
            <Grid container direction="row" spacing={0} className={classes.userProfileWrapper}>
                <Grid item xs={3} >
                    <Typography>Name: {userProfile.name}</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>Role: {userProfile.role}</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>Location: {userProfile.location}</Typography>
                </Grid>
                <Grid item xs={3}>
                    {
                        editMode ?
                            <div>
                                <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                                    Edit
                            </Button>
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
                            :
                            <Box></Box>
                    }
                </Grid>

            </Grid>
        </div>
    )
}

export default UserProfile
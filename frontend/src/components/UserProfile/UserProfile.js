import { Box, Grid, Menu, Typography, MenuItem, Button, TextField, FormControl, InputLabel, Select } from '@material-ui/core'
import React, { useState } from 'react'
import useStyles from './UserProfileStyle'
import UpdateIcon from '@material-ui/icons/Update';
import Axios from 'axios';
import "../../Utils/config";
const UserProfile = ({ userProfile, editMode }) => {

    const classes = useStyles()


    const [role, setRole] = useState(userProfile.role)
    const [name, setName] = useState(userProfile.name)
    const [room, setRoom] = useState(userProfile.roomName)
    const [roomId, setRoomId] = useState(userProfile.roomId)

    const [nameField, setNameField] = useState(userProfile.name)
    const [roleField, setRoleField] = useState(userProfile.role)


    const userProfileId = userProfile.id


    const handleNameModification = (event) => {
        event.preventDefault()
        Axios.put(global.config.BACKEND_URL + `/api/simulation/houseMember/nameModification/${userProfileId}?newName=${nameField}`)
            .then((response) => {
                setName(response.data.name)
            }).catch((response) => {
                //Might change to a more specific message
                alert("Unexpected error has occured")
            })
    }

    const handleRoleModification = (event) => {
        event.preventDefault()
        Axios.put(global.config.BACKEND_URL + `/api/simulation/houseMember/roleModification/${userProfileId}?newRole=${roleField}`)
            .then((response) => {
                setRole(response.data.role);
            }).catch((response) => {
                //Might change to a more specific message
                alert("Unexpected error has occured")
            })
    }

    const handleNameTyping = (event) => {
        setNameField(event.target.value)
    }

    //Function for handling menu
    const [anchorEl, setAnchorEl] = useState(null);

    const handleEditButtonClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleCloseEdit = () => {
        setAnchorEl(null);
    };

    const handleRoleSelectChange = (event) => {
        setRoleField(event.target.value);
    };
    //Add handle name, role, and location changes

    return (
        <div>
            <Grid container direction="row" spacing={0} className={classes.userProfileWrapper}>
                <Grid item xs={3} >
                    <Typography>Name: {name}</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>Role: {role}</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>Location: {room}</Typography>
                </Grid>
                <Grid item xs={3}>
                    {
                        editMode ?
                            <div>
                                <Button aria-controls="simple-menu" aria-haspopup="true" onClick={handleEditButtonClick}>
                                    Edit
                                </Button>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={anchorEl}
                                    keepMounted
                                    open={Boolean(anchorEl)}
                                    onClose={handleCloseEdit}>

                                    <MenuItem >
                                        <TextField id="standard-basic" label="Name" value={nameField} onChange={handleNameTyping} autoComplete="off" />
                                        <Button onClick={handleNameModification}>
                                            <UpdateIcon></UpdateIcon>
                                        </Button>

                                    </MenuItem>

                                    <MenuItem>
                                        <FormControl>
                                            <InputLabel id="demo-customized-select-label">Role</InputLabel>

                                            <Select
                                                labelId="demo-customized-select-label"
                                                id="demo-customized-select"
                                                value={roleField}
                                                onChange={handleRoleSelectChange}
                                            >
                                                <MenuItem value={"PARENT"}>Parent</MenuItem>
                                                <MenuItem value={"CHILD"}>Child</MenuItem>
                                                <MenuItem value={"GUEST"}>Guest</MenuItem>
                                            </Select>

                                            <Button onClick={handleRoleModification}>
                                                <UpdateIcon></UpdateIcon>
                                            </Button>
                                        </FormControl>
                                    </MenuItem>

                                    <MenuItem>
                                        <form>
                                            <TextField id="standard-basic" label="Location" />
                                            <Button type="submit">
                                                <UpdateIcon></UpdateIcon>
                                            </Button>
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
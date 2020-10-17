import { Box, Grid, Menu, Typography, MenuItem, Button, TextField } from '@material-ui/core'
import React, { useState } from 'react'
import useStyles from './UserProfileStyle'
import UpdateIcon from '@material-ui/icons/Update';
import Axios from 'axios';
import "../../Utils/config";
import RoleSelector from '../RoleSelector/RoleSelector';
import LocationSelector from '../LocationSelector/LocationSelector';
const UserProfile = ({ userProfile, editMode, rooms }) => {

    const classes = useStyles()


    const [role, setRole] = useState(userProfile.role)
    const [name, setName] = useState(userProfile.name)
    const [room, setRoom] = useState(userProfile.roomName)
    const [roomId, setRoomId] = useState(userProfile.roomId)

    const userProfileId = userProfile.id

    //Temporary state when typing or selecting a role
    const [nameField, setNameField] = useState(userProfile.name)
    const [roleField, setRoleField] = useState(userProfile.role)

    const handleNameModification = (event) => {
        event.preventDefault()
        Axios.put(global.config.BACKEND_URL + `/api/simulation/houseMember/nameModification/${userProfileId}?newName=${nameField}`)
            .then((response) => {
                setName(response.data.name)
            }).catch(() => {
                alert("Unexpected error has occured")
            })
    }

    const handleRoleModification = (event) => {
        event.preventDefault()
        Axios.put(global.config.BACKEND_URL + `/api/simulation/houseMember/roleModification/${userProfileId}?newRole=${roleField}`)
            .then((response) => {
                setRole(response.data.role);
            }).catch(() => {
                alert("Unexpected error has occured")
            })
    }

    const handleLocationChange = (event) => {
        event.preventDefault()
        Axios.put(global.config.BACKEND_URL + `/api/simulation/room/newRoom/${roomId}?name=${name}`)
            .then((response) => {
                setName(response.data.name)
                setRole(response.data.role)
                setRoom(response.data.roomName)
                setRoomId(response.data.roomId)
            }).catch(() => {
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
                                        <TextField label="Name" value={nameField} onChange={handleNameTyping} autoComplete="off" />
                                        <Button onClick={handleNameModification}>
                                            <UpdateIcon></UpdateIcon>
                                        </Button>

                                    </MenuItem>

                                    <MenuItem>
                                        <RoleSelector role={roleField} setRole={setRoleField}></RoleSelector>
                                        <Button onClick={handleRoleModification}>
                                            <UpdateIcon></UpdateIcon>
                                        </Button>
                                    </MenuItem>

                                    <MenuItem>
                                        <LocationSelector rooms={rooms} setRoomId={setRoomId}></LocationSelector>
                                        <Button onClick={handleLocationChange}>
                                            <UpdateIcon></UpdateIcon>
                                        </Button>
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
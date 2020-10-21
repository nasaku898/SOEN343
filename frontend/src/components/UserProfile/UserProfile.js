import { Box, Grid, Menu, Typography, MenuItem, Button, TextField } from '@material-ui/core'
import React, { useState } from 'react'
import useStyles from './UserProfileStyle'
import UpdateIcon from '@material-ui/icons/Update';
import "../../Utils/config";
import RoleSelector from '../RoleSelector/RoleSelector';
import LocationSelector from '../LocationSelector/LocationSelector';
import { houseMemberNameModification, houseMemberRoleModification, houseMemberLocationChange } from '../../modules/UserProfileList/HouseMemberAPI';
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

        houseMemberNameModification(userProfileId, nameField).then(response => {
            setName(response.name)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })
    }

    const handleRoleModification = (event) => {
        event.preventDefault()
        houseMemberRoleModification(userProfileId, roleField).then(response => {
            setRole(response.role)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })
    }

    const handleLocationChange = (event) => {
        event.preventDefault()
        houseMemberLocationChange(roomId, name).then(response => {
            setName(response.name)
            setRole(response.role)
            setRoom(response.roomName)
            setRoomId(response.roomId)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
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
        <div className={classes.container}>
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
                                        <LocationSelector currentRoom={roomId} rooms={rooms} setRoomId={setRoomId}></LocationSelector>
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
import {Box, Button, Grid, Menu, MenuItem, TextField, Typography} from '@material-ui/core'
import React, {useState} from 'react'
import useStyles from './UserProfileStyle'
import UpdateIcon from '@material-ui/icons/Update';
import "../../Utils/config";
import RoleSelector from '../RoleSelector/RoleSelector';
import LocationSelector from '../LocationSelector/LocationSelector';
import {updateHouseMember} from '../../modules/UserProfileList/HouseMemberService';
import {moveHouseMemberToRoom} from "../../modules/HouseOverview/SimulationService";

const UserProfile = ({userProfile, editMode, rooms}) => {

    const classes = useStyles()
    const [role, setRole] = useState(userProfile.role)
    const [name, setName] = useState(userProfile.name)
    const [room, setRoom] = useState(userProfile.location)
    const [profile, setProfile] = useState({userProfile})
    const userProfileId = userProfile.id

    //Temporary state when typing or selecting a role
    const [nameField, setNameField] = useState(userProfile.name)

    // this will send the PUT request to update the object, and will update our view with whichever fields have been modified
    const handleUpdate = (event) => {
        event.preventDefault()
        (async () => {
            const response = await updateHouseMember(userProfile);
            setProfile({
                ...profile,
                ...response
            })
        })
    }


    const handleLocationChange = (event) => {
        event.preventDefault()
        moveHouseMemberToRoom(profile.username, room.id).then(response => {
            setProfile({
                ...profile,
                ...response
            })
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })
    }
    const handleChange = (event) => {
            setProfile({
                ...profile,
                [event.target.name]: event.target.value,
            })
        }
    ;


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
                <Grid item xs={3}>
                    <Typography>Name: {profile.username}</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>Role: {profile.role}</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography>
                        Location:
                        {profile.isOutside
                            ? "outside"
                            : profile.location}
                    </Typography>
                </Grid>
                <Grid item xs={3}>
                    {
                        editMode ?
                            <div>
                                <Button aria-controls="simple-menu" aria-haspopup="true"
                                        onClick={handleEditButtonClick}>
                                    Edit
                                </Button>
                                <Menu
                                    id="simple-menu"
                                    anchorEl={anchorEl}
                                    keepMounted
                                    open={Boolean(anchorEl)}
                                    onClose={handleCloseEdit}>

                                    <MenuItem>
                                        <TextField label="Name" value={profile.username} onChange={handleChange}
                                                   autoComplete="off"/>
                                        <Button onClick={handleUpdate}>
                                            <UpdateIcon/>
                                        </Button>

                                    </MenuItem>

                                    <MenuItem>
                                        <RoleSelector role={profile.role} setRole={handleChange}/>
                                        <Button onClick={handleUpdate}>
                                            <UpdateIcon/>
                                        </Button>
                                    </MenuItem>

                                    <MenuItem>
                                        <LocationSelector currentRoom={profile.location} rooms={rooms}
                                                          setRoomId={handleChange}/>
                                        <Button onClick={handleLocationChange}>
                                            <UpdateIcon/>
                                        </Button>
                                    </MenuItem>

                                </Menu>
                            </div>
                            :
                            <Box/>
                    }
                </Grid>

            </Grid>
        </div>
    )
}

export default UserProfile

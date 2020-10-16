import { Box, Button, Modal, Typography, TextField, Select, FormControl, InputLabel, MenuItem } from '@material-ui/core'
import React, { useState, useEffect } from 'react'
import UserProfile from '../UserProfile/UserProfile'
import useStyles from './UserProfileListStyle'
import AddIcon from '@material-ui/icons/Add';
import Axios from 'axios';
import "../../Utils/config";
const UserProfileList = () => {
    const [editMode, SetEditMode] = useState(true)

    const classes = useStyles()
    const [userProfileList, setUserProfileList] = useState([])
    const [name, setName] = useState("")
    const [role, setRole] = useState(undefined)
    const [roomId, setRoom] = useState(undefined)

    useEffect(() => {
        console.log("hi")
        const fetchHouseMember = () => {
            Axios.get(global.config.BACKEND_URL + `/api/simulation/houseMember/all`)
                .then((response) => {
                    console.log(response.data)
                    setUserProfileList(response.data)
                })
        }
        fetchHouseMember()
    }, [])




    const handleNameTyping = (event) => {
        setName(event.target.value)
    }
    const createNewHouseMember = () => {
        Axios.post(global.config.BACKEND_URL + `/api/simulation/houseMember`, { name, role, roomId })
            .then((response) => {
                setUserProfileList(userProfileList.push(response.data))
            }).catch(
                alert("Unexpected error")
            )
    }
    
    // to do: implement fetching all rooms from house 
    //Modal Handling
    const handleEditButton = () => {
        SetEditMode(!editMode)
    }
    const [openModal, setOpenModal] = useState(false);

    const handleOpenModal = () => {
        setOpenModal(true);
    };

    const handleCloseModal = () => {
        setOpenModal(false);
    };

    const handleChange = (event) => {
        setRole(event.target.value);
    };

    const createUser = (
        <div className={classes.modal}>
            <form>
                <TextField id="standard-basic" label="Name" value={name} onChange={handleNameTyping} />
                <br></br>
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
                <br></br>
                <TextField id="standard-basic" label="Location" />
                <Button>Create</Button>
            </form>
        </div>
    );

    return (
        <div>
            <Typography>User Profiles</Typography>
            <div className={classes.userProfileListWrapper}>
                {
                    userProfileList.slice().map(userProfile => <UserProfile key={userProfile.id} userProfile={userProfile} editMode={editMode} />)
                }
            </div>

            {
                editMode ?
                    <div>
                        <Button onClick={handleOpenModal}>
                            <AddIcon></AddIcon>
                        </Button>
                        <Modal
                            open={openModal}
                            onClose={handleCloseModal}
                            aria-labelledby="simple-modal-title"
                            aria-describedby="simple-modal-description"
                            style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                            {createUser}
                        </Modal>
                    </div>

                    : <Box></Box>
            }
            <Button variant="contained" className={classes.editButton} onClick={handleEditButton}>Edit User Profiles</Button>
        </div>
    )
}

export default UserProfileList
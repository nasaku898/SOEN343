import { Box, Button, Modal, Typography, TextField, MenuItem } from '@material-ui/core'
import React, { useState, useEffect } from 'react'
import UserProfile from '../UserProfile/UserProfile'
import useStyles from './UserProfileListStyle'
import AddIcon from '@material-ui/icons/Add';
import Axios from 'axios';
import "../../Utils/config";
import RoleSelector from '../RoleSelector/RoleSelector';
import LocationSelector from '../LocationSelector/LocationSelector';
const UserProfileList = () => {
    const [editMode, SetEditMode] = useState(true)
    const [refresh, setRefresh] = useState(true)
    const classes = useStyles()

    const [userProfileList, setUserProfileList] = useState([])
    const [name, setName] = useState("")
    const [role, setRole] = useState("")
    const [roomId, setRoomId] = useState(null)
    const [rooms, setRooms] = useState([])

    useEffect(() => {
        const fetchHouseMember = () => {
            Axios.get(global.config.BACKEND_URL + `/api/simulation/houseMember/all`)
                .then((response) => {
                    setUserProfileList(response.data)
                })
        }

        const fetchRooms = () => {
            Axios.get(global.config.BACKEND_URL + `/api/simulation/room/all`)
                .then((response) => {
                    setRooms(response.data)
                })
        }

        fetchHouseMember()
        fetchRooms()
    }, [refresh])

    const handleNameTyping = (event) => {
        setName(event.target.value)
    }

    const createNewHouseMember = () => {
        Axios.post(global.config.BACKEND_URL + `/api/simulation/houseMember`, { name, role, roomId })
            .then(() => {
                setRefresh(!refresh)
                setName("")
                setRole("")
                setRoomId(null)
                handleCloseModal()
            }).catch(
                (response) => {
                    console.log(response)
                    alert("Unexpected error")
                }
            )
    }

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

    const createUser = (
        <div className={classes.modal}>
            <MenuItem>
                <TextField label="Name" value={name} onChange={handleNameTyping} />
            </MenuItem>

            <br></br>
            <MenuItem>
                <RoleSelector role={role} setRole={setRole}></RoleSelector>
            </MenuItem>

            <br></br>
            <MenuItem>
                <LocationSelector rooms={rooms} setRoomId={setRoomId}></LocationSelector>
            </MenuItem>

            <Button onClick={createNewHouseMember}>Create</Button>
        </div>
    );

    return (
        <div>
            <Typography>User Profiles</Typography>
            <div className={classes.userProfileListWrapper}>
                {
                    userProfileList.slice().map(userProfile => <UserProfile key={userProfile.id} userProfile={userProfile} editMode={editMode} rooms={rooms} />)
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
import { Box, Button, Modal, Typography, TextField, MenuItem } from '@material-ui/core'
import React, { useState, useEffect } from 'react'
import UserProfile from '../UserProfile/UserProfile'
import useStyles from './UserProfileListStyle'
import AddIcon from '@material-ui/icons/Add';
import "../../Utils/config";
import RoleSelector from '../RoleSelector/RoleSelector';
import LocationSelector from '../LocationSelector/LocationSelector';
import { fetchHouseMember, fetchRooms, createNewHouseMember } from '../../modules/UserProfileList/UserProfileAPI';

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

        fetchHouseMember().then(response => {
            setUserProfileList(response)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })

        fetchRooms().then(response => {
            setRooms(response)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })

    }, [refresh])

    const handleNameTyping = (event) => {
        setName(event.target.value)
    }

    const handleCreateNewHouseMember = () => {

        if (!name || !role || !roomId) {
            alert("Cannot leave field empty")
            return
        }

        createNewHouseMember(name, role, roomId)
            .then(() => {
                setRefresh(!refresh)
                setName("")
                setRole("")
                setRoomId(null)
                handleCloseModal()
            }).catch(error => {
                alert(`Status: ${error.status}: ${error.message}`)
            })
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
                <LocationSelector currentRoom={2} rooms={rooms} setRoomId={setRoomId}></LocationSelector>
            </MenuItem>
            <Button onClick={handleCreateNewHouseMember}>Create</Button>
        </div>
    );

    return (
        <div className={classes.container}>
            <Typography>User Profiles</Typography>
            <div className={classes.userProfileListWrapper}>
                {
                    userProfileList.slice().map(userProfile => <UserProfile key={userProfile.id} userProfile={userProfile} editMode={editMode} rooms={rooms} />)
                }
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

        </div>
    )
}

export default UserProfileList
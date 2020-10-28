import {Box, Button, MenuItem, Modal, TextField, Typography} from '@material-ui/core'
import React, {useEffect, useState} from 'react'
import UserProfile from '../UserProfile/UserProfile'
import useStyles from './UserProfileListStyle'
import AddIcon from '@material-ui/icons/Add';
import "../../Utils/config";
import RoleSelector from '../RoleSelector/RoleSelector';
import LocationSelector from '../LocationSelector/LocationSelector';
import {createNewHouseMember, findAllHouseMembers} from '../../modules/UserProfileList/HouseMemberService';
import {getHouse} from "../../modules/HouseOverview/HouseService";

const UserProfileList = () => {
    const [editMode, SetEditMode] = useState(true)
    const [refresh, setRefresh] = useState(true)
    const classes = useStyles()

    const [userProfileList, setUserProfileList] = useState([])
    const [name, setName] = useState("")
    const [role, setRole] = useState("")
    const [rooms, setRooms] = useState([])
    const [houseMember, setHouseMember] = useState({
        username: "",
        location: "",
        role: "",
        isOutside: true
    });

    useEffect(() => {
        findAllHouseMembers().then(response => {
            setUserProfileList(response)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })

        getHouse(1).then(response => {
            setRooms(response)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })

    }, [refresh])


    const handleChange = (event) => {
        setHouseMember({
            ...houseMember,
            [event.target.name]: event.target.value,
        });
    };


    const handleCreateNewHouseMember = () => {

        if (!name || !role) {
            alert("Cannot leave field empty")
            return
        }
        if (houseMember.location) {
            setHouseMember({
                ...houseMember,
                isOutside: false
            })
        }
        createNewHouseMember(houseMember)
            .then(() => {
                setRefresh(!refresh)
                setHouseMember(
                    {
                        name: "",
                        role: "",
                        location: {},
                        isOutside: true
                    })
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
                <TextField label="Name" value={houseMember.username} onChange={handleChange}/>
            </MenuItem>

            <br/>
            <MenuItem>
                <RoleSelector role={houseMember.role} setRole={handleChange}/>
            </MenuItem>

            <br/>
            <MenuItem>
                <LocationSelector rooms={rooms} setRoomId={handleChange}/>
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
                                <AddIcon/>
                            </Button>
                            <Modal
                                open={openModal}
                                onClose={handleCloseModal}
                                aria-labelledby="simple-modal-title"
                                aria-describedby="simple-modal-description"
                                style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                                {createUser}
                            </Modal>
                        </div>

                        : <Box/>
                }
                <Button variant="contained" className={classes.editButton} onClick={handleEditButton}>Edit User Profiles</Button>
            </div>

        </div>
    )
}

export default UserProfileList

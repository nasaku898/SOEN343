import { Box, Button, Modal, Typography, TextField, Select, FormControl, InputLabel, MenuItem } from '@material-ui/core'
import React, { useState } from 'react'
import UserProfile from '../UserProfile/UserProfile'
import useStyles from './UserProfileListStyle'
import AddIcon from '@material-ui/icons/Add';
const UserProfileList = () => {
    const [editMode, SetEditMode] = useState(true)

    const classes = useStyles()

    const [userProfileList] = useState([
        {
            id: 1,
            name: "Simon",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 2,
            name: "David",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 3,
            name: "Bob",
            role: "Child",
            location: "Kitchen"
        },
        {
            id: 1,
            name: "Simon",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 2,
            name: "David",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 3,
            name: "Bob",
            role: "Child",
            location: "Kitchen"
        },
        {
            id: 1,
            name: "Simon",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 2,
            name: "David",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 3,
            name: "Bob",
            role: "Child",
            location: "Kitchen"
        },
        {
            id: 1,
            name: "Simon",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 2,
            name: "David",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 3,
            name: "Bob",
            role: "Child",
            location: "Kitchen"
        },
        {
            id: 1,
            name: "Simon",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 2,
            name: "David",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 3,
            name: "Bob",
            role: "Child",
            location: "Kitchen"
        },
        {
            id: 1,
            name: "Simon",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 2,
            name: "David",
            role: "Parent",
            location: "Kitchen"
        },
        {
            id: 3,
            name: "Bob",
            role: "Child",
            location: "Kitchen"
        }
    ])

    const handleEditButton = () => {
        SetEditMode(!editMode)
    }
    const [open, setOpen] = React.useState(false);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const [role, setRole] = useState('')

    const handleChange = (event) => {
        setRole(event.target.value);
    };

    const createUser = (
        <div className={classes.modal}>
            <form>
                <TextField id="standard-basic" label="Name" />
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
                        <Button onClick={handleOpen}>
                            <AddIcon></AddIcon>
                        </Button>
                        <Modal
                            open={open}
                            onClose={handleClose}
                            aria-labelledby="simple-modal-title"
                            aria-describedby="simple-modal-description"
                            style={{display:'flex',alignItems:'center',justifyContent:'center'}}>
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
import {
  Box,
  Button,
  MenuItem,
  Modal,
  TextField,
  Typography,
} from "@material-ui/core";
import React, { useEffect, useState } from "react";
import UserProfile from "../UserProfile/UserProfile";
import useStyles from "./UserProfileListStyle";
import AddIcon from "@material-ui/icons/Add";
import "../../Utils/config";
import RoleSelector from "../RoleSelector/RoleSelector";
import LocationSelector from "../LocationSelector/LocationSelector";
import { useCurrentHouse } from "../../context/CurrentHouse";
import {
  createNewHouseMember,
  findAllHouseMembers,
} from "../../modules/UserProfileList/HouseMemberService";

const UserProfileList = () => {
  const [editMode, SetEditMode] = useState(true);
  const [refresh, setRefresh] = useState(true);
  const classes = useStyles();
  const [house ] = useCurrentHouse();
  const [userProfileList, setUserProfileList] = useState([]);
  const [rooms, setRooms] = useState([]);

  const [houseMember, setHouseMember] = useState({
    username: "",
    location: {},
    role: "",
    isOutside: true,
  });

  useEffect(() => {
    if (house) {
      findAllHouseMembers(house.id)
        .then((response) => {
          setUserProfileList(response);
        })
        .catch((error) => {
          alert(`Status: ${error.status}: ${error.message}`);
        });
    }
  }, [house, refresh]);

  useEffect(() => {
    if (house) {
      setRooms(house.rooms);
    }
  }, [house]);

  const handleChange = (event) => {
    event.preventDefault();
    setHouseMember({
      ...houseMember,
      [event.target.name]: event.target.value,
    });
  };

  const handleCreateNewHouseMember = () => {
    if (!houseMember.username || !houseMember.role) {
      alert("Cannot leave field empty");
      return;
    }
    if (houseMember.location) {
      setHouseMember({
        ...houseMember,
        isOutside: false,
      });
    }
    houseMember.houseIds = [house.id];
    createNewHouseMember(houseMember)
      .then(() => {
        setRefresh(!refresh);
        setHouseMember({
          username: "",
          role: "",
          location: null,
          isOutside: true,
        });
        handleCloseModal();
      })
      .catch((error) => {
        alert(`Status: ${error.status}: ${error.message}`);
      });
  };

  //Modal Handling
  const handleEditButton = () => {
    SetEditMode(!editMode);
  };

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
        <TextField
          label="Name"
          name="username"
          value={houseMember.username}
          onChange={handleChange}
        />
      </MenuItem>

      <br />
      <MenuItem>
        <RoleSelector role={houseMember.role} handleChange={handleChange} />
      </MenuItem>

      <br />
      <MenuItem>
        <LocationSelector
          name="location"
          rooms={rooms}
          handleChange={handleChange}
        />
      </MenuItem>
      <Button onClick={handleCreateNewHouseMember}>Create</Button>
    </div>
  );

  return (
    <div className={classes.container}>
      <Typography>User Profiles</Typography>
      <div className={classes.userProfileListWrapper}>
        {userProfileList.slice().map((userProfile) => (
          <UserProfile
            key={userProfile.id}
            userProfile={userProfile}
            editMode={editMode}
            rooms={rooms}
          />
        ))}
        {editMode ? (
          <div>
            <Button onClick={handleOpenModal}>
              <AddIcon />
            </Button>
            <Modal
              open={openModal}
              onClose={handleCloseModal}
              aria-labelledby="simple-modal-title"
              aria-describedby="simple-modal-description"
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
            >
              {createUser}
            </Modal>
          </div>
        ) : (
          <Box />
        )}
        <Button
          variant="contained"
          className={classes.editButton}
          onClick={handleEditButton}
        >
          Edit User Profiles
        </Button>
      </div>
    </div>
  );
};

export default UserProfileList;

import {
  Box,
  Button,
  Grid,
  Menu,
  MenuItem,
  TextField,
  Typography,
} from "@material-ui/core";
import React, { useState, useEffect } from "react";
import useStyles from "./UserProfileStyle";
import UpdateIcon from "@material-ui/icons/Update";
import "../../Utils/config";
import RoleSelector from "../RoleSelector/RoleSelector";
import LocationSelector from "../LocationSelector/LocationSelector";
import { updateHouseMember } from "../../modules/UserProfileList/HouseMemberService";
import { moveHouseMemberToRoom } from "../../modules/HouseOverview/SimulationService";

const DefaultProfile = {
  username: "",
  location: null,
  role: "",
  isOutside: false,
};

const UserProfile = ({ userProfile = DefaultProfile, editMode, rooms }) => {
  const [profile, setProfile] = useState(userProfile);
  const classes = useStyles();

  console.log(rooms);
  // this will send the PUT request to update the object, and will update our view with whichever fields have been modified
  const handleUpdate = (event) => {
    event.preventDefault();

    (async () => {
      console.log(profile);
      const response = await updateHouseMember(profile);
      setProfile({
        profile,
        ...response,
      });
    })();
  };

  const handleLocationChange = (event) => {
    event.preventDefault();
    moveHouseMemberToRoom(profile.username, ~~profile.location.roomId)
      .then((response) => {
        setProfile({
          profile,
          ...response,
        });
        console.log(response);
      })
      .catch((error) => {
        alert(`Status: ${error.status}: ${error.message}`);
      });
  };

  const handleChange = (event) => {
    event.preventDefault();
    console.log(event.target.value);

    setProfile({
      ...profile,
      [event.target.name]: event.target.value,
    });
  };

  //Function for handling menu
  const [anchorEl, setAnchorEl] = useState(null);

  const handleEditButtonClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleCloseEdit = () => {
    setAnchorEl(null);
  };

  const [currentLocation, setCurrentLocation] = useState("");

  useEffect(() => {
    if (profile.location || profile.isOutside) {
      setCurrentLocation(profile.isOutside ? "outside" : profile.location.name);
    }
  }, [profile.location]);

  return (
    <div className={classes.container}>
      <Grid
        container
        direction="row"
        spacing={0}
        className={classes.userProfileWrapper}
      >
        <Grid item xs={3}>
          <Typography>Name: {profile.username}</Typography>
        </Grid>
        <Grid item xs={3}>
          <Typography>Role: {profile.role}</Typography>
        </Grid>
        <Grid item xs={3}>
          <Typography>Location: {currentLocation}</Typography>
        </Grid>
        <Grid item xs={3}>
          {editMode ? (
            <div>
              <Button
                aria-controls="simple-menu"
                aria-haspopup="true"
                onClick={handleEditButtonClick}
              >
                Edit
              </Button>
              <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleCloseEdit}
              >
                <MenuItem>
                  <TextField
                    label="Name"
                    value={profile.username}
                    name="username"
                    onChange={handleChange}
                    autoComplete="off"
                  />
                  <Button onClick={handleUpdate}>
                    <UpdateIcon />
                  </Button>
                </MenuItem>

                <MenuItem>
                  <RoleSelector
                    role={profile.role}
                    handleChange={handleChange}
                  />
                  <Button onClick={handleUpdate}>
                    <UpdateIcon />
                  </Button>
                </MenuItem>

                <MenuItem>
                  {profile.location && (
                    <LocationSelector
                      currentRoom={profile.location.name}
                      handleChange={handleChange}
                      rooms={rooms}
                    />
                  )}
                  <Button onClick={handleLocationChange}>
                    <UpdateIcon />
                  </Button>
                </MenuItem>
              </Menu>
            </div>
          ) : (
            <Box />
          )}
        </Grid>
      </Grid>
    </div>
  );
};

export default UserProfile;

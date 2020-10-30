import React, { useState, useEffect } from "react";
import VisibilityIcon from "@material-ui/icons/Visibility";
import VisibilityOffIcon from "@material-ui/icons/VisibilityOff";
import {
  Avatar,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  ListSubheader,
  Typography,
} from "@material-ui/core";
import MeetingRoomIcon from "@material-ui/icons/MeetingRoom";
import NoMeetingRoomIcon from "@material-ui/icons/NoMeetingRoom";
import LockIcon from "@material-ui/icons/Lock";
import LockOpenIcon from "@material-ui/icons/LockOpen";
import BorderAllIcon from "@material-ui/icons/BorderAll";
import BorderBottomIcon from "@material-ui/icons/BorderBottom";
import useStyles from "./DraggableRoomStyle";

const DraggableRoom = ({ room }) => {
  console.log(room);
  console.log("DOISJHIOFDSIFH");
  const [name] = useState(room.name);
  const [temperature] = useState(room.temperature);
  const [doors] = useState(room.doors);
  const [lights] = useState(room.lights);
  const [houseWindows] = useState(room.houseWindows);

  const [lightOn, setLightOn] = useState(false);
  const [doorOpen, setDoorOpen] = useState(false);
  const [windowOpen, setWindowOpen] = useState(false);
  const [windowBlocked, setBlocked] = useState(false);

  const classes = useStyles();

  useEffect(() => {
    console.log(room);

    const checkIfLightOn = () => {
      lights.forEach((light) => {
        if (light.lightOn) {
          setLightOn(true);
        }
      });
    };

    const checkIfDoorOpen = () => {
      doors.forEach((door) => {
        if (door.open) {
          setDoorOpen(true);
        }
      });
    };

    const checkIfWindowOpen = () => {
      houseWindows.forEach((houseWindow) => {
        if (houseWindow.open) {
          setWindowOpen(true);
        }
      });
    };

    const checkIfWindowBlocked = () => {
      houseWindows.forEach((houseWindow) => {
        if (houseWindow.blocked) {
          setBlocked(true);
        }
      });
    };

    checkIfLightOn();
    checkIfDoorOpen();
    checkIfWindowBlocked();
    checkIfWindowOpen();
  }, [doors, houseWindows, lights]);

  return (
    <div>
      <List
        className={classes.root}
        subheader={
          <ListSubheader>
            <Typography>{`Room: ${name}`}</Typography>
            <Typography>{`Temperature: ${temperature} `} &#176;</Typography>
          </ListSubheader>
        }
      >
        <ListItem>
          <ListItemAvatar>
            {lightOn ? (
              <Avatar>
                <VisibilityIcon></VisibilityIcon>
              </Avatar>
            ) : (
              <Avatar>
                <VisibilityOffIcon></VisibilityOffIcon>
              </Avatar>
            )}
          </ListItemAvatar>
          {lights.map((light) => (
            <ListItemText
              key={light.id}
              primary={`Light ID: ${light.id}`}
              secondary={`On: ${Boolean(light.lightOn).toString()}`}
            ></ListItemText>
          ))}
        </ListItem>

        <ListItem>
          <ListItemAvatar>
            {doorOpen ? (
              <Avatar>
                <MeetingRoomIcon></MeetingRoomIcon>
              </Avatar>
            ) : (
              <Avatar>
                <NoMeetingRoomIcon></NoMeetingRoomIcon>
              </Avatar>
            )}
          </ListItemAvatar>
          {doors.map((door) => (
            <ListItemText
              key={door.id}
              primary={`Door ID: ${door.id}`}
              secondary={`Open: ${Boolean(door.open).toString()} ${
                door.locked ? "Locked:" + Boolean(door.locked).toString() : ""
              }`}
            ></ListItemText>
          ))}
        </ListItem>

        <ListItem>
          <ListItemAvatar>
            {windowOpen ? (
              <Avatar>
                <BorderBottomIcon></BorderBottomIcon>
                {windowBlocked ? (
                  <LockIcon></LockIcon>
                ) : (
                  <LockOpenIcon></LockOpenIcon>
                )}
              </Avatar>
            ) : (
              <Avatar>
                <BorderAllIcon></BorderAllIcon>
                {windowBlocked ? (
                  <LockIcon></LockIcon>
                ) : (
                  <LockOpenIcon></LockOpenIcon>
                )}
              </Avatar>
            )}
          </ListItemAvatar>
          {houseWindows.map((houseWindow) => (
            <ListItemText
              key={houseWindow.id}
              primary={`WindowID: ${houseWindow.id}`}
              secondary={`Open: ${Boolean(
                houseWindow.open
              ).toString()} Blocked: ${Boolean(
                houseWindow.blocked
              ).toString()}`}
            ></ListItemText>
          ))}
        </ListItem>
      </List>
    </div>
  );
};
export default DraggableRoom;

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
  const [name] = useState(room.name);
  const [temperature] = useState(room.temperature);
  const [doors] = useState(room.doors);
  const [lights] = useState(room.lights);
  const [windows] = useState(room.windows);

  const [lightOn, setLightOn] = useState(false);
  const [doorOpen, setDoorOpen] = useState(false);
  const [windowOpen, setWindowOpen] = useState(false);
  const [windowBlocked, setBlocked] = useState(false);

  const classes = useStyles();

  useEffect(() => {
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
      windows.forEach((window) => {
        if (window.open) {
          setWindowOpen(true);
        }
      });
    };

    const checkIfWindowBlocked = () => {
      windows.forEach((window) => {
        if (window.blocked) {
          setBlocked(true);
        }
      });
    };

    checkIfLightOn();
    checkIfDoorOpen();
    checkIfWindowBlocked();
    checkIfWindowOpen();
  }, [doors, windows, lights]);

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
          {windows.map((window) => (
            <ListItemText
              key={window.id}
              primary={`WindowID: ${window.id}`}
              secondary={`Open: ${Boolean(
                window.open
              ).toString()} Blocked: ${Boolean(window.blocked).toString()}`}
            ></ListItemText>
          ))}
        </ListItem>
      </List>
    </div>
  );
};
export default DraggableRoom;

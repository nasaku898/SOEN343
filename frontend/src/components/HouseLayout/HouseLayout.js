import React, { useEffect, useState } from "react";
import DraggableRoom from "../DraggableRoom/DraggableRoom";
import useStyles from "./HouseLayoutStyle";
import AddIcon from "@material-ui/icons/Add";
import { findAllRooms } from "../../modules/HouseOverview/SimulationService";

const Draggable = require("react-draggable");

const HouseLayout = (id) => {
  const [rooms, setRooms] = useState([]);
  const [refresh, setRefresh] = useState(false);
  const classes = useStyles();

  useEffect(() => {
    findAllRooms(id).then((response) => {
      setRooms(response);
    });
  }, [refresh]);

  return (
    <div className={classes.layoutWrapper}>
      {rooms.map((room) => (
        <Draggable key={room.id}>
          <div>
            <DraggableRoom room={room} />
          </div>
        </Draggable>
      ))}
    </div>
  );
};

export default HouseLayout;

import React, { useEffect, useState } from "react";
import DraggableRoom from "../DraggableRoom/DraggableRoom";
import useStyles from "./HouseLayoutStyle";
import { findAllRooms } from "../../modules/HouseOverview/SimulationService";
import { useCurrentHouse } from "../../context/CurrentHouse";

const Draggable = require("react-draggable");

const HouseLayout = () => {
  const { house } = useCurrentHouse();
  const [rooms, setRooms] = useState([]);
  const classes = useStyles();

  useEffect(() => {
    if (house) {
      setRooms(house.rooms);
    }
  }, [house]);

  if (rooms) {
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
  }

  return <></>;
};

export default HouseLayout;

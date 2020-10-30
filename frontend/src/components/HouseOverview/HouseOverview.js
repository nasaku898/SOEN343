import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@material-ui/core";
import React, { useEffect, useState } from "react";
import Paper from "@material-ui/core/Paper";
import RoomInfo from "../RoomInfo/RoomInfo";
import useStyles from "./HouseOverviewStyle";
import { findAllRooms } from "../../modules/HouseOverview/SimulationService";
import { useCurrentHouse } from "../../context/CurrentHouse";

const HouseOverview = () => {
  const { houseId } = useCurrentHouse();
  const { setHouseId } = useCurrentHouse();
  const [rows, setRows] = useState([]);
  const classes = useStyles();
  useEffect(() => {
    findAllRooms(houseId).then((response) => {
      setRows(response);
    });
  }, [houseId]);

  return (
    <div>
      <TableContainer component={Paper} className={classes.tableWrapper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Room</TableCell>
              <TableCell align="right">Temperature</TableCell>
              <TableCell align="right">Lights</TableCell>
              <TableCell align="right">Doors</TableCell>
              <TableCell align="right">Windows</TableCell>
            </TableRow>
          </TableHead>

          <TableBody>
            {rows.map((row) => (
              <RoomInfo key={row.name} row={row}></RoomInfo>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default HouseOverview;

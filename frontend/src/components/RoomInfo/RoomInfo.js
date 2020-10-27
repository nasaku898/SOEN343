import React, {useState} from 'react'
import {Button, Modal, TableCell, TableRow, TextField, Typography} from '@material-ui/core'
import UpdateIcon from '@material-ui/icons/Update';
import EditIcon from '@material-ui/icons/Edit';
import useStyles from '../HouseOverview/HouseOverviewStyle'
import {editRoomTemperature} from "../../modules/HouseOverview/RoomService";

const RoomInfo = ({row}) => {
    const [modalTyping, setModalTyping] = useState(row.temperature)
    const [temperature, setTemperature] = useState(row.temperature)

    const roomId = row.id

    const classes = useStyles()

    const handleTyping = (event) => {
        setModalTyping(event.target.value)
    }

    const handleUpdateTemperature = (event) => {
        event.preventDefault()
        editRoomTemperature(roomId, modalTyping).then(response => {
            setTemperature(response.temperature)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`)
        })
    }

    //Modal Section
    const editTemperatureModal = (
        <div className={classes.modal}>
            <TextField
                label="Temperature"
                type="number"
                value={modalTyping}
                InputLabelProps={{
                    shrink: true,
                }}
                onChange={handleTyping}
                variant="filled"
            />
            <Button onClick={handleUpdateTemperature}>
                <UpdateIcon></UpdateIcon>
            </Button>
        </div>
    )

    const [openModal, setOpenModal] = useState(false);

    const handleOpenModal = () => {
        setOpenModal(true);
    };

    const handleCloseModal = () => {
        setOpenModal(false);
    };

    return (
        <>
            <TableRow scope="row">
                <TableCell>{row.name}</TableCell>
                <TableCell align="right">
                    {temperature}
                    <Button onClick={handleOpenModal}>
                        <EditIcon></EditIcon>
                    </Button>
                    <Modal
                        open={openModal}
                        onClose={handleCloseModal}
                        aria-labelledby="simple-modal-title"
                        aria-describedby="simple-modal-description"
                        style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}

                    >
                        {editTemperatureModal}
                    </Modal>
                </TableCell>
                <TableCell align="right">
                    {
                        row.lights.map(light =>
                            <Typography key={light.id}>{`Light ID: ${light.id}, LightOn: ${Boolean(light.lightOn).toString()}`}</Typography>
                        )
                    }

                </TableCell>
                <TableCell align="right">
                    {
                        row.doors.map(door =>
                            <Typography key={door.id}>{`Door ID: ${door.id}, Open: ${Boolean(door.open).toString()}`}</Typography>
                        )
                    }
                </TableCell>
                <TableCell align="right">
                    {
                        row.houseWindows.map(houseWindow =>
                            <Typography key={houseWindow.id}>{`Window ID: ${houseWindow.id}, Blocked: ${Boolean(houseWindow.blocked).toString()}, Open: ${Boolean(houseWindow.open).toString()}`}</Typography>
                        )
                    }
                </TableCell>
            </TableRow>
        </>
    )
}

export default RoomInfo

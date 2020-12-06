import { Button, Modal, TextField, Typography } from '@material-ui/core'
import React, { useState } from 'react'
import {addRoomToZone, setZoneTemperature as setZoneTemperatureAPI} from '../../modules/SHH/SHHService' 
const Zone = (props) => {

    const [zone] = useState(props.zone)
    const [zoneTemperature, setZoneTemperature] = useState(props.zone.temperature)
    const [roomIdForModal, setRoomIdForModal] = useState(-1)

    const handleChangeTemperature = () => {
        setZoneTemperatureAPI(zone.id, zoneTemperature).then(
            data =>{
                setZoneTemperature(data)
            }
        ).catch(error =>{
            console.log(error)
        })
    }

    const handleAddRoom = ()=>{
        addRoomToZone(zone.id, roomIdForModal)
    }

    const handleTyping = (event)=>{
        event.preventDefault()
        setZoneTemperature(event.target.value)
    }

    const handleTypingForRoomId = (event)=>{
        event.preventDefault()
        setRoomIdForModal(event.target.value)
    }

    const addRoomModal = (
        <div>
            <TextField
                id="standard-number"
                label="Number"
                type="number"
                InputLabelProps={{
                    shrink: true,
                }}
                value={roomIdForModal}
                onChange={handleTypingForRoomId}
            >
            </TextField>
            <Button onClick={handleAddRoom}>Add</Button>
        </div>
    )

    const [openModal, setOpenModal] = useState(false);
    
    const handleOpenModal = () => {
        setOpenModal(true);
    };

    const handleCloseModal = () => {
        setOpenModal(false);
    }

    return (
        <div>
            <Typography>{`Zone ${zone.id}`}</Typography>
            <Typography>{`Zone temperature: ${zoneTemperature}`}</Typography>
            <Typography>{`Zone State: ${zone.zoneState}`}</Typography>
            <TextField
                id="standard-number"
                label="Number"
                type="number"
                InputLabelProps={{
                    shrink: true,
                }}
                value = {zoneTemperature}
                onChange={handleTyping}
            >
            </TextField>
            <Button onClick={handleOpenModal}>Add Room</Button>
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
              {addRoomModal}
            </Modal>
            <Button onClick={handleChangeTemperature}>Change</Button>
        </div>
    )
}

export default Zone

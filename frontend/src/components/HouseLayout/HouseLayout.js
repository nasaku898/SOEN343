import React, { useEffect, useState } from 'react'
import { fetchHouseState, loadHouseLayout } from '../../modules/HouseOverview/HouseOverviewAPI';
import DraggableRoom from '../DraggableRoom/DraggableRoom';
import useStyles from './HouseLayoutStyle'
import AddIcon from '@material-ui/icons/Add';
import { Box, Input, Typography } from '@material-ui/core';


let Draggable = require('react-draggable');

const HouseLayout = () => {
    const [rooms, setRooms] = useState([])
    const [refresh, setRefresh] = useState(false)
    const classes = useStyles();

    useEffect(() => {
        fetchHouseState().then(response => {
            setRooms(response)
        })
    }, [refresh])

    const handleHouseLayoutUpload = (event) => {
        const file = event.target.files[0]
        const reader = new FileReader()

        reader.onload = (event) => {
            loadHouseLayout(JSON.parse(event.target.result)).then(reponse => {
                setRefresh(!refresh)
            }).catch(error => {
                alert(`Status: ${error.status}: ${error.message}`)
            })
        }

        reader.readAsText(file);
    }

    return (
        <div className={classes.layoutWrapper}>
            <div className={classes.uploadWrapper}>
                <form>
                    <label htmlFor="file" className={classes.uploadBTN}>
                        <Box className={classes.uploadBTN} >
                            <Typography>
                                <AddIcon></AddIcon>
                            </Typography>
                        </Box>
                    </label>
                </form>

            </div>

            <Input className={classes.Input} type="file" id="file" name="file" inputProps={{ accept: ".json" }} onChange={handleHouseLayoutUpload}></Input>

            {
                rooms.map((room) => (
                    <Draggable key={room.id}>
                        <div>
                            <DraggableRoom room={room}></DraggableRoom>
                        </div>
                    </Draggable>
                ))
            }
        </div>
    )
}

export default HouseLayout

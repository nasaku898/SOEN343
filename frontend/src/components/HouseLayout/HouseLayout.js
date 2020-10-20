import React, { useEffect, useState } from 'react'
import { fetchHouseState } from '../../modules/HouseOverview/HouseOverviewAPI';
import DraggableRoom from '../DraggableRoom/DraggableRoom';
import useStyles from './HouseLayoutStyle'

let Draggable = require('react-draggable');

const HouseLayout = () => {
    const [rooms, setRooms] = useState([])

    const classes = useStyles();

    useEffect(() => {
        fetchHouseState().then(response => {
            setRooms(response)
            console.log(response)
        })
    }, [])

    return (
        <div className={classes.layoutWrapper}>
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

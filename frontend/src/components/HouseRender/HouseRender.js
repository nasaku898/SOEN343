import React, {useEffect, useState} from 'react'
import {Grid, Typography} from '@material-ui/core'
import {Brightness2, Brightness4, Brightness5, Lock, LockOpen} from '@material-ui/icons'
import {fetchHouseState} from '../../modules/HouseOverview/LoadSimulationService'
import {Button} from '../landing/form_components/Button'

const HouseRender = () => {

    const [rooms, setRooms] = useState([])
    const [outsideTemp, setOutsideTemp] = useState(25)
        
    useEffect(() => {
        fetchHouseState().then(response => {
            setRooms(response)
        })
        /*fetchOutsideTemp().then(response => {
            setOutsideTemp(response)
        })*/
    }, [])

    const tempColor = (roomTemp) => {
        if (roomTemp > outsideTemp) {
            return "red";
        }
        else if (roomTemp == outsideTemp) {
            return "green";
        }
        else {
            return "blue";
        }
    }

    const lightsOn = (roomLights) => {
        let allOn = true
        let allOff = true
        roomLights.foreach(light => {
            if (Boolean(light.isLightOn)) {
                allOff = false
            }
            else {
                allOn = false
            }
        })
        if (allOn) {
            return (
                <Brightness5/>
            );
        }
        else if (allOff) {
            return (
                <Brightness2/>
            );
        }
        else {
            return (
                <Brightness4/>
            );
        }
    }

    const roomSecure = (roomDoors, roomWindows) => {
        let allSecure = true
        roomDoors.foreach(door => {
            if (!Boolean(door.locked)) {
                allSecure = false
            }
        })
        roomWindows.foreach(window => {
            if (Boolean(window.open)) {
                allSecure = false
            }
        })
        if (allSecure) {
            return (
                <Lock/>
            );
        }
        else {
            return (
                <LockOpen/>
            );
        }
    }

    return (
        <div>
            <Grid>
                {
                    rooms.map(room => (
                        <Grid>
                            <Button variant="contained" color={tempColor(room.temperature)} /*onClick={handleClick(room)}*/>
                                <Typography>
                                    {`${room.name}\nTemp: ${room.temperature}&#8451;\n${lightsOn(room.lights)}${roomSecure(room.doors, room.houseWindows)}`}
                                </Typography>
                            </Button>
                        </Grid>
                        )
                    )
                }
            </Grid>
        </div>
    );
}    

export default HouseRender
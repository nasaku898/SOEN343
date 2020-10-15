import { Button, Input, List, ListItem, Modal, TextField, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import useStyles from './SimulationParametersStyle'
const SimulationParameters = () => {
    const [currentUser,setCurrentUser] = useState("Simon")
    const [date, setDate] = useState(new Date())
    const [currentTime, setCurrentTime] = useState(date.toLocaleTimeString())
    const [location, setLocation] = useState("Kitchen")
    const [outsideTemperature, setOutsideTemperature] = useState(20)

    const [open, setOpen] = useState(false);

    const classes = useStyles()
    /*
    const [currentUser,setCurrentUser] = useState(null)
    const [date, setDate] = useState(new Date())
    const [currentTime, setCurrentTime] = useState(date.toLocaleTimeString())
    const [location, setLocation] = useState(null)
    const [outsideTemperature, setOutsideTemperature] = useState(null)
*/

    useEffect(() => {
        setInterval(updateTime, 1000)
    }, [currentTime])

    const updateTime = ()=>{
        const newDate = new Date()
        setCurrentTime(newDate.toLocaleTimeString())
    }
    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const editSimulationParameter = (
        <div className={classes.modal}>
            <TextField id="standard-basic" label="User"></TextField>
            <br/>
            <Input type="date"></Input>
            <br/>
            <Input type="time"></Input>
            <br/>
            {
                //Add location
            }
            <TextField type="number" id="standard-basic" label="Outside Temperature"></TextField>
        </div>
    )

    return (
        <div>
            <List>
                <ListItem>
                    <Typography>
                        Name: {currentUser}
                    </Typography>
                </ListItem>
                <ListItem>
                    <Typography>
                        Date: {date.toDateString()}
                    </Typography>
                </ListItem>
                <ListItem>
                    <Typography>
                        Time: {currentTime}
                    </Typography>
                </ListItem>
                <ListItem>
                    <Typography>
                        Location: {location}
                    </Typography>
                </ListItem>
                <ListItem>
                    <Typography>
                        Outside Temperature: {outsideTemperature} &#176;
                    </Typography>
                </ListItem>
            </List>
            <Button variant="contained" onClick={handleOpen}>Edit System Parameters</Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
                style={{display:'flex',alignItems:'center',justifyContent:'center'}}
            >
            {editSimulationParameter}

            </Modal>
            
        </div>
    )
}

export default SimulationParameters

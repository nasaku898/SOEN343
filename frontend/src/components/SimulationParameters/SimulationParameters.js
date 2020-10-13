import { List, ListItem, TextField } from '@material-ui/core'
import React, { useEffect, useState } from 'react'

const SimulationParameters = () => {
    const [currentUser,setCurrentUser] = useState("Simon")
    const [date, setDate] = useState(new Date())
    const [currentTime, setCurrentTime] = useState(date.toLocaleTimeString())
    const [location, setLocation] = useState("Kitchen")
    const [outsideTemperature, setOutsideTemperature] = useState(20)

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

    return (
        <div>
            <List>
                <ListItem>
                    {currentUser}
                </ListItem>
                <ListItem>
                    {date.toDateString()}
                </ListItem>
                <ListItem>
                    {currentTime}
                </ListItem>
                <ListItem>
                    {location}
                </ListItem>
                <ListItem>
                    {outsideTemperature} &#176;
                </ListItem>

            </List>
            
        </div>
    )
}

export default SimulationParameters

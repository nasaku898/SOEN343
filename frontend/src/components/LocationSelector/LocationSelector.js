import React, { useState } from 'react'
import { Select, FormControl, InputLabel, MenuItem } from '@material-ui/core'

const LocationSelector = ({ rooms, setRoomId }) => {

    const [location, setLocation] = useState("")

    const handleChange = (event) => {
        setRoomId(event.target.value)
        setLocation(event.target.value)
    }

    return (
        <div>
            <FormControl>
                <InputLabel>Location</InputLabel>
                <Select
                    labelId="demo-customized-select-label"
                    id="demo-customized-select"
                    value={location}
                    onChange={handleChange}
                >
                    {
                        rooms.slice().map(room => <MenuItem key={room.roomId} value={room.roomId}>{room.name}</MenuItem>)
                    }
                </Select>
            </FormControl>
        </div>
    )
}

export default LocationSelector

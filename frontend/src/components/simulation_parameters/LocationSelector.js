import {FormControl, InputLabel, MenuItem, Select} from '@material-ui/core'
import React from 'react';
import {moveUser} from "../../modules/UserProfileList/UserService";

export const LocationSelector = (
    locationName,
    {house},
    username) => {
    const handleLocationChange = (event) => {
        event.preventDefault();
        (async () => {
            const roomId = house.rooms.find(room => {
                return room.name === event.target.value
            });
            await moveUser(username, roomId)
        })()
    }
    return (
        <div>
            <InputLabel>Location</InputLabel>
            <FormControl>
                <Select
                    labelId="demo-customized-select-label"
                    id="demo-customized-select"
                    value={locationName} onChange={handleLocationChange}>
                    >
                    {house.rooms
                        .map((room, index) => (
                            <MenuItem key={index} value={room.name}>{room.name}</MenuItem>
                        ))
                    };
                </Select>
            </FormControl>
        </div>
    )
}
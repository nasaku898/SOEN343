import { FormControl, InputLabel, MenuItem, Select, Typography } from '@material-ui/core'
import React, { useEffect, useState } from 'react'
import { useCurrentHouse } from '../../context/CurrentHouse'
import { getAllHouses, getHouse, localStorageHouseID } from '../../modules/HouseOverview/HouseService'

const HouseSelector = () => {
    const {setHouse} = useCurrentHouse()
    const [selectedHouse, setSelectedHouse] = useState(-1)
    const [listOfHouses, setListOfHouses] = useState([])

    useEffect(() => {
        getAllHouses().then(data => {
            setListOfHouses(data)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`);
        })
    }, [])

    const handleChange = (event) => {
        event.preventDefault()
        setSelectedHouse(event.target.value)
        getHouse(event.target.value).then(data => {
            console.log(data)
            setHouse(data)
            localStorageHouseID(data.id)
        }).catch(error => {
            alert(`Status: ${error.status}: ${error.message}`);
        })
    }
    return (
        <div style={{ float: "right" }}>
            <Typography variant="h1">Select a House</Typography>
            <InputLabel>Houses</InputLabel>
            <FormControl>
                <Select
                    labelId="demo-customized-select-label"
                    id="demo-customized-select"
                    onChange={handleChange}
                    value={selectedHouse}
                >
                    {
                        listOfHouses.map((house => (
                            <MenuItem key={house.id} value={house.id}>
                                {house.id}
                            </MenuItem>
                        )))
                    }
                </Select>
            </FormControl>
        </div>
    )
}

export default HouseSelector

import React, {useEffect, useState} from 'react';
import {getUser} from "../../modules/UserProfileList/UserService.js"
import {fetchHouseState, updateTemperatureOutside} from "../../modules/HouseOverview/HouseService";
import SimulationField from "./SimulationField";
import SimulationHeader from "./SimulationHeader";
import LocationSelector from "../LocationSelector/LocationSelector";

const SimulationForm = () => {
    const [user, setUser] = useState({
        id: "",
        email: "",
        username: "",
        firstName: "",
        lastName: "",
        role: "GUEST",
        location: ""
    });

    const [house, setHouse] = useState({
        id: "",
        rooms: [{}],
        temperatureOutside: "",
    });

    const [locationName, setLocationName] = useState("");
    const [temperature, setTemperature] = useState("")
    const [date, setDate] = useState(new Date());
    let disabled = true;

    useEffect(() => {
        (async () => {
            setUser(await getUser("Dawgears"));
        })();

        (async () => {
            setHouse(await fetchHouseState);
            setTemperature(house.temperatureOutside);
            setLocationName(house.rooms.find(room => {
                    return room === user.location
                })
            );
        })();

    }, [house.rooms, house.temperatureOutside, user.location]);

    const handleTemperatureChange = (event) => {
        event.preventDefault();
        (async () => {
            await updateTemperatureOutside(house.id, event.target.value);
        })();
    }

    const handleDateChange = (event) => {
        event.preventDefault();
        setDate(event.target.value);
    }

    return (
        <div>
            <SimulationHeader user={user}/>

            <LocationSelector
                locationName={locationName}
                house={house}
                username={user.username}
            />

            <SimulationField
                name="date"
                id="date"
                type="text"
                onChange={handleDateChange}
                value={date}
                disabled={disabled}
            />

            <SimulationField
                name="temperature"
                id="temperature"
                type="text"
                onChange={handleTemperatureChange}
                value={temperature}
                disabled={disabled}
            />

            disabled &&
            <button onClick={!disabled}>Edit Parameters</button>
            !disabled &&
            <button onClick={!disabled} type="submit">Edit Parameters</button>
        </div>
    );
};

export default SimulationForm;

import React, {useEffect, useState} from 'react';
import {moveUser} from "../../modules/UserProfileList/UserService.js"
import {getHouse, updateTemperatureOutside} from "../../modules/HouseOverview/HouseService";
import SimulationHeader from "./SimulationHeader";
import SimulationField from "./SimulationField";
import {LocationChooser} from "./LocationChooser";
import {getUser} from "../../modules/UserProfileList/UserService";

const SimulationForm = () => {
    const [user, setUser] = useState({
        id: 0,
        email: "",
        username: "",
        firstName: "",
        lastName: "",
        role: "GUEST",
        roomId: 2
    });

    const [house, setHouse] = useState({
        id: 1,
        rooms: [],
        temperatureOutside: 0,
    });

    const [date, setDate] = useState(new Date());
    const [disabled, setDisabled] = useState(true);


    useEffect(() => {
            (async () => {
                setUser(await getUser("Dawgears"));
            })();

        },
        [user.roomId]);

    useEffect(() => {
        (async () => {
            setHouse(await getHouse(1))
            setDisabled(true);
        })()
    }, []);

    const handleTemperatureChange = (event) => {
        event.preventDefault();
        setHouse({...house, temperatureOutside: event.target.value});
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        const fd = new FormData(event.target);

        (async () => {

            await updateTemperatureOutside(parseInt(house.id), house.temperatureOutside);
            const {id} = house.rooms.find(room =>
                room.id === ~~fd.get('location')
            );
            await moveUser(user.username, id);
        })();
        setDisabled(true);
    }

    const handleDateChange = (event) => {
        event.preventDefault();
        setDate(event.target.value);
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <SimulationHeader user={user}/>

                {house.rooms.length &&
                <LocationChooser
                    name={"location"}
                    disabled={disabled}
                    locationName={house.rooms[0].name}
                    rooms={house.rooms}
                    username={user.username}
                />}

                <SimulationField
                    name="date"
                    id="date"
                    type="text"
                    onChange={handleDateChange}
                    value={date}
                    disabled={disabled}
                />
                <br/>
                <SimulationField
                    name="temperature"
                    id="temperature"
                    type="text"
                    onChange={handleTemperatureChange}
                    value={house.temperatureOutside}
                    disabled={disabled}
                />
                <br/>
                {disabled
                    ? <button onClick={(event) => {
                        event.preventDefault()
                        setDisabled(!disabled)
                    }}>Edit Parameters</button>
                    : <button type="submit">Submit</button>
                }

            </form>

        </>
    );
};

export default SimulationForm;

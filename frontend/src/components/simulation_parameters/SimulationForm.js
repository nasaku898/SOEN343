import React, { useEffect, useState } from "react";

import {
  updateTemperatureOutside,
  getCity,
} from "../../modules/HouseOverview/CityService";
import SimulationHeader from "./SimulationHeader";
import SimulationField from "./SimulationField";
import { LocationChooser } from "./LocationChooser";
import { useCurrentHouse } from "../../context/CurrentHouse";
import { useUser } from "../../context/UserContext";
import { moveUserToRoom } from "../../modules/HouseOverview/SimulationService";

const SimulationForm = () => {
  const { house } = useCurrentHouse();
  const { user } = useUser();

  const [date, setDate] = useState(new Date());
  const [disabled, setDisabled] = useState(true);

  const [city, setCity] = useState(null);

  const [outside, setOutside] = useState(true);

  useEffect(() => {
    if (user) {
      setOutside(user.isOutisde && disabled);
    }
  }, [user, disabled]);

  useEffect(() => {
    if (house) {
      (async () => {
        setCity(await getCity(house.city));
        console.log(house);
      })();
    }
  }, [house]);

  const handleTemperatureChange = (event) => {
    event.preventDefault();
    setCity({ ...city, temperatureOutside: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const fd = new FormData(event.target);

    (async () => {
      await updateTemperatureOutside(city.name, city.temperatureOutside);
      const { roomId } = house.rooms.find(
        (room) => room.name === fd.get("location")
      );
      await moveUserToRoom(user.username, roomId);
    })();
    setDisabled(true);
  };

  const handleDateChange = (event) => {
    event.preventDefault();
    setDate(event.target.value);
  };

  if (city) {
    return (
      <>
        <form onSubmit={handleSubmit}>
          <SimulationHeader user={user} city={city} />
          {outside ? (
            <p>is outside</p>
          ) : (
            house.rooms.length && (
              <LocationChooser
                name={"location"}
                disabled={disabled}
                locationName={house.rooms[0].name}
                rooms={house.rooms}
                username={user.username}
              />
            )
          )}

          <SimulationField
            name="date"
            id="date"
            type="text"
            onChange={handleDateChange}
            value={date}
            disabled={disabled}
          />
          <br />
          <SimulationField
            name="temperature"
            id="temperature"
            type="text"
            onChange={handleTemperatureChange}
            value={city.temperatureOutside}
            disabled={disabled}
          />
          <br />
          {disabled ? (
            <button
              onClick={(event) => {
                event.preventDefault();
                setDisabled(!disabled);
              }}
            >
              Edit Parameters
            </button>
          ) : (
            <button type="submit">Submit</button>
          )}
        </form>
      </>
    );
  }

  return <></>;
};

export default SimulationForm;

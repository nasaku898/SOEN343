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
import Time from "../Time/Time";
import MonthSelector from "../MonthSelector/MonthSelector";

const SimulationForm = () => {
  const { house } = useCurrentHouse();
  const { user } = useUser();

  const [setDate] = useState(new Date());
  const [disabled, setDisabled] = useState(true);

  const [city, setCity] = useState(null);

  const [outside, setOutside] = useState(true);
  const [locationName, setLocationName] = useState("outside");

  useEffect(() => {
    if (user) {
      setOutside(user.outisde && disabled);
      if (!user.outside) {
        setLocationName(user.location.name);
      }
    }
  }, [user, disabled]);

  useEffect(() => { }, [user]);

  useEffect(() => {
    if (house) {
      (async () => {
        setCity(await getCity(house.city));
      })();
    }
  }, [house, user]);

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
                  locationName={locationName}
                  rooms={house.rooms}
                  username={user.username}
                />
              )
            )}
          <Time time={1523104441258} disabled={disabled}></Time>
          <br />
          <MonthSelector />
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

import React, { useEffect, useState } from "react";
import {
  getAuthenticatedUser,
  moveUser,
} from "../../modules/UserProfileList/UserService.js";
import {
  getHouse,
  updateTemperatureOutside,
} from "../../modules/HouseOverview/HouseService";
import SimulationHeader from "./SimulationHeader";
import SimulationField from "./SimulationField";
import { LocationChooser } from "./LocationChooser";
import { useCurrentHouse } from "../../context/CurrentHouse";
import { getCity } from "../../modules/HouseOverview/SimulationService.js";
import { useUser } from "../../context/UserContext";

const SimulationForm = () => {
  const { house } = useCurrentHouse();
  const { user } = useUser();

  const [date, setDate] = useState(new Date());
  const [disabled, setDisabled] = useState(true);

  const [city, setCity] = useState(null);

  useEffect(() => {
    if (house) {
      (async () => {
        console.log(house.city);
        setCity(await getCity(house.name));
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
      await updateTemperatureOutside(
        parseInt(house.id),
        house.temperatureOutside
      );
      const { id } = house.rooms.find(
        (room) => room.id === ~~fd.get("location")
      );
      await moveUser(user.username, id);
    })();
    setDisabled(true);
  };

  const handleDateChange = (event) => {
    event.preventDefault();
    setDate(event.target.value);
  };

  if (house) {
    return (
      <>
        <form onSubmit={handleSubmit}>
          <SimulationHeader user={user} />

          {house.rooms.length && (
            <LocationChooser
              name={"location"}
              disabled={disabled}
              locationName={house.rooms[0].name}
              rooms={house.rooms}
              username={user.username}
            />
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
            value={house.temperatureOutside}
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

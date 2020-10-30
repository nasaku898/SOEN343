import React from "react";

const SimulationHeader = ({ user, city }) => {
  console.log(city);
  return (
    <>
      <h3>Simulation Parameters</h3>
      <h4>User: {user.username}</h4>
      <h4>Role: {user.role}</h4>
      <h4>City: {city.name}</h4>
    </>
  );
};

export default SimulationHeader;

import React from 'react';

const SimulationHeader = ({user}) => {
    return (
        <div>
            <h3>Simulation Parameters</h3>
            <h3>{user.username}</h3>
            <h3>{user.role}</h3>
        </div>
    );
};

export default SimulationHeader;

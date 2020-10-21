import React, {Fragment} from 'react';

const SimulationField =
    ({
         name,
         type,
         onChange,
         value,
         disabled,
     }) => {
        return (
            <Fragment>
                <input
                    name={name}
                    id={name}
                    type={type}
                    onChange={onChange}
                    value={value}
                    disabled={disabled}
                />

            </Fragment>
        );
    };

export default SimulationField;

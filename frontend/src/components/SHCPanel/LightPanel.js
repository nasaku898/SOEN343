import React from 'react';
import FormControl from "@material-ui/core/FormControl";
import {Checkbox, FormGroup, FormLabel} from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";

const LightPanel = ({
                        lights,
                        handleItemChange,
                        classes,
                    }) => {
    return (
        <>
            <FormControl component="fieldset" className={classes.checkboxFormControl}>
                <FormLabel component="legend">On</FormLabel>
                {
                    lights.map(light => {
                        return (
                            <FormGroup>
                                <FormControlLabel
                                    control={<Checkbox key={`on-light-${light.id}`} checked={light.lightOn}
                                                       onChange={(event) => handleItemChange(event, 'light')}
                                                       name={`${light.id}`}/>}
                                    label={light.id}
                                />
                            </FormGroup>
                        )
                    })}
            </FormControl>
        </>
    );
};

export default LightPanel;

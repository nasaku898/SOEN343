import React from 'react';
import FormControl from "@material-ui/core/FormControl";
import {Checkbox, FormGroup, FormLabel} from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";

const DoorPanel = ({
                       doors,
                       handleItemChange,
                       classes,
                   }) => {
    return (
        <>
            <FormControl component="fieldset" className={classes.checkboxFormControl}>
                <FormLabel component="legend">Open</FormLabel>
                {
                    doors.map(door => {
                            if (door.locked != null) {
                                return (
                                    <FormGroup>
                                        <FormControlLabel
                                            control={<Checkbox
                                                key={`lock-door-${door.id}`}
                                                checked={door.locked}
                                                onChange={(event) => handleItemChange(event, 'exteriorDoorLock')}
                                                name={`${door.id}`}/>}
                                            label={door.id}
                                        />
                                    </FormGroup>
                                )
                            }
                        }
                    )
                }
                <FormLabel component="legend">Locked</FormLabel>
                {
                    doors.map(door => {
                        if (door.locked != null) {
                            return (
                                <FormGroup>
                                    <FormControlLabel
                                        control={<Checkbox key={`lock-door-${door.id}`}
                                                           checked={door.locked}
                                                           onChange={(event) => handleItemChange(event, 'exteriorDoorLock')}
                                                           name={`${door.id}`}/>}
                                        label={door.id}
                                    />
                                </FormGroup>
                            )
                        }
                        return <></>
                    })}
            </FormControl>
        </>
    )
};

export default DoorPanel;

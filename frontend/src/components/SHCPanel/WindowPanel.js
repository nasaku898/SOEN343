import React from 'react';
import FormControl from "@material-ui/core/FormControl";
import {Checkbox, FormGroup, FormLabel} from "@material-ui/core";
import FormControlLabel from "@material-ui/core/FormControlLabel";

const WindowPanel = ({
                         houseWindows,
                         handleItemChange,
                         classes,
                     }) => {
    return (
        <>
            <FormControl component="fieldset" className={classes.checkboxFormControl}>
                <FormLabel component="legend">Open</FormLabel>
                {
                    houseWindows.map(window => {
                        return (
                            <FormGroup>
                                <FormControlLabel
                                    control={<Checkbox key={`open-window-${window.id}`}
                                                       checked={window.open}
                                                       onChange={(event) => handleItemChange(event, 'windowOpen')}
                                                       name={`${window.id}`}/>}
                                    label={window.id}
                                />
                            </FormGroup>
                        )
                    })
                }
                <FormLabel component="legend">Blocked</FormLabel>
                {
                    houseWindows.map(window => {
                        return (
                            <FormGroup>
                                <FormControlLabel
                                    control={<Checkbox key={`block-window-${window.id}`}
                                                       checked={window.blocked}
                                                       onChange={(event) => handleItemChange(event, 'windowBlock')}
                                                       name={`${window.id}`}/>}
                                    label={window.id}
                                />
                            </FormGroup>
                        )
                    })
                }
            </FormControl>
        </>
    );
};

export default WindowPanel;

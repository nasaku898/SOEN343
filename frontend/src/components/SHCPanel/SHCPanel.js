import React, {Fragment, useEffect, useState} from 'react';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import useStyles from './SHCPanelStyle';
import {fetchLightsDoorsAndWindows} from './LightsDoorsAndWindows';
import {setLightDoorOrWindow} from '../../modules/SHCPanel/setLightDoorOrWindow';
import {HOUSE_ID, ITEMS} from './SHCPanelConstants';
import DoorPanel from "./DoorPanel";
import WindowPanel from "./WindowPanel";
import LightPanel from "./LightPanel";

const SHCPanel = (props) => {
    const classes = useStyles();
    const [itemSelected, setItemSelected] = useState('');
    const [data, setData] = useState({});

    useEffect(() => {
        handleUpdateLightDoorsAndWindows();
    }, []);

    const handleUpdateLightDoorsAndWindows = () => {
        fetchLightsDoorsAndWindows(HOUSE_ID).then(response => {
            setData(response);
        });
    }

    const handleItemSelectedChange = (event) => {
        setItemSelected(event.target.value);
    };

    const handleItemChange = async (event, itemType) => {
        await setLightDoorOrWindow(event, itemType);
        handleUpdateLightDoorsAndWindows();
    }

    return (
        <div className={classes.container}>
            <FormControl className={classes.selectFormControl}>
                <Fragment>
                    <InputLabel id="item-simple-select-label">Item</InputLabel>
                    <Select
                        labelId="item-simple-select-label"
                        id="item-simple-select"
                        value={itemSelected}
                        onChange={handleItemSelectedChange}
                    >
                        {
                            ITEMS.map(item => {
                                return <MenuItem key={item.value} value={item.value}>{item.title}</MenuItem>
                            })
                        }
                    </Select>
                </Fragment>
            </FormControl>

            {/* Lights */}
            {itemSelected === "lights" &&
            <LightPanel
                classes={classes}
                handleItemChange={handleItemChange}
                lights={data.lights}
            />}

            {/* Doors */}
            {itemSelected === "doors" &&
            <DoorPanel handleItemChange={handleItemChange}
                       doors={data.doors}
                       classes={classes}
            />}

            {/* Windows */}
            {itemSelected === "windows" &&
            <WindowPanel handleItemChange={handleItemChange}
                         classes={classes}
                         houseWindows={data.houseWindows}
            />}
        </div>
    )
}

export default SHCPanel;
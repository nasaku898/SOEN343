import React, {useEffect, useState} from 'react';
import useStyles from './SHCPanelStyle';
import {fetchLightsDoorsAndWindows} from './LightsDoorsAndWindows';
import {houseObjectStateChangeHandler} from './HouseObjectStateChangeHandler';
import {HOUSE_ID} from './SHCPanelConstants';
import DoorPanel from "./DoorPanel";
import WindowPanel from "./WindowPanel";
import LightPanel from "./LightPanel";
import SHCHeader from "./SHCHeader";

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
        await houseObjectStateChangeHandler(event, itemType);
        handleUpdateLightDoorsAndWindows();
    }

    return (
        <div className={classes.container}>
            <SHCHeader
                classes={classes}
                handleItemSelectedChange={handleItemSelectedChange}
                temSelected={itemSelected}
            />

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
import React, { useEffect, useState } from "react";
import useStyles from "./SHCPanelStyle";
import { fetchLightsDoorsAndWindows } from "./LightsDoorsAndWindows";
import { houseObjectStateChangeHandler } from "./HouseObjectStateChangeHandler";
import { HOUSE_ID } from "./SHCPanelConstants";
import DoorPanel from "./DoorPanel";
import WindowPanel from "./WindowPanel";
import LightPanel from "./LightPanel";
import SHCHeader from "./SHCHeader";
import { useCurrentHouse } from "../../context/CurrentHouse";

const SHCPanel = (props) => {
  const { house } = useCurrentHouse();
  const classes = useStyles();
  const [itemSelected, setItemSelected] = useState("");
  const [data, setData] = useState({});

  useEffect(() => {
    if (house) {
      handleUpdateLightDoorsAndWindows();
    }
  }, [house]);

  const handleUpdateLightDoorsAndWindows = () => {
    setData(fetchLightsDoorsAndWindows(house));
  };

  const handleItemSelectedChange = (event) => {
    setItemSelected(event.target.value);
  };

  const handleItemChange = async (event, itemType) => {
    await houseObjectStateChangeHandler(event, itemType);
    handleUpdateLightDoorsAndWindows();
  };

  if (data) {
    return (
      <div className={classes.container}>
        <SHCHeader
          classes={classes}
          handleItemSelectedChange={handleItemSelectedChange}
          temSelected={itemSelected}
        />

        {/* Lights */}
        {itemSelected === "lights" && (
          <LightPanel
            classes={classes}
            handleItemChange={handleItemChange}
            lights={data.lights}
          />
        )}

        {/* Doors */}
        {itemSelected === "doors" && (
          <DoorPanel
            handleItemChange={handleItemChange}
            doors={data.doors}
            classes={classes}
          />
        )}

        {/* Windows */}
        {itemSelected === "windows" && (
          <WindowPanel
            handleItemChange={handleItemChange}
            classes={classes}
            houseWindows={data.houseWindows}
          />
        )}
      </div>
    );
  }
  return <></>;
};

export default SHCPanel;

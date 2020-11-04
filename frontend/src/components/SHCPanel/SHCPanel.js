import React, { useEffect, useState } from "react";
import useStyles from "./SHCPanelStyle";
import { fetchLightsDoorsAndWindows } from "./LightsDoorsAndWindows";
import { houseObjectStateChangeHandler } from "./HouseObjectStateChangeHandler";
import DoorPanel from "./DoorPanel";
import WindowPanel from "./WindowPanel";
import LightPanel from "./LightPanel";
import SHCHeader from "./SHCHeader";
import { useCurrentHouse } from "../../context/CurrentHouse";
import { getHouse } from "../../modules/HouseOverview/HouseService";

const SHCPanel = () => {
  const {house, setHouse}  = useCurrentHouse();
  const classes = useStyles();
  const [itemSelected, setItemSelected] = useState("");
  const [data, setData] = useState(null);

  useEffect(() => {
    if (house) {
      handleUpdateLightDoorsAndWindows();
    }
  }, [house]);

  const handleUpdateLightDoorsAndWindows = () => {
    if (house.rooms) {
      setData(fetchLightsDoorsAndWindows(house.rooms));
    }
  };

  const handleItemSelectedChange = (event) => {
    setItemSelected(event.target.value);
  };

  const handleItemChange = async (event, itemType) => {
    await houseObjectStateChangeHandler(event, itemType);
    await handleUpdateLightDoorsAndWindows();
    getHouse(localStorage.getItem("houseID")).then((data)=>{
      setHouse(data)
    })
  };

  if (data) {
    return (
      <div className={classes.container}>
        <SHCHeader
          classes={classes}
          handleItemSelectedChange={handleItemSelectedChange}
          itemSelected={itemSelected}
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
            windows={data.windows}
          />
        )}
      </div>
    );
  }
  return <></>;
};

export default SHCPanel;

import {findAllRooms} from "../../modules/HouseOverview/SimulationService";

export const fetchLightsDoorsAndWindows = async (houseId) => {
  const {data} = await findAllRooms(houseId);
  const lightsDoorsWindows = {lights: [], doors: [], windows: []};

  data.forEach(dataItem => {
    lightsDoorsWindows.lights = lightsDoorsWindows.lights.concat(dataItem.lights)
    lightsDoorsWindows.doors = lightsDoorsWindows.doors.concat(dataItem.doors);
    lightsDoorsWindows.windows = lightsDoorsWindows.windows.concat(dataItem.houseWindows)
  });
  return lightsDoorsWindows;
}
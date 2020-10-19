import axios from 'axios';
import '../../Utils/config';

export const fetchLightsDoorsAndWindows = async (houseId) => {
  try {
    const { data } = await axios.get(`${global.config.BACKEND_URL}/api/simulation/house/${houseId}/roomState/all`);

    let lightsDoorsWindows = {lights: [], doors: [], houseWindows: []};

    data.forEach(dataItem => {
      lightsDoorsWindows.lights = lightsDoorsWindows.lights.concat(dataItem.lights)
      lightsDoorsWindows.doors = lightsDoorsWindows.doors.concat(dataItem.doors);
      lightsDoorsWindows.houseWindows = lightsDoorsWindows.houseWindows.concat(dataItem.houseWindows)
    });

    return lightsDoorsWindows;
  } catch (error) {
    throw error.response.data;
  }
}
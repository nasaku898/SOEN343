export const fetchLightsDoorsAndWindows = async ({ house }) => {
  if (house) {
    const rooms = house.rooms;
    const lightsDoorsWindows = { lights: [], doors: [], windows: [] };

    rooms.forEach((roomsItem) => {
      lightsDoorsWindows.lights = lightsDoorsWindows.lights.concat(
        roomsItem.lights
      );
      lightsDoorsWindows.doors = lightsDoorsWindows.doors.concat(
        roomsItem.doors
      );
      lightsDoorsWindows.windows = lightsDoorsWindows.windows.concat(
        roomsItem.houseWindows
      );
    });
    return lightsDoorsWindows;
  }
};

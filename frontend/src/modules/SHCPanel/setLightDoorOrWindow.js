import axios from 'axios';

export const setLightDoorOrWindow = async (event, itemType) => {
  const itemID = event.target.name;
  let params = {};

  if (itemType === 'light') {
    params = {
      desiredState: event.target.checked
    }
  }
  else if (itemType === 'exteriorDoorOpen') {
    itemType = 'exteriorDoor';
    params = {
      open: true,
      desiredState: event.target.checked
    }
  }
  else if (itemType === 'exteriorDoorLock') {
    itemType = 'exteriorDoor';
    params = {
      open: false,
      desiredState: event.target.checked
    }
  }
  else if (itemType === 'windowOpen') {
    itemType = 'window';
    params = {
      open: true,
      desiredState: event.target.checked
    }
  }
  else if (itemType === 'windowBlock') {
    itemType = 'window';
    params = {
      open: false,
      desiredState: event.target.checked
    }
  }
  else {
    throw new Error("ItemType specified does not exist.");
  }

  try {
    await axios.put(`${global.config.BACKEND_URL}/api/house/${itemType}/${itemID}`, null, { params })
  } catch (error) {
    throw error.response.data;
  }
}
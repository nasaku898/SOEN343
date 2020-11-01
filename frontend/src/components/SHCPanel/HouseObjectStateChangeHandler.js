// import {
//   modifyExteriorDoorState,
//   modifyLightState,
//   modifyWindowState,
// } from "../../modules/HouseOverview/HouseService";

// /*
//  Not a huge fan of this tbh, i think we should allow for more than one DB hit at the same time...
//  Doesnt seem too user friendly to make them have to do this same action if they wanna change multiple states at once
// */
// export const houseObjectStateChangeHandler = async (event, itemType) => {
//   const itemID = event.target.name;

//   switch (itemType) {
//     case "light":
//       await modifyLightState(itemID, event.target.checked);
//       break;

//     // case "exteriorDoorOpen":
//     //   await modifyExteriorDoorState(itemID, true, event.target.checked);
//     //   break;

//     // case "exteriorDoorLock":
//     //   await modifyExteriorDoorState(itemID, false, event.target.checked);
//     //   break;

//     case "windowOpen":
//       await modifyWindowState(itemID, true, event.target.checked);
//       break;

//     case "windowBlock":
//       await modifyWindowState(itemID, false, event.target.checked);
//       break;

//     default:
//       throw new Error("ItemType specified does not exist.");
//   }
// };

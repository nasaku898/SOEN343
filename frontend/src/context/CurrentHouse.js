import { createContext, useContext } from "react";

export const HouseContext = createContext();

export const useCurrentHouse = () => {
  return useContext(HouseContext);
};

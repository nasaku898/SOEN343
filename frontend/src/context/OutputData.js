import { createContext, useContext } from "react";

export const OutputDataContext = createContext();

export const useOutputData = () => {
  return useContext(OutputDataContext);
};

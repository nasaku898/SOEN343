import { createContext, useContext } from "react";

export const DateContext = createContext();

export const useCurrentDate = () => {
    return useContext(DateContext);
};
import { Grid } from "@material-ui/core";
import React, { useState, useContext, useEffect } from "react";
import HouseLayout from "../HouseLayout/HouseLayout";
import UserProfileList from "../UserProfileList/UserProfileList";
import HouseOverview from "../HouseOverview/HouseOverview";
import OutputConsole from "../OutputConsole/OutputConsole";
import SimulationForm from "../simulation_parameters/SimulationForm";
import { useUser } from "../../context/UserContext";
import { useCurrentHouse } from "../../context/CurrentHouse";
const SHSPage = () => {
  const [outputData, setOutputData] = useState([
    { id: 1, date: new Date(), data: "This is a sample action log." },
  ]);

  const { user } = useUser();
  const { house } = useCurrentHouse();

  return (
    <div>
      <Grid container direction="row">
        <Grid item xs={12} lg={3}>
          {/* {<SimulationForm></SimulationForm>} */}
        </Grid>
        <Grid item lg={9} xs={12}>
          <HouseLayout></HouseLayout>
        </Grid>
        <Grid item xs={12} lg={3}>
          {/* <UserProfileList></UserProfileList> */}
        </Grid>
        <Grid item xs={12} lg={6}>
          {/* <HouseOverview></HouseOverview> */}
        </Grid>
        <Grid item xs={12} lg={3}>
          <OutputConsole outputData={outputData}></OutputConsole>
        </Grid>
      </Grid>
    </div>
  );
};

export default SHSPage;

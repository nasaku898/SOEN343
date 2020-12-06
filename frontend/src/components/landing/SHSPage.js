import { Grid } from "@material-ui/core";
import React from "react";
import HouseLayout from "../HouseLayout/HouseLayout";
import UserProfileList from "../UserProfileList/UserProfileList";
import HouseOverview from "../HouseOverview/HouseOverview";
import OutputConsole from "../OutputConsole/OutputConsole";
import SimulationForm from "../simulation_parameters/SimulationForm";
const SHSPage = () => {

  return (
    <div>
      <Grid container direction="row">
        <Grid item xs={12} lg={3}>
          {<SimulationForm></SimulationForm>}
        </Grid>
        <Grid item lg={9} xs={12}>
          <HouseLayout></HouseLayout>
        </Grid>
        <Grid item xs={12} lg={3}>
          <UserProfileList></UserProfileList>
        </Grid>
        <Grid item xs={12} lg={6}>
          <HouseOverview></HouseOverview>
        </Grid>
        <Grid item xs={12} lg={3}>
          <OutputConsole></OutputConsole>
        </Grid>
      </Grid>
    </div>
  );
};

export default SHSPage;

import React from "react";
import { Grid } from "@material-ui/core";
import HouseLayout from "../HouseLayout/HouseLayout";
import UserProfileList from "../UserProfileList/UserProfileList";
import OutputConsole from "../OutputConsole/OutputConsole";
import SimulationForm from "../simulation_parameters/SimulationForm";
import SHHPanel from "../SHHPanel/SHHPanel";

const SHHPage = () => {
    
    return (
      <div>

        <Grid container direction="row">
          <Grid item xs={12} lg={3}>
            <SimulationForm></SimulationForm>
          </Grid>
          <Grid item lg={9} xs={12}>
            <HouseLayout></HouseLayout>
          </Grid>
          <Grid item xs={12} lg={3}>
            <UserProfileList></UserProfileList>
          </Grid>
          <Grid item xs={12} lg={6}>
            <SHHPanel></SHHPanel>
          </Grid>
          <Grid item xs={12} lg={3}>
            <OutputConsole></OutputConsole>
          </Grid>
        </Grid>
      </div>
    );
};

export default SHHPage;
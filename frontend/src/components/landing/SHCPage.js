import React, { useState } from "react";
import { Grid } from "@material-ui/core";
import HouseLayout from "../HouseLayout/HouseLayout";
import UserProfileList from "../UserProfileList/UserProfileList";
import OutputConsole from "../OutputConsole/OutputConsole";
import SHCPanel from "../SHCPanel/SHCPanel";
import SimulationForm from "../simulation_parameters/SimulationForm";
import { useCurrentHouse } from "../../context/CurrentHouse";
const SHCPage = () => {
  const [outputData, setOutputData] = useState([
    { id: 1, date: new Date(), data: "This is a sample action log." },
  ]);

  const { house } = useCurrentHouse();
  if (house) {
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
            <SHCPanel></SHCPanel>
          </Grid>
          <Grid item xs={12} lg={3}>
            <OutputConsole outputData={outputData}></OutputConsole>
          </Grid>
        </Grid>
      </div>
    );
  }
  return <></>;
};

export default SHCPage;

import React from "react";
import { Box, Input, Typography } from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import useStyles from "./HouseLayoutStyle";
import { loadHouseLayout } from "../../modules/HouseOverview/LoadSimulationService";
import { useCurrentHouse } from "../../context/CurrentHouse";
import { useHistory } from "react-router-dom";
import { localStorageHouseID } from "../../modules/HouseOverview/HouseService";
// TODO: Ideally this should be a modal..
const HouseUploadForm = () => {
  const classes = useStyles();
  const {setHouse} = useCurrentHouse();
  const history = useHistory();
  const handleHouseLayoutUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (event) => {
      loadHouseLayout(JSON.parse(event.target.result))
        .then((data) => {
          setHouse(data)
          localStorageHouseID(data.id)
          history.push("/")
        })
        .catch((error) => {
          alert(`Status: ${error.status}: ${error.message}`);
        });
    };

    reader.readAsText(file);
  };

  return (
    <>
      <div className={classes.uploadWrapper}>
        <form>
          <label htmlFor="file" className={classes.uploadBTN}>
            <Box className={classes.uploadBTN}>
              <Typography>
                <AddIcon />
              </Typography>
            </Box>
          </label>
        </form>
      </div>
      <Input
        className={classes.Input}
        type="file"
        id="file"
        name="file"
        inputProps={{ accept: ".json" }}
        onChange={handleHouseLayoutUpload}
      ></Input>
    </>
  );
};

export default HouseUploadForm;

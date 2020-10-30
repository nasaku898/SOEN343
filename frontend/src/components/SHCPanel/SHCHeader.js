import React, { Fragment } from "react";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import { ITEMS } from "./SHCPanelConstants";
import MenuItem from "@material-ui/core/MenuItem";

const SHCHeader = ({ itemSelected, handleItemSelectedChange, classes }) => {
  return (
    <>
      <FormControl classN ame={classes.selectFormControl}>
        <Fragment>
          <InputLabel id="item-simple-select-label">Item</InputLabel>
          <Select
            labelId="item-simple-select-label"
            id="item-simple-select"
            value={itemSelected}
            onChange={handleItemSelectedChange}
          >
            {ITEMS.map((item) => {
              return (
                <MenuItem key={item.value} value={item.value}>
                  {item.title}
                </MenuItem>
              );
            })}
          </Select>
        </Fragment>
      </FormControl>
    </>
  );
};

export default SHCHeader;

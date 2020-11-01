import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Tabs,
  Tab,
  Button,
  Typography,
  Box,
} from "@material-ui/core";
import AccountCircleOutlinedIcon from "@material-ui/icons/AccountCircleOutlined";
import { Link } from "react-router-dom";
import useStyles from "./NavbarStyle";
import { AuthContext } from "../../context/Auth";
import Axios from "axios";
import "../../Utils/config";
import { useHistory } from "react-router-dom";
import { useUser } from "../../context/UserContext";

const Navbar = (props) => {
  const [selectedTab, setSelectedTab] = React.useState(0);
  const classes = useStyles();
  const history = useHistory();

  const handleChange = (event, newValue) => {
    setSelectedTab(newValue);
  };

  const { user, setUser } = useUser();

  const [authTokens, setAuthTokens] = useState(
    localStorage.getItem("token") || ""
  );

  const setTokens = (data) => {
    localStorage.setItem("token", JSON.stringify(data));
    setAuthTokens(data);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setUser(null);
    Axios.get(global.config.BACKEND_URL + `/logout`);
    history.push("/login");
  };

  return (
    <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens }}>
      <Box component="nav">
        <AppBar position="static" style={{ background: "#222" }}>
          <Toolbar>
            <Link to="/" className={classes.navItem}>
              <Typography variant="h4">Smart Home Simulator</Typography>
            </Link>

            <Tabs value={selectedTab} onChange={handleChange}>
              <Tab label="SHS" component={Link} to="/shs"></Tab>
              <Tab label="SHC" component={Link} to="/shc"></Tab>
              <Tab label="SHP"></Tab>
              <Tab label="SHH"></Tab>
              <Tab label="+"></Tab>
            </Tabs>

            {user ? (
              <Button
                variant="contained"
                startIcon={<AccountCircleOutlinedIcon />}
                onClick={handleLogout}
              >
                Logout
              </Button>
            ) : (
              <Link to="/login">
                {
                  <Button
                    variant="contained"
                    startIcon={<AccountCircleOutlinedIcon />}
                  >
                    Login
                  </Button>
                }
              </Link>
            )}
          </Toolbar>
        </AppBar>
      </Box>
    </AuthContext.Provider>
  );
};
export default Navbar;

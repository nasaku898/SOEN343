import React from 'react'
import { AppBar, Toolbar, Tabs, Tab, Button, Typography, Box } from "@material-ui/core"
import AccountCircleOutlinedIcon from '@material-ui/icons/AccountCircleOutlined';
import { Link } from 'react-router-dom';
import useStyles from './NavbarStyle'
const Navbar = () => {
    const [selectedTab, setSelectedTab] = React.useState(0);
    const classes = useStyles()

    const handleChange = (event, newValue) => { setSelectedTab(newValue); }

    return (
        <Box component="nav">
            <AppBar position="static" style={{ background: "#222" }}>
                <Toolbar>
                    <Link to="/" className={classes.navItem}>
                        <Typography variant="h4">
                            Smart Home Simulator
                        </Typography>
                    </Link>

                    <Tabs value={selectedTab} onChange={handleChange}>
                        <Tab label="SHS" component={Link} to="/shs"></Tab>
                        <Tab label="SHC" component={Link} to="/shc"></Tab>
                        <Tab label="SHP"></Tab>
                        <Tab label="SHH"></Tab>
                        <Tab label="+"></Tab>
                    </Tabs>

                    <Link to="/login">
                        <Button
                            variant="contained"
                            startIcon={<AccountCircleOutlinedIcon />}
                        >
                            Login
                        </Button>
                    </Link>
                </Toolbar>
            </AppBar>
        </Box>
    )
}
export default Navbar
import React from 'react'
import { AppBar, Toolbar, Tabs, Tab, Button, Typography, Box } from "@material-ui/core"
import AccountCircleOutlinedIcon from '@material-ui/icons/AccountCircleOutlined';

const Navbar = () => {
    const [selectedTab, setSelectedTab] = React.useState(0);

    const handleChange = (event, newValue) => { setSelectedTab(newValue); }
    return (
        <Box component="nav">
            <AppBar position="static" style={{ background: "#222" }}>
                <Toolbar>
                    <Typography variant="h4">
                        Smart Home Simulator
                 </Typography>

                    <Tabs value={selectedTab} onChange={handleChange}>
                        <Tab label="SHS"></Tab>
                        <Tab label="SHC"></Tab>
                        <Tab label="SHP"></Tab>
                        <Tab label="SHH"></Tab>
                        <Tab label="+"></Tab>
                    </Tabs>

                    <Button
                        variant="contained"
                        color="red"
                        startIcon={<AccountCircleOutlinedIcon />}
                    >
                        Login
                    </Button>
                </Toolbar>
            </AppBar>
        </Box>
    )
}
export default Navbar
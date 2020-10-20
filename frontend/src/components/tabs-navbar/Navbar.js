import React from 'react'
import {
AppBar, Toolbar, Tabs, Tab, Button, Typography, Box
} from "@material-ui/core"
import AccountCircleOutlinedIcon from '@material-ui/icons/AccountCircleOutlined';
import {
    ArrowBack, AssignmentInd, Home, Apps, ArrowRight
    } from "@material-ui/icons"

import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles((theme) => ({
    button: {
        float: "right",
        position: "relative",
    },
  }));   
const Navbar = () => {
    const [selectedTab, setSelectedTab] = React.useState(0);

    const handleChange = (event, newValue) => {setSelectedTab(newValue);}
    return (
        <Box component = "nav">
         <AppBar position = "static" style ={{background: "#222"}}>
             <Toolbar>
                 <Typography variant= "h4">
                    Smart Home Simulator
                 </Typography>
                 
                 <Tabs value ={selectedTab} onChange ={handleChange}>
                 <Tab label = "SHS"></Tab>
                 <Tab label = "SHC"></Tab>
                 <Tab label = "SHP"></Tab>
                 <Tab label = "SHH"></Tab>
                 <Tab label = "+"></Tab>
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


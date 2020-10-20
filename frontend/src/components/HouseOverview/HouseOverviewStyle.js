const { makeStyles } = require("@material-ui/core");

const useStyles = makeStyles((theme) => ({
    modal: {
        backgroundColor: theme.palette.background.paper
    },
    container:{
        height:"100%"
    },
    tableWrapper:{
        border:"solid 3px white",
        height:"100%"
    }
}));

export default useStyles
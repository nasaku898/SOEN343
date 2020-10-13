const { makeStyles } = require("@material-ui/core");

const useStyles = makeStyles((theme) => ({
    editButton:{
        width:"100%",
        margin:"auto",
        marginTop:"10px"
    },
    userProfileListWrapper:{
        height:"350px",
        overflowY:"scroll",
        border:"solid 2px white"
    },
    modal:{
        backgroundColor: theme.palette.background.paper
    }
}))
export default useStyles
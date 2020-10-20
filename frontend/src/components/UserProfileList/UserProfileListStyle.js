const { makeStyles } = require("@material-ui/core");

const useStyles = makeStyles((theme) => ({
    editButton: {
        width: "100%",
        margin: "auto",
        marginTop: "10px"
    },
    userProfileListWrapper: {
        overflowY: "scroll",
        border: "solid 3px white",
        height:"100%"
    },
    modal: {
        backgroundColor: theme.palette.background.paper
    },
    container:{
        height: "100%",
        border:"solid 3px white"
    }
}))
export default useStyles
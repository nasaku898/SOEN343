const { makeStyles } = require("@material-ui/core");

const useStyles = makeStyles((theme) => ({
    layoutWrapper: {
        width: "100%",
        border: "solid 2px white"
    },
    Input: {
        display: "none",
    },
    uploadBTN:{
        '&:hover': {
            color: theme.palette.primary.main
        }
    },
    uploadWrapper:{
        width:"50px"
    }
}))

export default useStyles
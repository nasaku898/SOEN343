const { makeStyles } = require("@material-ui/core");

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
        border:"solid 3px white"
    },
}));

export default useStyles
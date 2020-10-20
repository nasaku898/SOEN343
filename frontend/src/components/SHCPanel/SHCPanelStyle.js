import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  selectFormControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
  checkboxFormControl: {
    margin: theme.spacing(3),
  },
  container:{
    border:"solid 3px white",
    height:"100%"
  }
}));

export default useStyles;
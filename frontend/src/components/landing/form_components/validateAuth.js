const validationAuth = values => {
  let errors = {};
  // email errors
  if (!values.username) {
    errors.username = "Email is required";
  } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.username)) {
    errors.username = "Invalid email address";
  }
  //password errors
  if (!values.password) {
    errors.password = "Password is required";
  } else if (values.password.length < 6) {
    console.log("FUCK");

    errors.password = "Password must be atleast 6 characters";
  }
  return errors;
};
export default validationAuth;




import React, { Fragment } from "react";
import FormInput from "./form_components/FormInput.js";
// import { Button } from "./form_components/Button.js";
import useFormValidation from "./validators/UseFormValidation";
import ValidateAuthentication from "./validators/ValidateAuthentication";
import { authenticate } from "../../modules/login_registration/AuthenticationService.js";
import { useAuth } from "../../context/Auth";
import { useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
import Button from "@material-ui/core/Button";
import { useUser } from "../../context/UserContext.js";

const INITIAL_STATE = {
  username: "",
  password: "",
};

const LoginForm = () => {
  const { setAuthTokens, authTokens } = useAuth();
  const { user, setUser } = useUser();
  const history = useHistory();
  const loginUser = async (fields) => {
    const result = await authenticate(fields);
    setUser(await result.user);
    setAuthTokens(await result.token);

    if (user) {
      history.push("/shs");
    }
  };

  const {
    handleSubmit,
    handleChange,
    handleBlur,
    values,
    errors,
    isSubmitting,
  } = useFormValidation(INITIAL_STATE, ValidateAuthentication, loginUser);

  return (
    <Fragment>
      <form onSubmit={handleSubmit}>
        {errors.username && (
          <p className="error-text alert alert-danger">{errors.username}</p>
        )}
        <FormInput
          label="Username"
          name="username"
          type="text"
          className={`${errors.username} ${"error-input"} ${"form-control"}`}
          value={values.username}
          onChange={handleChange}
          placeholder="Username"
        />
        <br />
        {errors.password && (
          <p className="error-text alert alert-danger">{errors.password}</p>
        )}
        <FormInput
          type="password"
          onChange={handleChange}
          onBlur={handleBlur}
          name="password"
          className={`${errors.password}  ${"error-input"} ${"form-control"}`}
          value={values.password}
          placeholder="Password"
        />
        <br />
        <Button type="submit" label="Submit" disabled={isSubmitting}>
          Submit
        </Button>
        <Button component={Link} to="/register">
          Register
        </Button>
      </form>
    </Fragment>
  );
};
export default LoginForm;

import React, { useState, useEffect } from "react";
import { FormInput } from "./form_components/FormInput.js";
import { Button } from "./form_components/Button.js";
import useFormValidation from "./validators/useFormValidation";
import { ValidateAuthentication } from "./validators/validateAuthentication";

const INITIAL_STATE = {
    username="",
    password=""
};

const LoginForm = () => {
    const [loggedIn, setLoggedIn] = useState(false);
    const { username, password } = values; 

    const {
        handleSubmit,
        handleChange,
        handleBlur,
        values,
        errors,
        isSubmitting
      } = useFormValidation(INITIAL_STATE, validateAuth, authenticateUser);

    return ( 
        <form
        onSubmit={handleSubmit}
        className="form-signin"
        >
            {errors.username && (
                <p className="error-text" class="alert alert-danger">
                    {errors.username}
                </p>
            )};

            <FormInput
                label="Username"
                name="username"
                type="text"
                className={errors.username && "error-input"}
                className="form-control"
                value={values.username}
                onChange={handleChange}
                placeholder="Email address"
            />
            
            <br></br>
                
            {errors.password && (
                <p className="error-text" class="alert alert-danger">
                    {errors.password}
                </p>
            )};

            <FormInput
                type="password"
                onChange={handleChange}
                onBlur={handleBlur}
                name="password"
                className={errors.password && "error-input"}
                className="form-control"
                value={values.password}
                placeholder="Password"
            />

            <br></br>

            <Button
                type="submit"
                label="Submit"
                className="button"
                handleClick={onSubmit}
            />
        
        </form>
     );
}
 
export default LoginForm;
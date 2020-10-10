import React, { useState, useEffect } from "react";
import { FormInput } from "./form_components/FormInput.js";
import { Button } from "./form_components/Button.js";
import { register } from "../../modules/login_registration.js"
import { Redirect } from "react-router-dom";
import { ValidateRegistration } from "./validators/validateRegistration.js"


const INITIAL_STATE = {
    username="",
    password=""
};

const RegistrationForm = () => {
    const [registered, setRegistered] = useState(false);
    
    const { username,
            password,
            matchingPassword,
            firstName,
            lastName,
            email,
            role    
        } = values; 

    const {
        handleSubmit,
        handleChange,
        handleBlur,
        values,
        errors,
        isSubmitting,
        role
    } = useFormValidation(INITIAL_STATE, validateAuth, authenticateUser);

    if (isRegister) {
        return <Redirect to="/login"></Redirect>;
      }

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
                label="Email"
                name="email"
                type="text"
                className={errors.email && "error-input"}
                className="form-control"
                value={values.email}
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

            <FormInput
                type="password"
                onChange={handleChange}
                onBlur={handleBlur}
                name="matchingPassword"
                className={errors.matchingPassword && "error-input"}
                className="form-control"
                value={values.matchingPassword}
                placeholder="Matching password"
            />

            <br></br>
            
            <FormInput
                label="Username"
                name="username"
                type="text"
                className={errors.username && "error-input"}
                className="form-control"
                value={values.username}
                onChange={handleChange}
                placeholder="Username"
            />

            <br></br>
            
            <FormInput
                label="FirstName"
                name="firstName"
                type="text"
                className={errors.firstName && "error-input"}
                className="form-control"
                value={values.firstName}
                onChange={handleChange}
                placeholder="First Name"
            />

            <FormInput
                label="LastName"
                name="firstName"
                type="text"
                className={errors.firstName && "error-input"}
                className="form-control"
                value={values.firstName}
                onChange={handleChange}
                placeholder="First Name"
            />

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
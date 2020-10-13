import React from "react";
import FormInput from "./form_components/FormInput.js";
import {Button} from "./form_components/Button.js";
import {register} from "../../modules/login_and_registration/AuthenticationService.js"
import ValidateRegistration from "./validators/ValidateRegistration.js";
import useFormValidation from "./validators/UseFormValidation.js";

const INITIAL_STATE = {
 email: "",
 password: "",
 matchingPassword: "",
 username: "",
 firstName: "",
 lastName: ""
};

const RegistrationForm = () => {

 const {
  handleSubmit,
  handleChange,
  handleBlur,
  values,
  errors,
  isSubmitting,
 } = useFormValidation(INITIAL_STATE, ValidateRegistration, register);

 return (
     <form
         onSubmit={handleSubmit}
         className="form-signin"
     >
      {errors.username && (
          <p className="error-text" class="alert alert-danger">
           {errors.username}
          </p>
      )}

      <FormInput
          name="email"
          type="text"
          className={errors.email && "error-input"}
          className="form-control"
          onBlur={handleBlur}
          value={values.email}
          onChange={handleChange}
          placeholder="Email address"
      />

      <br/>

      {errors.password && (
          <p className="error-text" class="alert alert-danger">
           {errors.password}
          </p>
      )}

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

      <br/>

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

      <br/>

      <FormInput
          label="Username"
          name="username"
          type="text"
          className={errors.username && "error-input"}
          className="form-control"
          value={values.username}
          onBlur={handleBlur}
          onChange={handleChange}
          placeholder="Username"
      />

      <br/>

      <FormInput
          label="FirstName"
          name="firstName"
          type="text"
          className={errors.firstName && "error-input"}
          className="form-control"
          value={values.firstName}
          onBlur={handleBlur}
          onChange={handleChange}
          placeholder="First Name"
      />

      <br/>

      <FormInput
          label="LastName"
          name="lastName"
          type="text"
          className={errors.lastName && "error-input"}
          className="form-control"
          value={values.lastName}
          onChange={handleChange}
          onBlur={handleBlur}
          placeholder="Last Name"
      />

      <br/>

      <select>
       <option value="Parent">Parent</option>
       <option value="Child">Child</option>
       <option value="Guest">Guest</option>
      </select>

      <Button
          type="submit"
          label="Submit"
          className="button"
          disabled={isSubmitting}
      />

     </form>
 );
}

export default RegistrationForm;
import React, {Fragment} from "react";
import FormInput from "./form_components/FormInput.js";
import {Button} from "./form_components/Button.js";
import useFormValidation from "./validators/UseFormValidation";
import ValidateAuthentication from "./validators/ValidateAuthentication";
import {authenticate} from "../../modules/login_registration/AuthenticationService.js"

const INITIAL_STATE = {
	username: "",
	password: ""
};

const LoginForm = () => {
	const loginUser = async () => {
		const {
			username,
			password
		} = values;
		await authenticate(values);
	}
	const {
		handleSubmit,
		handleChange,
		handleBlur,
		values,
		errors,
		isSubmitting
	} = useFormValidation(INITIAL_STATE, ValidateAuthentication, loginUser);

	return (
		<Fragment>
			<form
				onSubmit={handleSubmit}
			>
				{errors.username && (
					<p className="error-text" className="alert alert-danger">
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

				<br/>

				{errors.password && (
					<p className="error-text" className="alert alert-danger">
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

				<br/>

				<Button
					type="submit"
					label="Submit"
					className="button"
					disabled={isSubmitting}
				/>
			</form>
		</Fragment>
	);
}

export default LoginForm;
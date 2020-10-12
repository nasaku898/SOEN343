const ValidateAuthentication = values => {
	let errors = []
	const requiredFields = [
		"email",
		"password"
	];

	errors
		.concat([
			...requiredFields.map((field) => checkRequiredField(field)),
			checkEmail(values.email)
		])
		.filter((value) => value === false)

	return errors;
};

const checkRequiredField = field => {
	if (!field) return `${field} is required`;
	return true;
};

const checkEmail = email => {
	if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(email))
		return "Invalid email address";
	return true;
};

export default ValidateAuthentication;
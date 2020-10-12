const ValidateRegistration = values => {
	let errors = [];
	const requiredFields = [
		"username",
		"password",
		"firstName",
		"lastName",
		"email",
	];

	errors
		.concat([
			...requiredFields.map((field) => checkRequiredField(field)),
			checkEmail(values.username),
			checkPassword(values.password),
			checkPasswordMatches(values.password, values.matchingPassword)
		])
		.filter((value) => value !== true);

	return errors;

};

const checkRequiredField = field => {
	if (!field) return `${field} is required`;
	return true;
};

const checkPassword = password => {
	if (password.length < 6) return "Password must be at least 6 characters";
	return true;
};

const checkEmail = email => {
	if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(email))
		return "Invalid email address";
	return true;
};

const checkPasswordMatches = (password, matchingPassword) => {
	return password === matchingPassword;
}

export default ValidateRegistration;


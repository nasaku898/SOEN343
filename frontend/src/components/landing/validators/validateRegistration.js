const validateRegistration = values => {
  const errors = [];
  const requiredFields = {
    username: values.username,
    password: values.password,
    matchingPassword: values.matchingPassword,
    firstName: values.firstName,
    lastName: values.lastName,
    email: values.email,
    role: values.role
  };

  errors
    .concat([
      ...requiredFields.map((field) => checkRequiredField(field)),
      checkEmail(values.email),
      checkPassword(values.password),
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

export default validateRegistration;


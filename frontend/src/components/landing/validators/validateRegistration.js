const validateRegistration = values => {
  const errors = [];
  const requiredFields = [
    "username",
    "password",
    "firstName",
    "lastName",
    "email",
    "role"
  ];

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
  if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email))
    return "Invalid email address";
  return true;
};

export default validateRegistration;


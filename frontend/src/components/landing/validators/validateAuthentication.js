const ValidateAuthentication = values => {
    let errors = {}
    const requiredFields = {
        username: values.username,
        password: values.password
    }

    errors
        .concat([
        ...requiredFields.map((field) => checkRequiredField(field)),
        checkRequiredField(values.username),
        checkEmail(values.username),
        checkRequiredField(values.password)
        ])
        .filter((value) => value !== true);
      
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
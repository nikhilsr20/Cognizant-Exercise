import { useState } from 'react';

function Register() {
  const [fields, setFields] = useState({
    fullName: '',
    email: '',
    password: ''
  });

  const [errors, setErrors] = useState({
    fullName: '',
    email: '',
    password: ''
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    const updatedFields = { ...fields, [name]: value };
    const updatedErrors = { ...errors };

    switch (name) {
      case 'fullName':
        updatedErrors.fullName = value.length < 5 ? 'Full Name must be 5 characters long!' : '';
        break;
      case 'email': {
        const validEmailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        updatedErrors.email = validEmailRegex.test(value) ? '' : 'Email is not valid!';
        break;
      }
      case 'password':
        updatedErrors.password = value.length < 8 ? 'Password must be 8 characters long!' : '';
        break;
      default:
        break;
    }

    setFields(updatedFields);
    setErrors(updatedErrors);
  };

  const getValidationErrors = () => ({
    fullName: fields.fullName.length < 5 ? 'Full Name must be 5 characters long!' : '',
    email: /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(fields.email) ? '' : 'Email is not valid!',
    password: fields.password.length < 8 ? 'Password must be 8 characters long!' : ''
  });

  const handleSubmit = (event) => {
    event.preventDefault();
    const validationErrors = getValidationErrors();
    setErrors(validationErrors);

    if (!validationErrors.fullName && !validationErrors.email && !validationErrors.password) {
      alert('Valid Form');
    } else {
      if (validationErrors.fullName !== '') {
        alert(validationErrors.fullName);
      }
      if (validationErrors.email !== '') {
        alert(validationErrors.email);
      }
      if (validationErrors.password !== '') {
        alert(validationErrors.password);
      }
    }
  };

  return (
    <div className="register-container">
      <h1>Register Here!!!</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Name:</label>
          <input
            type="text"
            name="fullName"
            value={fields.fullName}
            onChange={handleChange}
          />
        </div>
        <div className="form-row">
          <label>Email:</label>
          <input
            type="text"
            name="email"
            value={fields.email}
            onChange={handleChange}
          />
        </div>
        <div className="form-row">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={fields.password}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Register;

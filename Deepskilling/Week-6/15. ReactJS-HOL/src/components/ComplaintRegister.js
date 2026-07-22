import { useState } from 'react';

function ComplaintRegister() {
  const [formData, setFormData] = useState({
    ename: '',
    complaint: ''
  });

  const handleChange = (event) => {
    setFormData({
      ...formData,
      [event.target.name]: event.target.value
    });
  };

  const handleSubmit = (event) => {
    const numberHolder = Math.floor(Math.random() * 100) + 1;
    const msg = 'Thanks ' + formData.ename + '\n Your Complaint was Submitted.\nTransaction ID is : ' + numberHolder;
    alert(msg);
    event.preventDefault();
  };

  return (
    <div className="complaint-container">
      <h1>Register your complaints here!!!</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Name:</label>
          <input
            type="text"
            name="ename"
            value={formData.ename}
            onChange={handleChange}
          />
        </div>
        <div className="form-row">
          <label>Complaint:</label>
          <textarea
            name="complaint"
            value={formData.complaint}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default ComplaintRegister;

import React, { useState } from 'react';
import { Input } from 'semantic-ui-react';

const ContactInformationForm = () => {
  const [contactData, setContactData] = useState({
    email: '',
    phoneNumber: '',
  });

  const [error] = useState('');

  const handleContactSubmit = (e) => {
    e.preventDefault();
    console.log('Contact information submitted:', contactData);
  };

  const handleContactChange = (e) => {
    const { name, value } = e.target;
    setContactData({ ...contactData, [name]: value });
  };

  const onSubmit = (e) => {
    e.preventDefault();
    handleContactSubmit(contactData);
  };

  return (
    <>
      <h2 className="checkout-title">CONTACT INFORMATION</h2>
      <form onSubmit={onSubmit} className="checkout-form">
        {error && <div className="error-message">{error}</div>}
        <div className="form-group">
          <label className="form-label">Email Address</label>
          <Input
            name="email"
            value={contactData.email}
            onChange={handleContactChange}
            className="form-input"
            autoComplete="off"
            required
          />
        </div>
        <div className="form-group">
          <label className="form-label">Phone Number</label>
          <Input
            name="phoneNumber"
            value={contactData.phoneNumber}
            onChange={handleContactChange}
            className="form-input"
            required
          />
        </div>
      </form>
    </>
  );
};

export default ContactInformationForm;

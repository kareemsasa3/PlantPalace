import React, { useState } from 'react';
import { Input, Button } from 'semantic-ui-react';
import { useNavigate } from 'react-router-dom';
import { fetchPlaceSuggestions } from '../../api/googleApi';
import Dropdown from '../Dropdown';

const ShippingInformationForm = () => {
  const [shippingData, setShippingData] = useState({
    firstName: '',
    lastName: '',
    address: '',
    apartment: '',
    city: '',
    state: '',
    postalCode: '',
  });

  const [addressSuggestions, setAddressSuggestions] = useState([]);
  const [showAddressSuggestions, setShowAddressSuggestions] = useState(false);
  const navigate = useNavigate();

  const handleShippingChange = async (e) => {
    const { name, value } = e.target;
    setShippingData({ ...shippingData, [name]: value });

    if (name === 'address' && value) {
      try {
        const { predictions } = await fetchPlaceSuggestions(value);
        setAddressSuggestions(predictions);
        setShowAddressSuggestions(true);
      } catch (err) {
        console.error('Error fetching address suggestions:', err);
      }
    } else {
      setAddressSuggestions([]);
      setShowAddressSuggestions(false);
    }
  };

  const handleSuggestionSelect = (selectedOption) => {
    const [addressName, city, state] = selectedOption.value.split(',');
    setShippingData({
      ...shippingData,
      address: addressName.trim(),
      city: city ? city.trim() : shippingData.city,
      state: state ? state.trim() : shippingData.state
    });
    setShowAddressSuggestions(false);
  };

  const handleShippingSubmit = (e) => {
    e.preventDefault();
    console.log('Shipping information submitted:', shippingData);
    navigate('/payment');
  };

  return (
    <div>
      <h2 className="checkout-title">SHIPPING ADDRESS</h2>
      <form onSubmit={handleShippingSubmit} className="checkout-form">
        <div className="name-container">
          <div className="form-group">
            <label className="form-label">First Name</label>
            <Input
              name="firstName"
              value={shippingData.firstName}
              onChange={handleShippingChange}
              className="form-input form-input-left"
              required
            />
          </div>
          <div className="form-group">
            <label className="form-label">Last Name</label>
            <Input
              name="lastName"
              value={shippingData.lastName}
              onChange={handleShippingChange}
              className="form-input form-input-right"
              required
            />
          </div>
        </div>
        <div className="form-group">
          <label className="form-label">Address</label>
          <Input
            name="address"
            value={shippingData.address}
            onChange={handleShippingChange}
            className="form-input"
            autoComplete="off"
            onFocus={() => showAddressSuggestions && setShowAddressSuggestions(true)}
          />
          <Dropdown
            suggestions={addressSuggestions}
            onSelect={handleSuggestionSelect}
            showSuggestions={showAddressSuggestions}
            setShowSuggestions={setShowAddressSuggestions}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Apartment, Suite, Etc. (Optional)</label>
          <Input
            name="apartment"
            value={shippingData.apartment}
            onChange={handleShippingChange}
            className="form-input"
          />
        </div>
        <div className="name-container">
          <div className="form-group">
            <label className="form-label">City</label>
            <Input
              name="city"
              value={shippingData.city}
              onChange={handleShippingChange}
              className="form-input form-input-left"
              autoComplete="off"
            />
          </div>
          <div className="form-group">
            <label className="form-label">ZIP Code</label>
            <Input
              name="postalCode"
              value={shippingData.postalCode}
              onChange={handleShippingChange}
              className="form-input form-input-right"
              required
            />
          </div>
        </div>
        <div className="form-group">
          <label className="form-label">State</label>
          <Input
            name="state"
            value={shippingData.state}
            onChange={handleShippingChange}
            className="form-input"
            autoComplete="off"
          />
        </div>
        <h2 className="checkout-title">DELIVERY OPTIONS</h2>
        <Button type="submit" className="submit-button">Continue to Payment</Button>
      </form>
    </div>
  );
};

export default ShippingInformationForm;

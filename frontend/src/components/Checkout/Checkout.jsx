import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Input, Button, Icon } from 'semantic-ui-react';
import Breadcrumbs from '../Breadcrumbs';
import { fetchPlaceSuggestions } from '../../api/googleApi';
import Dropdown from '../Dropdown';
import './Checkout.css';

const Checkout = () => {
  const cartItems = useSelector((state) => state.shop.cart);
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn); // Access authentication state

  const [formData, setFormData] = useState({
    email: '',
    phoneNumber: '',
    firstName: '',
    lastName: '',
    address: '',
    apartment: '',
    city: '',
    state: '',
    postalCode: '',
    cardNumber: '',
    expiryDate: '',
    cvv: ''
  });
  const [addressSuggestions, setAddressSuggestions] = useState([]);
  const [showAddressSuggestions, setShowAddressSuggestions] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    console.log(`User is ${isLoggedIn ? 'logged in' : 'not logged in'}`);
  }, [isLoggedIn]); // Log when the component mounts or when the login state changes

  const handleInputChange = async (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });

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
    setFormData({
      ...formData,
      address: addressName.trim(),
      city: city ? city.trim() : formData.city,
      state: state ? state.trim() : formData.state
    });
    setShowAddressSuggestions(false);
  };

  const handleContactSubmit = (e) => {
    e.preventDefault();
    console.log('Contact information submitted:', formData);
  };

  const handleShippingSubmit = (e) => {
    e.preventDefault();
    console.log('Shipping information submitted:', formData);
    navigate('/payment');
  };

  const calculateTotals = () => {
    const TAX_RATE = 0.08;
    const SHIPPING_COST = 5.00;
    let totalPrice = 0;
    cartItems.forEach((item) => {
      totalPrice += item.price || 0;
    });

    const tax = totalPrice * TAX_RATE;
    const totalWithTax = totalPrice + tax + SHIPPING_COST;
    return { totalPrice, tax, totalWithTax, shipping: SHIPPING_COST };
  };

  const { totalPrice, tax, totalWithTax, shipping } = calculateTotals();

  const handleBackToCart = () => {
    navigate('/cart');
  };

  return (
    <div className="checkout-page">
      <Breadcrumbs />
      <Button className="back-to-cart-button" onClick={handleBackToCart}>
        <Icon name='arrow left' />
        Back to Cart
      </Button>
      <h2 className="checkout-title">CONTACT INFORMATION</h2>
      <form onSubmit={handleContactSubmit} className="checkout-form">
        {error && <div className="error-message">{error}</div>}
        <div className="form-group">
          <label className="form-label">Email Address</label>
          <Input
            name="email"
            value={formData.email}
            onChange={handleInputChange}
            className="form-input"
            autoComplete="off"
            required
          />
        </div>
        <div className="form-group">
          <label className="form-label">Phone Number</label>
          <Input
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={handleInputChange}
            className="form-input"
            required
          />
        </div>
      </form>

      <h2 className="checkout-title">SHIPPING ADDRESS</h2>
      <form onSubmit={handleShippingSubmit} className="checkout-form">
        <div className="name-container">
          <div className="form-group">
            <label className="form-label">First Name</label>
            <Input
              name="firstName"
              value={formData.firstName}
              onChange={handleInputChange}
              className="form-input form-input-left"
              required
            />
          </div>
          <div className="form-group">
            <label className="form-label">Last Name</label>
            <Input
              name="lastName"
              value={formData.lastName}
              onChange={handleInputChange}
              className="form-input form-input-right"
              required
            />
          </div>
        </div>
        <div className="form-group">
          <label className="form-label">Address</label>
          <Input
            name="address"
            value={formData.address}
            onChange={handleInputChange}
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
            value={formData.apartment}
            onChange={handleInputChange}
            className="form-input"
          />
        </div>
        <div className="name-container">
          <div className="form-group">
            <label className="form-label">City</label>
            <Input
              name="city"
              value={formData.city}
              onChange={handleInputChange}
              className="form-input-left"
              autoComplete="off"
            />
          </div>
          <div className="form-group">
            <label className="form-label">ZIP Code</label>
            <Input
              name="postalCode"
              value={formData.postalCode}
              onChange={handleInputChange}
              className="form-input-right"
              required
            />
          </div>
        </div>
        <div className="form-group">
          <label className="form-label">State</label>
          <Input
            name="state"
            value={formData.state}
            onChange={handleInputChange}
            className="form-input"
            autoComplete="off"
          />
        </div>
        <h2 className="checkout-title">DELIVERY OPTIONS</h2>
        <button type="submit" className="submit-button">Continue to Payment</button>
      </form>

      <div className="order-summary">
        <h3>Order Summary</h3>
        <div className="summary-item">
          <span>Total Price:</span>
          <span>${totalPrice.toFixed(2)}</span>
        </div>
        <div className="summary-item">
          <span>Tax (8%):</span>
          <span>${tax.toFixed(2)}</span>
        </div>
        <div className="summary-item">
          <span>Shipping:</span>
          <span>${shipping.toFixed(2)}</span>
        </div>
        <div className="summary-item total">
          <span>Total:</span>
          <span>${totalWithTax.toFixed(2)}</span>
        </div>
      </div>
    </div>
  );
};

export default Checkout;

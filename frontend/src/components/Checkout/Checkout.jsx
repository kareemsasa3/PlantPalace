import React from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import Breadcrumbs from '../Breadcrumbs';
import './Checkout.css'; // Import the CSS file for Checkout styling

const Checkout = () => {
  const cartItems = useSelector((state) => state.shop.cart);
  const [formData, setFormData] = React.useState({
    email: '',
    phoneNumber: '',
    firstName: '',
    lastName: '',
    address: '',
    apartment: '',
    city: '',
    postalCode: '',
    country: '',
    cardNumber: '',
    expiryDate: '',
    cvv: ''
  });
  const [error, setError] = React.useState('');
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleContactSubmit = (e) => {
    e.preventDefault();
    // Add validation logic for the contact form here
    console.log('Contact information submitted:', formData);
  };

  const handleShippingSubmit = (e) => {
    e.preventDefault();
    // Add validation logic for the shipping form here
    console.log('Shipping information submitted:', formData);
    // Navigate to payment form
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

  return (
    <div className="checkout-page">
      <Breadcrumbs />
      <h2 className="checkout-title">CONTACT INFORMATION</h2>
      <form onSubmit={handleContactSubmit} className="checkout-form">
        {error && <div className="error-message">{error}</div>}
        <div className="form-group">
          <label className="form-label">Email Address</label>
          <input
            name="email"
            value={formData.email}
            onChange={handleInputChange}
            className="form-input"
            required
          />
        </div>
        <div className="form-group">
          <label className="form-label">Phone Number</label>
          <input
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
            <input
              name="firstName"
              value={formData.firstName}
              onChange={handleInputChange}
              className="form-input"
              required
            />
          </div>
          <div className="form-group">
            <label className="form-label">Last Name</label>
            <input
              name="lastName"
              value={formData.lastName}
              onChange={handleInputChange}
              className="form-input"
              required
            />
          </div>
        </div>
        <div className="form-group">
          <label className="form-label">Address</label>
          <input
            name="address"
            value={formData.address}
            onChange={handleInputChange}
            className="form-input"
            required
          />
        </div>
        <div className="form-group">
          <label className="form-label">Apartment, Suite, Etc. (Optional)</label>
          <input
            name="apartment"
            value={formData.apartment}
            onChange={handleInputChange}
            className="form-input"
          />
        </div>
        <div className='name-container'>
          <div className="form-group">
            <label className="form-label">City</label>
            <input
              name="city"
              value={formData.city}
              onChange={handleInputChange}
              className="form-input"
              required
            />
          </div>
          <div className="form-group">
            <label className="form-label">State</label>
            <input
              name="state"
              value={formData.state}
              onChange={handleInputChange}
              className="form-input"
              required
            />
          </div>
        </div>
        <div className='name-container'>
          <div className="form-group">
            <label className="form-label">ZIP Code</label>
            <input
              name="postalCode"
              value={formData.postalCode}
              onChange={handleInputChange}
              className="form-input"
              required
            />
          </div>
          <div className="form-group">
          <label className="form-label">Country</label>
          <input
            name="country"
            value={formData.country}
            onChange={handleInputChange}
            className="form-input"
            required
          />
        </div>
        </div>
        
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

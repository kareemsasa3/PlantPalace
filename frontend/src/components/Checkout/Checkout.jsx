// src/components/Checkout/Checkout.js

import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import { Button, Form, Message } from 'semantic-ui-react';
import './Checkout.css'; // Import the CSS file for Checkout styling
import { useNavigate } from 'react-router-dom';

const Checkout = () => {
  const cartItems = useSelector((state) => state.shop.cart);
  const [formData, setFormData] = useState({
    name: '',
    address: '',
    city: '',
    postalCode: '',
    cardNumber: '',
    expiryDate: '',
    cvv: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      // Simulate payment processing
      console.log('Payment processed successfully:', formData);
      navigate('/confirmation');
    }
  };

  const validateForm = () => {
    const { name, address, city, postalCode, cardNumber, expiryDate, cvv } = formData;
    if (!name || !address || !city || !postalCode || !cardNumber || !expiryDate || !cvv) {
      setError('Please fill in all fields.');
      return false;
    }
    if (cardNumber.length !== 16 || cvv.length !== 3) {
      setError('Invalid card details.');
      return false;
    }
    setError('');
    return true;
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
      <h2>Checkout</h2>
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
      <Form onSubmit={handleSubmit} className="checkout-form">
        {error && <Message negative>{error}</Message>}
        <Form.Field>
          <label>Name</label>
          <input
            name="name"
            placeholder="Full Name"
            value={formData.name}
            onChange={handleInputChange}
            required
          />
        </Form.Field>
        <Form.Field>
          <label>Address</label>
          <input
            name="address"
            placeholder="Street Address"
            value={formData.address}
            onChange={handleInputChange}
            required
          />
        </Form.Field>
        <Form.Field>
          <label>City</label>
          <input
            name="city"
            placeholder="City"
            value={formData.city}
            onChange={handleInputChange}
            required
          />
        </Form.Field>
        <Form.Field>
          <label>Postal Code</label>
          <input
            name="postalCode"
            placeholder="Postal Code"
            value={formData.postalCode}
            onChange={handleInputChange}
            required
          />
        </Form.Field>
        <Form.Field>
          <label>Card Number</label>
          <input
            name="cardNumber"
            placeholder="Card Number"
            value={formData.cardNumber}
            onChange={handleInputChange}
            maxLength="16"
            required
          />
        </Form.Field>
        <Form.Field>
          <label>Expiry Date</label>
          <input
            name="expiryDate"
            placeholder="MM/YY"
            value={formData.expiryDate}
            onChange={handleInputChange}
            required
          />
        </Form.Field>
        <Form.Field>
          <label>CVV</label>
          <input
            name="cvv"
            placeholder="CVV"
            value={formData.cvv}
            onChange={handleInputChange}
            maxLength="3"
            required
          />
        </Form.Field>
        <Button primary type="submit">
          Complete Purchase
        </Button>
      </Form>
    </div>
  );
};

export default Checkout;

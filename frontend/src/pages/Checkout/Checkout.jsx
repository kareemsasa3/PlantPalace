import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { Button, Icon } from 'semantic-ui-react';
import Breadcrumbs from '../../components/Breadcrumbs';
import CartSummary from '../../components/CartSummary';
import CartItems from '../../components/CartItems';
import './Checkout.css';
import ContactInformationForm from '../../components/ContactInformationForm';
import ShippingInformationForm from '../../components/ShippingInformationForm';

const Checkout = () => {
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn);
  const navigate = useNavigate();

  useEffect(() => {
    console.log(`User is ${isLoggedIn ? 'logged in' : 'not logged in'}`);
  }, [isLoggedIn]);

  const handleBackToCart = () => {
    navigate('/cart');
  };

  return (
    <div className="checkout-page">
      
      <div className='checkout-container'>
        <Breadcrumbs />
        <Button className="back-to-cart-button" onClick={handleBackToCart}>
          <Icon name='arrow left' />
          Back to Cart
        </Button>
        <ContactInformationForm />
        <ShippingInformationForm />
      </div>
      <div className='checkout cart-details'>
        <CartSummary />
        <h1 className='checkout-title'>Your Order</h1>
        <CartItems />
      </div>
    </div>
  );
};

export default Checkout;

import React from 'react';
import { Button, Icon } from 'semantic-ui-react';
import Login from '../Login';
import './NotLoggedInSection.css'; // Import the new CSS file for NotLoggedInSection styling
import { useNavigate } from 'react-router-dom';

const NotLoggedInSection = () => {

    const navigate = useNavigate();

    const handleGuestCheckout = () => {
        navigate('/checkout'); // Adjust the path to your checkout page if different
    };

    const handleBackToCart = () => {
        navigate('/cart'); // Adjust the path to your cart page if different
    };

    return (
        <div className='login-guest-container'>
        <div className="login-guest-options">
            <div className='back-button-container'>
            <Button className="back-to-cart-button" onClick={handleBackToCart}>
                <Icon name='arrow left' />
                Back to Cart
            </Button>
            </div>
            <div>
            <Login />
            </div>
        </div>
        <div className='new-customer-container'>
            <h1 className='new-customer-title'>NEW CUSTOMERS</h1>
            <p className='new-customer-description'>Don't want to create an account just yet?</p>
            <Button onClick={handleGuestCheckout} className='continue-guest-button'>CONTINUE AS GUEST</Button>
        </div>
        </div>
    );
};

export default NotLoggedInSection;

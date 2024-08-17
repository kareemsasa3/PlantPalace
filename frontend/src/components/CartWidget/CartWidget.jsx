import React from 'react';
import { useSelector } from 'react-redux';
import { Icon, Button } from 'semantic-ui-react';
import { useNavigate } from 'react-router-dom';
import './CartWidget.css';

const CartWidget = () => {
  const cartItems = useSelector((state) => state.shop.cart);
  const navigate = useNavigate();

  const handleCartClick = () => {
    navigate('/cart'); // Navigate to the Cart page
  };

  if (cartItems.length === 0) {
    return null;
  }

  return (
    <div className="cart-widget">
      <Button icon onClick={handleCartClick} className="cart-button">
        <Icon name="shopping cart" />
        <span className="cart-count">{cartItems.length}</span>
      </Button>
    </div>
  );
};

export default CartWidget;

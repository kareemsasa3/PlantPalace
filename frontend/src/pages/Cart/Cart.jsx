import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

import Breadcrumbs from '../../components/Breadcrumbs';
import CartItems from '../../components/CartItems';
import CartSummary from '../../components/CartSummary';

import './Cart.css';

const Cart = () => {
  const cartItems = useSelector((state) => state.shop.cart);
  const navigate = useNavigate();

  useEffect(() => {
    if (cartItems.length === 0) {
      navigate('/shop');
    }
  }, [cartItems, navigate]);

  return (
    <div className="cart-page">
      <Breadcrumbs />
      <h1 className="cart-title">MY CART</h1>
      <div className='cart-content'>
        <CartSummary />
        <CartItems />
      </div>
    </div>
  );
};

export default Cart;

// src/components/ProductCard/ProductCard.js

import React from 'react';
import { Link } from 'react-router-dom';
import { Heart } from 'phosphor-react';
import { Button } from 'semantic-ui-react';
import { useDispatch, useSelector } from 'react-redux';
import { addToWishlist, removeFromWishlist, addToCart, removeFromCart } from '../../redux/slices/shopSlice';
import './ProductCard.css';

const ProductCard = ({ product }) => {
  const dispatch = useDispatch();
  const wishlist = useSelector((state) => state.shop.wishlist);
  const cart = useSelector((state) => state.shop.cart);

  const { id, name, amount, image, price, type } = product;

  const isProductInWishlist = (productId) => wishlist.includes(productId);
  const isProductInCart = (productId) => cart.includes(productId);

  const handleWishlist = (e, productId) => {
    e.preventDefault();
    if (isProductInWishlist(productId)) {
      dispatch(removeFromWishlist(productId));
    } else {
      dispatch(addToWishlist(productId));
    }
  };

  const handleCart = (e, productId) => {
    e.preventDefault();
    if (isProductInCart(productId)) {
      dispatch(removeFromCart(productId));
    } else {
      dispatch(addToCart(productId));
    }
  };

  return (
    <div className="product-card">
      <Link to={`/products/${id}`}>
        <h2 className="product-name">{name}</h2>
        <img
          src={image || 'no_image_available.jpeg'}
          alt={name || 'No Name'}
          className="product-image"
        />
        <div className="product-description">
          <Button
            className="wishlist-button"
            onClick={(e) => handleWishlist(e, id)}
          >
            <Heart
              size={32}
              weight={isProductInWishlist(id) ? 'fill' : 'regular'}
            />
          </Button>
        </div>
        <p className="type">{type || 'N/A'}</p>
        <div className="product-info">
          <span className="amount">
            {amount || 'N/A'} {amount === 1 ? 'Gram' : 'Grams'}
          </span>
          <span className="price">$ {price || 'N/A'}</span>
        </div>
        <Button
          className={`add-to-cart-button ${isProductInCart(id) ? 'in-cart' : ''}`}
          onClick={(e) => handleCart(e, id)}
        >
          {isProductInCart(id) ? 'Remove from Cart' : 'Add to Cart'}
        </Button>
      </Link>
    </div>
  );
};

export default ProductCard;

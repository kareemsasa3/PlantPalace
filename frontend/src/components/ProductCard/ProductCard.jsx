import React from 'react';
import PropTypes from 'prop-types'; // Import PropTypes
import { Link } from 'react-router-dom';
import { Heart } from 'phosphor-react';
import { Button } from 'semantic-ui-react';
import { useDispatch, useSelector } from 'react-redux';
import { addToWishlist, removeFromWishlist, addToCart, removeFromCart, addToProducts } from '../../redux/slices/shopSlice';
import './ProductCard.css';

const ProductCard = ({ product }) => {
  const dispatch = useDispatch();
  const wishlist = useSelector((state) => state.shop.wishlist);
  const cart = useSelector((state) => state.shop.cart);

  const { id, name, amount, image, price, type } = product;

  const isProductInWishlist = (productId) => wishlist.includes(productId);
  const isProductInCart = (productId) => cart.some(item => item.productId === productId);

  const handleWishlist = (e, productId) => {
    e.preventDefault();
    if (isProductInWishlist(productId)) {
      dispatch(removeFromWishlist(productId));
    } else {
      dispatch(addToWishlist(productId));
    }
  };

  const handleCart = (e, product) => {
    e.preventDefault();
    const quantity = 1; // Default quantity to 1 when adding to cart

    if (isProductInCart(product.id)) {
      dispatch(removeFromCart({ productId: product.id })); // Adjust for quantity if necessary
    } else {
      dispatch(addToCart({ productId: product.id, quantity })); // Pass product ID and quantity
      dispatch(addToProducts(product)); // Add product details to products list
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
          onClick={(e) => handleCart(e, product)}
        >
          {isProductInCart(id) ? 'Remove from Cart' : 'Add to Cart'}
        </Button>
      </Link>
    </div>
  );
};

// Define prop types
ProductCard.propTypes = {
  product: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    amount: PropTypes.number,
    image: PropTypes.string,
    price: PropTypes.number,
    type: PropTypes.string,
  }).isRequired,
};

export default ProductCard;

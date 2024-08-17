import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { fetchProductById } from '../../api/fetchProducts'; // Adjust path as needed
import { addToCart, removeFromCart, addToWishlist, removeFromWishlist } from '../../redux/slices/shopSlice'; // Adjust path as needed
import { Heart } from 'phosphor-react';
import './Product.css';

const Product = () => {
  const { productId } = useParams();
  const dispatch = useDispatch();
  const wishlist = useSelector((state) => state.shop.wishlist);
  const cart = useSelector((state) => state.shop.cart);
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const numericProductId = parseInt(productId, 10); // Convert productId to a number

  const isWishlisted = wishlist.includes(numericProductId);
  const isInCart = cart.includes(numericProductId);

  useEffect(() => {
    const getProduct = async () => {
      try {
        const data = await fetchProductById(productId);
        setProduct(data);
      } catch (error) {
        setError('Failed to fetch product. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    getProduct();
  }, [productId]);

  const handleWishlist = () => {
    if (isWishlisted) {
      dispatch(removeFromWishlist(numericProductId));
    } else {
      dispatch(addToWishlist(numericProductId));
    }
  };

  const handleCart = () => {
    if (isInCart) {
      dispatch(removeFromCart(numericProductId));
    } else {
      dispatch(addToCart(numericProductId));
    }
  };

  if (loading) {
    return <div className="loading">Loading...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  if (!product) {
    return <div className="no-product">Product not found</div>;
  }

  return (
    <div className='product-container'>
      <h1 className='product-title'>{product.name}</h1>
      <img src={product.image || 'no_image_available.jpeg'} alt={product.name} className='product-img'/>
      <div className="product-details">
        <div className='product-desc'>
          <p>Price: ${product.price}</p>
          <p>Amount: {product.amount} {product.amount === 1 ? 'Gram' : 'Grams'}</p>
          <p>Category: {product.category}</p>
          <p>Type: {product.type}</p>
          <p>THC: {product.thc}%</p>
          <p>{product.description}</p>
          <p>Effects: {product.effects}</p>
          <p>Terpenes: {Object.entries(product.terpenes).map(([key, value]) => (
            <span key={key}>{key}: {value}% </span>
          ))}</p>
        </div>
        <button
          className={`add-to-cart-btn ${isInCart ? 'in-cart' : ''}`}
          onClick={handleCart}
        >
          {isInCart ? 'Remove from Cart' : 'Add to Cart'}
        </button>
        <button
          className={`wishlist-btn ${isWishlisted ? 'in-wishlist' : ''}`}
          onClick={handleWishlist}
        >
          <Heart size={32} weight={isWishlisted ? 'fill' : 'regular'} />
        </button>
      </div>
    </div>
  );
};

export default Product;

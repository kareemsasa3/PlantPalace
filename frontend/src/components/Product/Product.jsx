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

  useEffect(() => {
    const getProduct = async () => {
      try {
        const data = await fetchProductById(productId);
        setProduct(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    getProduct();
  }, [productId]);

  const handleWishlist = () => {
    if (wishlist.includes(productId)) {
      dispatch(removeFromWishlist(productId));
    } else {
      dispatch(addToWishlist(productId));
    }
  };

  const handleCart = () => {
    if (cart.includes(productId)) {
      dispatch(removeFromCart(productId));
    } else {
      dispatch(addToCart(productId));
    }
  };

  if (loading) {
    return <div className="loading">Loading...</div>;
  }

  if (error) {
    return <div className="error">Error: {error}</div>;
  }

  if (!product) {
    return <div className="no-product">Product not found</div>;
  }

  const isInWishlist = wishlist.includes(productId);
  const isInCart = cart.includes(productId);

  return (
    <div className="product-details">
      <button
        className={`wishlist-button ${isInWishlist ? 'in-wishlist' : ''}`}
        onClick={handleWishlist}
      >
        <Heart size={32} weight={isInWishlist ? 'fill' : 'regular'} />
      </button>
      <h1 className='product-title'>{product.name}</h1>
      <img src={product.image || 'no_image_available.jpeg'} alt={product.name} className='product-image'/>
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
      <button
        className={`add-to-cart-button ${isInCart ? 'in-cart' : ''}`}
        onClick={handleCart}
      >
        {isInCart ? 'Remove from Cart' : 'Add to Cart'}
      </button>
    </div>
  );
};

export default Product;

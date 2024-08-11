import React, { useState, useEffect } from 'react';
import { Grid } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import { Heart } from 'phosphor-react';
import { useDispatch, useSelector } from 'react-redux';
import {
  addToWishlist,
  removeFromWishlist,
  addToCart,
  removeFromCart,
} from '../../redux/slices/shopSlice';
import { fetchProducts } from '../../api/fetchProducts'; // Adjust the import path as needed
import './ProductList.css';
import LoadingScreen from '../../util/LoadingScreen';

const ProductList = () => {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const dispatch = useDispatch();
  const wishlist = useSelector((state) => state.shop.wishlist);
  const cart = useSelector((state) => state.shop.cart);

  // New state to track which product has an active animation
  const [animationTrigger, setAnimationTrigger] = useState(null);

  const isProductInWishlist = (productId) => wishlist.includes(productId);
  const isProductInCart = (productId) => cart.includes(productId);

  const handleWishlist = (e, productId) => {
    e.preventDefault();
    if (isProductInWishlist(productId)) {
      dispatch(removeFromWishlist(productId));
    } else {
      dispatch(addToWishlist(productId));
      // Trigger animation
      setAnimationTrigger(productId);
      // Reset animation after it completes
      setTimeout(() => setAnimationTrigger(null), 1000); // 1000ms corresponds to animation duration
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

  useEffect(() => {
    // Fetch products when the component mounts
    const loadProducts = async () => {
      try {
        const productData = await fetchProducts();
        setProducts(productData);
        setIsLoading(false);
      } catch (error) {
        setError(error);
        setIsLoading(false);
      }
    };

    loadProducts();
  }, []);

  if (isLoading) {
    return <LoadingScreen />;
  }

  if (error) {
    return <div className='error'>Error loading products: {error.message}</div>;
  }

  if (products.length === 0) {
    return <div className='no-products'>No products found.</div>;
  }

  return (
    <div className="product-list">
      <Grid columns={5} stackable doubling>
        {products.map((product) => {
          const { id, name, amount, image, price } = product;
          return (
            <Grid.Column key={id}>
              <Link to={`/products/${id}`}>
                <div className="product">
                  <img
                    src={image || 'no_image_available.jpeg'}
                    alt={name || 'No Name'}
                    className="product-image"
                  />
                  <div className="product-description">
                    <button
                      className="wishlist-button"
                      onClick={(e) => handleWishlist(e, id)}
                    >
                      <Heart
                        size={32}
                        weight={isProductInWishlist(id) ? 'fill' : 'regular'}
                      />
                    </button>
                    {animationTrigger === id && (
                      <div className="confetti-container">
                        {Array.from({ length: 12 }).map((_, i) => (
                          <div key={i} className={`confetti confetti-${i}`}></div>
                        ))}
                      </div>
                    )}
                  </div>
                  <div className="product-info">
                    <h2 className="product-name">{name}</h2>
                  </div>
                  <p>{amount || 'N/A'} Grams</p>
                  <p>$ {price || 'N/A'}</p>
                  <button
                    className="add-to-cart-button"
                    onClick={(e) => handleCart(e, id)}
                  >
                    {isProductInCart(id) ? 'Remove from Cart' : 'Add to Cart'}
                  </button>
                </div>
              </Link>
            </Grid.Column>
          );
        })}
      </Grid>
    </div>
  );
};

export default ProductList;

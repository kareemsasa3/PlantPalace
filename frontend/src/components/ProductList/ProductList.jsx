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
          const { id, name, description, imageUrl, price } = product;
          return (
            <Grid.Column key={id}>
              <Link to={`/products/${id}`}>
                <div className="product">
                  <img
                    src={imageUrl || 'no_image_available.jpeg'}
                    alt={name || 'No Name'}
                    className="product-image"
                  />
                  <div className="product-description">
                    <h3>{description}</h3>
                    <button
                      className="wishlist-button"
                      onClick={(e) => handleWishlist(e, id)}
                    >
                      <Heart
                        size={32}
                        weight={isProductInWishlist(id) ? 'fill' : 'regular'}
                      />
                    </button>
                  </div>
                  <div className="product-info">
                    <h2 className="product-name">{name}</h2>
                  </div>
                  <p>$ {price || 'N/A'}</p>
                  <button
                    className="cart-button"
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

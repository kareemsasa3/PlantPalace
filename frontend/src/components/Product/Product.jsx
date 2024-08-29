import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import LoadingScreen from '../../util/LoadingScreen';
import { fetchProductById } from '../../api/fetchProducts'; // Adjust path as needed
import { addToCart, removeFromCart, addToWishlist, removeFromWishlist } from '../../redux/slices/shopSlice'; // Adjust path as needed
import { Heart } from 'phosphor-react';
import { Grid, Button, Image, Container } from 'semantic-ui-react';
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
    console.log(product);
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
    return <LoadingScreen />;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  if (!product) {
    return <div className="no-product">Product not found</div>;
  }

  return (
    <Container>
      <Grid divided stackable className="product-container">
        <Grid.Row>
          <Grid.Column width={6}>
            <Image
              src={product.image || 'no_image_available.jpeg'}
              alt={`Image of ${product.name}`}
              className='product-img'
              rounded
            />
          </Grid.Column>
          <Grid.Column width={10}>
            <p className='product-title'>{product.name}</p>
            <p className='product-subtitle'>by {product.brand}</p>
            <div className='product-details'>
              <p>Price: ${product.price}</p>
              <p>Amount: {product.amount} {product.amount === 1 ? 'Gram' : 'Grams'}</p>
              <p>Category: {product.category}</p>
              <p>Type: {product.type}</p>
              <p>THC: {product.thc}%</p>
              <p>{product.description}</p>
              <p>Effects: {product.effects}</p>
              
            </div>
            <Button
              onClick={handleCart}
              className={`add-to-cart-btn ${isInCart ? 'in-cart' : ''}`}
              color={isInCart ? 'grey' : 'black'}
              fluid
            >
              {isInCart ? 'Remove from Cart' : 'Add to Cart'}
            </Button>
            <Button
              onClick={handleWishlist}
              icon
              className={`wishlist-btn ${isWishlisted ? 'in-wishlist' : ''}`}
              aria-label={isWishlisted ? 'Remove from wishlist' : 'Add to wishlist'}
              color={isWishlisted ? 'yellow' : 'grey'}
              circular
              fluid
            >
              <Heart size={32} weight={isWishlisted ? 'fill' : 'regular'} />
            </Button>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <div>
        <p className='product-title'>{product.name}</p>
        <p className='product-subtitle'>by {product.brand}</p>
        <div className='product-details'>
          <p>{Object.entries(product.terpenes).map(([key, value]) => (
            <span key={key}>{key}: {value}% </span>
          ))}</p>
        </div>
        
      </div>
    </Container>
  );
};

export default Product;

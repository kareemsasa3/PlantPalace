import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchProductById } from '../../api/fetchProducts';
import { Button, Icon } from 'semantic-ui-react';
import { removeFromCart } from '../../redux/slices/shopSlice'; // Import the removeFromCart action
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import './Cart.css'; // Import the CSS file for Cart styling

const TAX_RATE = 0.08;
const SHIPPING_COST = 5.00;

const Cart = () => {
  const [products, setProducts] = useState([]);
  const cartItems = useSelector((state) => state.shop.cart);
  const dispatch = useDispatch();
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const fetchedProducts = await Promise.all(
          cartItems.map(async (itemId) => {
            const product = await fetchProductById(itemId);
            return product;
          })
        );
        setProducts(fetchedProducts);
      } catch (error) {
        console.error('Error fetching cart products:', error);
      }
    };

    if (cartItems.length > 0) {
      fetchProducts();
    } else {
      setProducts([]);
      navigate('/shop'); // Redirect to /shop if the cart is empty
    }
  }, [cartItems, navigate]);

  const handleRemoveFromCart = (productId) => {
    dispatch(removeFromCart(productId));
  };

  const calculateTotals = () => {
    let totalPrice = 0;
    products.forEach((product) => {
      if (product.price) {
        totalPrice += product.price;
      }
    });

    const tax = totalPrice * TAX_RATE;
    const totalWithTax = totalPrice + tax;
    return { totalPrice, tax, totalWithTax, shipping: SHIPPING_COST };
  };

  const { totalPrice, tax, totalWithTax, shipping } = calculateTotals();

  if (cartItems.length === 0) {
    return <p>Your cart is empty.</p>;
  }

  return (
    <div className="cart-page">
      <h2>Your Cart</h2>
      <div className="cart-items">
        {products.map((product) => (
          <div key={product.id} className="cart-item">
            <div className="cart-item-details">
              <h3 className="item-name">{product.name}</h3>
              <p className="item-price">${product.price.toFixed(2)}</p>
              <p className="item-amount">{product.amount} Grams</p>
              <p className="item-type">{product.type}</p>
            </div>
            <img src={product.image} alt={product.name} className="item-image" />
            <Button 
              icon
              onClick={() => handleRemoveFromCart(product.id)}
              aria-label={`Remove ${product.name} from cart`}
              className='remove-button'
            >
              <Icon name="trash" />
            </Button>
          </div>
        ))}
      </div>

      <div className="cart-summary">
        <div className="summary-item">
          <span>Total Price:</span>
          <span>${totalPrice.toFixed(2)}</span>
        </div>
        <div className="summary-item">
          <span>Tax (8%):</span>
          <span>${tax.toFixed(2)}</span>
        </div>
        <div className="summary-item">
          <span>Shipping:</span>
          <span>${shipping.toFixed(2)}</span>
        </div>
        <div className="summary-item total">
          <span>Total:</span>
          <span>${(totalWithTax + shipping).toFixed(2)}</span>
        </div>
        <Button primary className="checkout-button">
            Proceed to Checkout
        </Button>
      </div>
    </div>
  );
};

export default Cart;

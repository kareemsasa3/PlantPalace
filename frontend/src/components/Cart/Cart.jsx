import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchProductById } from '../../api/fetchProducts';
import { Button, Icon, Dropdown, Segment } from 'semantic-ui-react';
import { removeFromCart, updateQuantity } from '../../redux/slices/shopSlice';
import { useNavigate } from 'react-router-dom';
import Breadcrumbs from '../Breadcrumbs';
import './Cart.css';

const TAX_RATE = 0.08;
const SHIPPING_COST = 5.00;

const Cart = () => {
  const [products, setProducts] = useState([]);
  const [localQuantities, setLocalQuantities] = useState({});
  const cartItems = useSelector((state) => state.shop.cart);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const fetchedProducts = await Promise.all(
          cartItems.map(async (item) => {
            const product = await fetchProductById(item.productId);
            return { ...product, quantity: item.quantity };
          })
        );
        setProducts(fetchedProducts);
        const quantities = fetchedProducts.reduce((acc, product) => {
          acc[product.id] = product.quantity;
          return acc;
        }, {});
        setLocalQuantities(quantities);
      } catch (error) {
        console.error('Error fetching cart products:', error);
      }
    };

    if (cartItems.length > 0) {
      fetchProducts();
    } else {
      setProducts([]);
      navigate('/shop');
    }
  }, [cartItems, navigate]);

  const handleRemoveFromCart = (productId) => {
    dispatch(removeFromCart(productId));
  };

  const handleQuantityChange = (productId, quantity) => {
    dispatch(updateQuantity({ productId, quantity }));
    setLocalQuantities(prevQuantities => ({
      ...prevQuantities,
      [productId]: quantity
    }));
  };

  // Recalculate totals whenever products or localQuantities change
  const calculateTotals = () => {
    let totalPrice = 0;
    products.forEach((product) => {
      if (product.price) {
        totalPrice += product.price * (localQuantities[product.id] || product.quantity);
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

  const handleCheckout = () => {
    navigate('/checkout');
  };

  return (
    <div className="cart-page">
      <Breadcrumbs />
      <h1 className="cart-title">MY CART</h1>
      <div className="cart-content">
        <div className="cart-items">
          {products.map((product) => (
            <Segment key={product.id} className="cart-item">
              <div className="cart-item-left">
                <img src={product.image} alt={product.name} className="item-image" />
                <div className='cart-item-details'>
                  <p className="item-brand">{product.brand}</p>
                  <p className="item-category">{product.category}</p>
                  <h3 className="item-name">{product.name}</h3>
                </div>
              </div>
              <div className="cart-item-middle">
                <Button
                  icon
                  onClick={() => handleRemoveFromCart(product.id)}
                  aria-label={`Remove ${product.name} from cart`}
                  className="remove-button"
                >
                  <Icon name="trash" />
                </Button>
                <input
                  type="number"
                  className="quantity-selector"
                  value={localQuantities[product.id] || product.quantity}
                  onChange={(e) => handleQuantityChange(product.id, parseInt(e.target.value, 10))}
                  min="1"
                />
              </div>
              <div className="cart-item-right">
                <p className="item-price">${(product.price * (localQuantities[product.id] || product.quantity)).toFixed(2)}</p>
              </div>
            </Segment>
          ))}
        </div>
        <div className="cart-summary">
          <h1 className="cart-summary-title">SUMMARY</h1>
          <div className="summary-item">
            <span className="item-left">Subtotal:</span>
            <span className="item-right">${totalPrice.toFixed(2)}</span>
          </div>
          <div className="summary-item">
            <span className="item-left">Shipping:</span>
            <span className="item-right">${shipping.toFixed(2)}</span>
          </div>
          <div className="summary-item">
            <span className="item-left">Sales Tax:</span>
            <span className="item-right">${tax.toFixed(2)}</span>
          </div>
          <div className="summary-item total">
            <span className="item-left">Estimated Total:</span>
            <span className="item-right">${(totalWithTax + shipping).toFixed(2)}</span>
          </div>
          <Button
            primary
            className="checkout-button"
            onClick={handleCheckout}
          >
            Proceed to Checkout
          </Button>
        </div>
      </div>
    </div>
  );
};

export default Cart;

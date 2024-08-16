import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { Icon, Button } from 'semantic-ui-react'; // Import Semantic UI components
import { fetchProductById } from '../../api/fetchProducts';
import './CartWidget.css'; // Import your custom CSS

const TAX_RATE = 0.08; // Example tax rate of 8%
const SHIPPING_COST = 5.00; // Flat shipping cost

const CartWidget = () => {
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [products, setProducts] = useState([]);
  const cartItems = useSelector((state) => state.shop.cart);

  // Toggle the cart visibility
  const handleCartClick = () => {
    setIsCartOpen(!isCartOpen);
  };

  // Handle the checkout process
  const handleCheckout = () => {
    console.log('Proceeding to checkout...');
    // Optionally clear the cart or navigate to a checkout page
    // dispatch(resetCart());
  };

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
    }
  }, [cartItems]);

  // Calculate total price, tax, and shipping
  const calculateTotals = () => {
    let totalPrice = 0;

    // Verify that products are properly loaded and have valid prices
    products.forEach((product) => {
      if (product.price) {
        totalPrice += product.price; // Price is for the given amount
      }
    });

    const tax = totalPrice * TAX_RATE;
    const totalWithTax = totalPrice + tax;
    return { totalPrice, tax, totalWithTax, shipping: SHIPPING_COST };
  };

  const { totalPrice, tax, totalWithTax, shipping } = calculateTotals();

  // Return null if the cart is empty, so the widget won't be displayed
  if (cartItems.length === 0) {
    return null;
  }

  return (
    <>
      {/* Cart Widget */}
      <div className="cart-widget">
        <Button icon onClick={handleCartClick} className="cart-button">
          <Icon name="shopping cart" />
          <span className="cart-count">{cartItems.length}</span>
        </Button>
      </div>

      {/* Cart Container */}
      <div className={`cart-container ${isCartOpen ? '' : 'hidden'}`}>
        <div className="overlay" onClick={handleCartClick}></div>
        <div className="cart-content">
          <h2>Your Cart</h2>
          <div className="cart-items">
            {products.length > 0 ? (
              products.map((product) => (
                <div key={product.id} className="cart-item">
                  <div className="cart-item" key={product.id}>
                    <div className="cart-item-details">
                        <h3 className="product-name">{product.name}</h3>
                        <p className="product-price">Price: ${product.price.toFixed(2)}</p>
                        <p className="product-amount">Amount: {product.amount} Grams</p>
                        <p className="product-type">Type: {product.type}</p>
                    </div>
                    <img src={product.image} alt={product.name} className="product-image" /> {/* Adjust the src as needed */}
                    </div>
                </div>
              ))
            ) : (
              <p>Your cart is empty.</p>
            )}
          </div>
          {products.length > 0 && (
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
            </div>
          )}
          <Button primary className="checkout-button" onClick={handleCheckout}>
            Proceed to Checkout
          </Button>
        </div>
      </div>
    </>
  );
};

export default CartWidget;

import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Icon, Segment } from 'semantic-ui-react';
import { removeFromCart, updateQuantity } from '../../redux/slices/shopSlice';
import './CartItems.css';

const CartItems = () => {
  const cartItems = useSelector((state) => state.shop.cart);
  const products = useSelector((state) => state.shop.products);
  const dispatch = useDispatch();

  const productMap = new Map(products.map(product => [product.id, product]));

  const handleRemoveFromCart = (productId) => {
    dispatch(removeFromCart(productId));
  };

  const handleQuantityChange = (productId, quantity) => {
    dispatch(updateQuantity({ productId, quantity }));
  };

  const getProduct = (productId) => {
    const product = productMap.get(productId);

    if (product) {
      return product;
    } else {
      return null;
    }
  };

  return (
    <div className="cart-items-container">
      <div className="cart-items">
        {cartItems.map((item) => {
          const product = getProduct(item.productId);
          if (!product) return null;
          return (
            <Segment key={product.id} className="cart-item">
                <img src={product.image} alt={product.name} className="item-image" />
                <div className='cart-item-details'>
                  <p className="item-brand">{product.brand}</p>
                  <p className="item-name">{product.name}</p>
                  <p className="item-category">{product.category}</p>
                  <p className='item-amount'>{product.amount} Grams</p>
                </div>
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
                  value={item.quantity}
                  onChange={(e) => handleQuantityChange(product.id, parseInt(e.target.value, 10))}
                  min="1"
                />
                <p className="item-price">${(product.price * item.quantity).toFixed(2)}</p>
            </Segment>
          );
        })}
      </div>
    </div>
  );
};

export default CartItems;

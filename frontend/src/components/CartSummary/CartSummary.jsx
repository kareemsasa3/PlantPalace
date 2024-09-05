import React, { useMemo } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import './CartSummary.css';

const TAX_RATE = 0.08;
const SHIPPING_COST = 5.00;

const CartSummary = () => {
    const navigate = useNavigate();
    const products = useSelector((state) => state.shop.products);
    const cart = useSelector((state) => state.shop.cart);

    const user = useMemo(() => JSON.parse(localStorage.getItem('user')), []);
    const isAuthenticated = Boolean(user);

    const handleCheckout = () => {
        if (isAuthenticated) {
            navigate('/checkout');
        } else {
            navigate('/checkout/login');
        }
    };

    const { totalPrice, tax, totalWithTax, shipping } = useMemo(() => {
        let totalPrice = 0;

        cart.forEach((item) => {
            const product = products.find(p => p.id === item.productId);
            if (product) {
                totalPrice += product.price * item.quantity;
            }
        });

        const tax = totalPrice * TAX_RATE;
        const totalWithTax = totalPrice + tax;
        return { totalPrice, tax, totalWithTax, shipping: SHIPPING_COST };
    }, [cart, products]);

    return (
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
            <button
                className="checkout-button"
                onClick={handleCheckout}
                aria-label="Proceed to Secure Checkout"
            >
                Secure Checkout
            </button>
        </div>
    );
};

export default CartSummary;
import React from 'react';
import PropTypes from 'prop-types';
import { List, Segment } from 'semantic-ui-react';
import './OrderHistory.css';

const OrderHistory = ({ orderHistory }) => {
    return (
        <div className="order-history">
            <h2>Order History</h2>
            {orderHistory && orderHistory.length > 0 ? (
                <List divided relaxed>
                    {orderHistory.map((order) => (
                        <List.Item key={order.id}>
                            <List.Content>
                                <List.Header>Order #{order.id} - Status: {order.status}</List.Header>
                                <List.Description>
                                    <p><strong>Order Received:</strong> {new Date(order.orderReceivedTimestamp).toLocaleString()}</p>
                                    <p><strong>Expected Delivery:</strong> {new Date(order.expectedDeliveryTimestamp).toLocaleString()}</p>
                                    <p><strong>Shipping Address:</strong> {`${order.shippingAddress.streetAddress}, ${order.shippingAddress.city}, ${order.shippingAddress.state}, ${order.shippingAddress.postalCode}`}</p>
                                    <p><strong>Billing Address:</strong> {`${order.billingAddress.streetAddress}, ${order.billingAddress.city}, ${order.billingAddress.state}, ${order.billingAddress.postalCode}`}</p>
                                    <p><strong>Total Amount:</strong> {order.priceSummary.totalAmount}</p>
                                    <p><strong>Tax:</strong> {order.priceSummary.tax}</p>
                                    <p><strong>Total Price:</strong> ${order.priceSummary.totalPrice}</p>
                                    <strong>Products:</strong>
                                    <ul>
                                        {order.productSummary.map((product) => (
                                            <li key={product.name}>
                                                {product.name} - {product.amount}g at ${product.price}
                                            </li>
                                        ))}
                                    </ul>
                                </List.Description>
                            </List.Content>
                        </List.Item>
                    ))}
                </List>
            ) : (
                <Segment>No previous orders found. Your orders will go here.</Segment>
            )}
        </div>
    );
};

// Define prop types for the OrderHistory component
OrderHistory.propTypes = {
    orderHistory: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.string.isRequired,
            status: PropTypes.string.isRequired,
            orderReceivedTimestamp: PropTypes.string.isRequired,
            expectedDeliveryTimestamp: PropTypes.string.isRequired,
            shippingAddress: PropTypes.shape({
                streetAddress: PropTypes.string.isRequired,
                city: PropTypes.string.isRequired,
                state: PropTypes.string.isRequired,
                postalCode: PropTypes.string.isRequired,
            }).isRequired,
            billingAddress: PropTypes.shape({
                streetAddress: PropTypes.string.isRequired,
                city: PropTypes.string.isRequired,
                state: PropTypes.string.isRequired,
                postalCode: PropTypes.string.isRequired,
            }).isRequired,
            priceSummary: PropTypes.shape({
                totalAmount: PropTypes.number.isRequired,
                tax: PropTypes.number.isRequired,
                totalPrice: PropTypes.number.isRequired,
            }).isRequired,
            productSummary: PropTypes.arrayOf(
                PropTypes.shape({
                    name: PropTypes.string.isRequired,
                    amount: PropTypes.number.isRequired,
                    price: PropTypes.number.isRequired,
                })
            ).isRequired,
        })
    ).isRequired,
};

export default OrderHistory;

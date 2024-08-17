import React from 'react';
import { List, Segment } from 'semantic-ui-react';
import './OrderHistory.css';

const OrderHistory = ({ orderHistory }) => {
    return (
        <div className="order-history">
            <h2>Order History</h2>
            {orderHistory && orderHistory.length > 0 ? (
                <List divided relaxed>
                    {orderHistory.map((order, index) => (
                        <List.Item key={index}>
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
                                        {order.productSummary.map((product, idx) => (
                                            <li key={idx}>
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

export default OrderHistory;

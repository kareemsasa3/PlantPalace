import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../api/authApi';
import { updateUser } from '../../api/userApi';
import { Button, Grid, Input, List, Segment } from 'semantic-ui-react';

import './Account.css';

const Account = () => {
    const navigate = useNavigate();
    const [isEditing, setIsEditing] = useState(false);
    const [user, setUser] = useState({});
    const [editUser, setEditUser] = useState(user);

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        setUser(storedUser);
        setEditUser(storedUser);
        console.log('User from local storage:', storedUser);
    }, []);

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    const handleEdit = () => {
        setIsEditing(true);
    };

    const handleCancel = () => {
        setIsEditing(false);
        setEditUser(user); // Reset the edited user to the original user data
    };

    const handleSave = async () => {
        try {
            const updatedUser = await updateUser(user.id, editUser);
            setUser(updatedUser);
            localStorage.setItem('user', JSON.stringify(updatedUser));
            setIsEditing(false);
        } catch (err) {
            console.error('Failed to update user:', err);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setEditUser({ ...editUser, [name]: value });
    };

    return (
        <div className="account-container">
            <h1>Account</h1>
            {isEditing ? (
                <div className="edit-buttons">
                    <Button onClick={handleCancel} className="cancel-btn">Cancel</Button>
                    <Button onClick={handleSave} className="save-btn">Save</Button>
                </div>
            ) : (
                <div className='user-buttons'>
                    <Button onClick={handleEdit} className="edit-btn">Edit</Button>
                    <Button onClick={handleLogout} className="logout-btn">Logout</Button>
                </div>
            )}
            <div className="user-info">
                <Grid>
                    <Grid.Row columns={2}>
                        <Grid.Column>
                            <div className="form-group">
                                <label>Username</label>
                                <Input
                                    type="text"
                                    name="username"
                                    value={editUser.username}
                                    onChange={handleChange}
                                    disabled={!isEditing}
                                />
                            </div>
                        </Grid.Column>
                        <Grid.Column>
                            <div className="form-group">
                                <label>Email</label>
                                <Input
                                    type="email"
                                    name="email"
                                    value={editUser.email}
                                    onChange={handleChange}
                                    disabled={!isEditing}
                                />
                            </div>
                        </Grid.Column>
                    </Grid.Row>
                    <Grid.Row columns={2}>
                        <Grid.Column>
                            <div className="form-group">
                                <label>First Name</label>
                                <Input
                                    type="text"
                                    name="firstName"
                                    value={editUser.firstName}
                                    onChange={handleChange}
                                    disabled={!isEditing}
                                />
                            </div>
                        </Grid.Column>
                        <Grid.Column>
                            <div className="form-group">
                                <label>Last Name</label>
                                <Input
                                    type="text"
                                    name="lastName"
                                    value={editUser.lastName}
                                    onChange={handleChange}
                                    disabled={!isEditing}
                                />
                            </div>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </div>
            <div className="order-history">
                <h2>Order History</h2>
                {user.orderHistory && user.orderHistory.length > 0 ? (
                    <List divided relaxed>
                        {user.orderHistory.map((order, index) => (
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
        </div>
    );
};

export default Account;

import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../api/authApi';
import { updateUser } from '../../api/userApi';
import { Button, Grid, Input } from 'semantic-ui-react';
import Wishlist from '../../components/Wishlist';
import OrderHistory from '../../components/OrderHistory';

import './Account.css';

const Account = () => {
    const navigate = useNavigate();
    const [isEditing, setIsEditing] = useState(false);
    const [user, setUser] = useState({
        username: '',
        email: '',
        firstName: '',
        lastName: '',
        orderHistory: [],
    });
    const [editUser, setEditUser] = useState(user);

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        if (!storedUser) {
            navigate('/login'); // Redirect to /shop if no user is found
            return;
        }
        setUser(storedUser);
        setEditUser(storedUser);
        console.log('User from local storage:', storedUser);
    }, [navigate]);

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    const handleEdit = () => {
        setIsEditing(true);
    };

    const handleCancel = () => {
        setIsEditing(false);
        setEditUser(user);
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
            <div className="user-info">
                
                <Grid stackable>
                    <Grid.Row columns={2}>
                        <Grid.Column>
                            <div className="form-group">
                                <label htmlFor="username">Username</label>
                                <Input
                                    id="username"
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
                                <label htmlFor="email">Email</label>
                                <Input
                                    id="email"
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
                                <label htmlFor="firstName">First Name</label>
                                <Input
                                    id="firstName"
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
                                <label htmlFor="lastName">Last Name</label>
                                <Input
                                    id="lastName"
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
            </div>
            <OrderHistory orderHistory={user.orderHistory} />
            <Wishlist />
        </div>
    );
};

export default Account;

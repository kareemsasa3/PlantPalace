import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../api/authApi';
import { updateUser } from '../../api/userApi';
import { Button, Grid, Input } from 'semantic-ui-react';
import { useSelector, useDispatch } from 'react-redux';
import { resetWishlist } from '../../redux/slices/shopSlice';
import { fetchProductById } from '../../api/fetchProducts';
import Wishlist from '../Wishlist';
import OrderHistory from '../OrderHistory';

import './Account.css';

const Account = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const [isEditing, setIsEditing] = useState(false);
    const [user, setUser] = useState({});
    const [editUser, setEditUser] = useState(user);
    const [products, setProducts] = useState([]);
    const wishlist = useSelector((state) => state.shop.wishlist);

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        setUser(storedUser);
        setEditUser(storedUser);
        console.log('User from local storage:', storedUser);
        console.log('wishlist products:', products);
    }, []);

    useEffect(() => {
        const fetchWishlistProducts = async () => {
            try {
                const productPromises = wishlist.map(id => fetchProductById(id));
                const productResponses = await Promise.all(productPromises);
                setProducts(productResponses);
            } catch (error) {
                console.error('Failed to fetch wishlist products:', error);
            }
        };

        if (wishlist.length > 0) {
            fetchWishlistProducts();
        }
    }, [wishlist]);

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

    const handleClearWishlist = () => {
        dispatch(resetWishlist());
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
            <OrderHistory orderHistory={user.orderHistory} />
            <Wishlist products={products} handleClearWishlist={handleClearWishlist} />
        </div>
    );
};

export default Account;

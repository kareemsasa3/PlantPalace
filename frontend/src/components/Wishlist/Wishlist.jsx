import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Segment, List } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import { resetWishlist } from '../../redux/slices/shopSlice';
import { fetchProductById } from '../../api/fetchProducts';
import './Wishlist.css';

const Wishlist = () => {
    const dispatch = useDispatch();
    const wishlist = useSelector((state) => state.shop.wishlist);
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const handleResetWishlist = () => {
        dispatch(resetWishlist());
    };

    useEffect(() => {
        const fetchWishlistProducts = async () => {
            setLoading(true);
            try {
                const productPromises = wishlist.map(id => fetchProductById(id));
                const productResponses = await Promise.all(productPromises);
                setProducts(productResponses);
            } catch (error) {
                console.error('Failed to fetch wishlist products:', error);
                setError('Failed to load wishlist products.');
            } finally {
                setLoading(false);
            }
        };

        if (wishlist.length > 0) {
            fetchWishlistProducts();
        } else {
            setProducts([]); // Clear products if wishlist is empty
            setLoading(false);
        }
    }, [wishlist]);

    if (loading) return <Segment>Loading wishlist...</Segment>;
    if (error) return <Segment>{error}</Segment>;

    return (
        <div className="wishlist-container">
            <h2>Wishlist</h2>
            {products.length > 0 ? (
                <div>
                    <List divided relaxed className="wishlist-list">
                        {products.map((product) => {
                            const { id, name, price, amount, effects, description, image } = product || {};

                            if (!id || !name || typeof price !== 'number') {
                                return null; // Skip rendering this item if key properties are missing
                            }

                            return (
                                <List.Item key={id} className="wishlist-item">
                                    <List.Content floated="right">
                                        <img 
                                            src={image} 
                                            alt={`Image of ${name}`} 
                                            className="product-img-wishlist" 
                                        />
                                    </List.Content>
                                    <List.Content>
                                        <List.Header as={Link} to={`/products/${id}`}>
                                            {name}
                                        </List.Header>
                                        <List.Description>
                                            <p><strong>Price:</strong> ${price.toFixed(2)}</p>
                                            <p><strong>Amount:</strong> {amount} grams</p>
                                            <p><strong>Effects:</strong> {effects}</p>
                                            <p><strong>Description:</strong> {description}</p>
                                        </List.Description>
                                    </List.Content>
                                </List.Item>
                            );
                        })}
                    </List>
                    <Button 
                        className="clear-wishlist-btn" 
                        onClick={handleResetWishlist}
                    >
                        Reset Wishlist
                    </Button>
                </div>
            ) : (
                <Segment className='no-items-segment'>No items in wishlist.</Segment>
            )}
        </div>
    );
};

export default Wishlist;

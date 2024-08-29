import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Segment, Grid } from 'semantic-ui-react';
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

    // Function to reset the wishlist in the Redux store
    const handleResetWishlist = () => {
        dispatch(resetWishlist());
    };

    // Fetch wishlist products when wishlist changes
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
        <div className="wishlist">
            <h2>Wishlist</h2>
            {products.length > 0 ? (
                <div>
                    <Grid stackable doubling columns={2} className="wishlist-grid">
                        {products.map((product) => {
                            const { id, name, price, amount, effects, description, image } = product || {};

                            if (!id || !name || typeof price !== 'number') {
                                return null; // Skip rendering this item if key properties are missing
                            }

                            return (
                                <Grid.Column 
                                    key={id} 
                                    mobile={16} 
                                    tablet={8} 
                                    computer={8} 
                                    className="wishlist-item"
                                >
                                    <Link to={`/products/${id}`}>
                                        <h3>{name}</h3>
                                        <p><strong>Price:</strong> ${price.toFixed(2)}</p>
                                        <p><strong>Amount:</strong> {amount} grams</p>
                                        <p><strong>Effects:</strong> {effects}</p>
                                        <p><strong>Description:</strong> {description}</p>
                                        <img 
                                            src={image} 
                                            alt={`Image of ${name}`} 
                                            className="product-img-wishlist" 
                                        />
                                    </Link>
                                </Grid.Column>
                            );
                        })}
                    </Grid>
                    <Button 
                        className="clear-wishlist-btn" 
                        onClick={handleResetWishlist}
                    >
                        Reset Wishlist
                    </Button>
                </div>
            ) : (
                <Segment>No items in wishlist.</Segment>
            )}
        </div>
    );
};

export default Wishlist;

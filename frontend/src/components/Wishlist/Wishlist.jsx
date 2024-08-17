import React from 'react';
import PropTypes from 'prop-types'; // Import PropTypes
import { Button, Segment } from 'semantic-ui-react';
import { Link } from 'react-router-dom';
import './Wishlist.css';

const Wishlist = ({ products, handleClearWishlist }) => {
    return (
        <div className="wishlist">
            <h2>Wishlist</h2>
            {products.length > 0 ? (
                <div>
                    <div className="wishlist-grid">
                        {products.map((product) => (
                            <Link 
                                key={product.id} 
                                to={`/products/${product.id}`} 
                                className="wishlist-item"
                            >
                                <h3>{product.name}</h3>
                                <p><strong>Price:</strong> ${product.price}</p>
                                <p><strong>Amount:</strong> {product.amount} grams</p>
                                <p><strong>Effects:</strong> {product.effects}</p>
                                <p><strong>Description:</strong> {product.description}</p>
                                <img src={product.image} alt={product.name} className='product-img-wishlist' />
                            </Link>
                        ))}
                    </div>
                    <Button className="clear-wishlist-btn" onClick={handleClearWishlist}>
                        Clear Wishlist
                    </Button>
                </div>
            ) : (
                <Segment>No items in wishlist.</Segment>
            )}
        </div>
    );
};

// Define prop types
Wishlist.propTypes = {
    products: PropTypes.arrayOf(PropTypes.shape({
        id: PropTypes.string.isRequired,
        name: PropTypes.string.isRequired,
        price: PropTypes.number.isRequired,
        amount: PropTypes.number.isRequired,
        effects: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        image: PropTypes.string.isRequired,
    })).isRequired,
    handleClearWishlist: PropTypes.func.isRequired,
};

export default Wishlist;

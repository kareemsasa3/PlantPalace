import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import ProductCard from '../ProductCard';
import './ProductList.css';
import LoadingScreen from '../../util/LoadingScreen';

const ProductList = ({ products = [], isLoading = false, error = null }) => {
  if (isLoading) {
    return <LoadingScreen />;
  }

  if (error) {
    return <div className="error">Error loading products: {error.message}</div>;
  }

  if (products.length === 0) {
    return <div className="no-products">No products found.</div>;
  }

  return (
    <div className="product-list">
      <Grid columns={4} stackable doubling>
        {products.map((product) => (
          <Grid.Column key={product.id.toString()}>
            <ProductCard product={{ ...product, id: product.id.toString() }} />
          </Grid.Column>
        ))}
      </Grid>
    </div>
  );
};

ProductList.propTypes = {
  products: PropTypes.array,
  isLoading: PropTypes.bool,
  error: PropTypes.object,
};

export default ProductList;
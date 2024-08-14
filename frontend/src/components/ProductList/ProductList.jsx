// src/components/ProductList/ProductList.js

import React from 'react';
import { Grid } from 'semantic-ui-react';
import ProductCard from '../ProductCard'; // Import the new ProductCard component
import './ProductList.css';
import LoadingScreen from '../../util/LoadingScreen';

const ProductList = ({ products, isLoading, error }) => {
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
      <Grid columns={6} stackable doubling>
        {products.map((product) => (
          <Grid.Column key={product.id}>
            <ProductCard product={product} />
          </Grid.Column>
        ))}
      </Grid>
    </div>
  );
};

export default ProductList;

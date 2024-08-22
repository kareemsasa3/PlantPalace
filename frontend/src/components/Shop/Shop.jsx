import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import './Shop.css';
import Breadcrumbs from '../Breadcrumbs';
import ProductList from '../ProductList';
import CartWidget from '../CartWidget';
import { fetchProducts } from '../../api/fetchProducts';

const Shop = () => {
  const { categoryName } = useParams(); // Get categoryName from URL params
  const location = useLocation(); // Access location state
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadProducts = async () => {
      try {
        let productData = location.state?.searchResults || [];

        if (productData.length === 0) {
          // Fetch products if no search results are provided
          productData = await fetchProducts();
        }

        if (categoryName) {
          // Filter products by category name if categoryName is present
          const filteredProducts = productData.filter(
            (product) => product.category.toLowerCase() === categoryName.toLowerCase()
          );
          setProducts(filteredProducts);
        } else {
          // Otherwise, load all products
          setProducts(productData);
        }
        setIsLoading(false);
      } catch (error) {
        setError(error.message || 'An error occurred');
        setIsLoading(false);
      }
    };

    loadProducts();
  }, [categoryName, location.state?.searchResults]); // Re-run effect when categoryName or searchResults changes

  return (
    <div className='shop-container'>
      <div className='shop-header'>
        <Breadcrumbs />
        <CartWidget />
      </div>
      <h1 className='products-title'>
        {categoryName ? `${categoryName}` : 'Products'}
      </h1>
      <ProductList products={products} isLoading={isLoading} error={error} />
    </div>
  );
};

export default Shop;

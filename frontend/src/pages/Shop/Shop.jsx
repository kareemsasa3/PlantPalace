import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import './Shop.css';
import Breadcrumbs from '../../components/Breadcrumbs';
import ProductList from '../../components/ProductList';
import { fetchCannabisProducts } from '../../api/fetchProducts';
import { resetCart } from '../../redux/slices/shopSlice';

const Shop = () => {
  const { categoryName } = useParams();
  const location = useLocation();
  const dispatch = useDispatch();
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadProducts = async () => {
      try {
        let productData = location.state?.searchResults || [];

        if (productData.length === 0) {
          productData = await fetchCannabisProducts();
        }
        setProducts(productData);
        setIsLoading(false);
      } catch (error) {
        setError({ message: error.message || 'An error occurred'});
        setIsLoading(false);
      }
    };

    loadProducts();
  }, [categoryName, location.state?.searchResults, dispatch]);

  const handleReset = () => {
    dispatch(resetCart());
  };

  products.forEach((product, index) => {
    console.log(`Product ${index + 1}:`, product);
  });

  return (
    <div className='shop-container'>
      <div className='shop-header'>
        <Breadcrumbs />
        <h1 className='products-title'>
          {categoryName ? `${categoryName}` : 'Products'}
        </h1>
      </div>
      <ProductList products={products} isLoading={isLoading} error={error} />
      <button onClick={handleReset}>Reset Cart</button>
    </div>
  );
};

export default Shop;

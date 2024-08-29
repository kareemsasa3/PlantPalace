import React, { useState, useEffect } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import './Shop.css';
import Breadcrumbs from '../Breadcrumbs';
import ProductList from '../ProductList';
import { fetchProducts } from '../../api/fetchProducts';
import { resetCart } from '../../redux/slices/shopSlice';

const Shop = () => {
  const { categoryName } = useParams();
  const location = useLocation();
  const dispatch = useDispatch();
  const [products, setProductsState] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadProducts = async () => {
      try {
        let productData = location.state?.searchResults || [];

        if (productData.length === 0) {
          productData = await fetchProducts();
        }

        if (categoryName) {
          const filteredProducts = productData.filter(
            (product) => product.category.toLowerCase() === categoryName.toLowerCase()
          );
          setProductsState(filteredProducts);
        } else {
          setProductsState(productData);
        }
        setIsLoading(false);
      } catch (error) {
        setError(error.message || 'An error occurred');
        setIsLoading(false);
      }
    };

    loadProducts();
  }, [categoryName, location.state?.searchResults, dispatch]);

  const handleReset = () => {
    dispatch(resetCart());
  };

  return (
    <div className='shop-container'>
      <div className='shop-header'>
        <Breadcrumbs />
      </div>
      <h1 className='products-title'>
        {categoryName ? `${categoryName}` : 'Products'}
      </h1>
      <ProductList products={products} isLoading={isLoading} error={error} />
      <button onClick={handleReset}>Reset Cart</button>
    </div>
  );
};

export default Shop;

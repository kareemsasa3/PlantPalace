import React from 'react';
import './Shop.css';
import Breadcrumbs from '../Breadcrumbs';
import ProductList from '../ProductList';

const Shop = () => {
    return (
        <>
            <header className='shop-header'>
                <Breadcrumbs />
            </header>
            <h1 className='products-title'>Featured</h1>
            <ProductList />
        </>
    );
};

export default Shop;

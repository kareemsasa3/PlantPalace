import React, { useRef } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Shop.css';
import Breadcrumbs from '../Breadcrumbs';

const categories = [
    { label: 'SHOP ALL', path: '/shop', image: '/shop.png', text: 'SHOP MED & RECREATIONAL MARIJUANA' },
    { label: 'FLOWER', path: '/categories/flower', image: '/flower.png', text: 'SHOP CANNABIS FLOWER' },
    { label: 'CONCENTRATES', path: '/categories/concentrates', image: '/concentrate.png', text: 'SHOP CANNABIS CONCENTRATES' },
    { label: 'EDIBLES', path: '/categories/edibles', image: '/edible.png', text: 'SHOP CANNABIS EDIBLES' },
    { label: 'PRE-ROLLS', path: '/categories/pre-rolls', image: '/pre-roll.png', text: 'SHOP CANNABIS PRE-ROLLS' },
    { label: 'VAPORIZERS', path: '/categories/vaporizers', image: '/vaporizer.png', text: 'SHOP CANNABIS VAPORIZERS' },
    { label: 'TOPICALS', path: '/categories/topicals', image: '/topical.png', text: 'SHOP CANNABIS TOPICALS' },
    { label: 'TINCTURES', path: '/categories/tinctures', image: '/tincture.png', text: 'SHOP CANNABIS TINCTURES' },
    { label: 'ACCESSORIES', path: '/categories/accessories', image: '/accessory.png', text: 'SHOP CANNABIS ACCESSORIES' }
];

const Shop = () => {
    const scrollerRef = useRef(null);
    const location = useLocation();

    // Find the current category based on the path
    const currentCategory = categories.find(category => category.path === location.pathname);

    const scrollLeft = () => {
        scrollerRef.current.scrollBy({ left: -200, behavior: 'smooth' });
    };

    const scrollRight = () => {
        scrollerRef.current.scrollBy({ left: 200, behavior: 'smooth' });
    };

    return (
        <>
            <header className='shop-header'>
                <Breadcrumbs />
                {currentCategory && <h1>{currentCategory.text}</h1>}
            </header>
            <div className="category-scroller-container">
                <h3>SHOP BY CATEGORY</h3>
                <div className="scroller-wrapper">
                    <button className="scroll-button left" onClick={scrollLeft}>◀</button>
                    <ul className="scroller" ref={scrollerRef}>
                        {categories.map(category => (
                            <li key={category.label} className="category-item">
                                <Link to={category.path}>
                                    <div className="category-content">
                                        <img src={category.image} alt={category.label} className="category-image" />
                                        <div className="category-name">{category.label}</div>
                                    </div>
                                </Link>
                            </li>
                        ))}
                    </ul>
                    <button className="scroll-button right" onClick={scrollRight}>▶</button>
                </div>
            </div>
        </>
    );
};

export default Shop;

import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import NavBar from '../NavBar';
import Menu from '../Menu';
import { searchProducts } from '../../api/fetchProducts'; // Adjust import as necessary

import './Header.css';

const Header = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSearchSubmit = async () => {
    try {
      const results = await searchProducts(searchTerm);
      navigate('/shop', { state: { searchResults: results } });
    } catch (error) {
      console.error('Error searching products:', error);
    }
  };

  return (
    <header>
      <div className="header-top">
        <div className="logo">
          <Link to="/">
            <h1>Plant Palace</h1>
          </Link>
        </div>
        <div className="search-bar">
          <input
            type="text"
            placeholder="Search"
            value={searchTerm}
            onChange={handleSearchChange}
          />
          <button type="button" onClick={handleSearchSubmit}>
            🔍
          </button>
        </div>
      </div>
      <div className="header-middle">
        <NavBar />
      </div>
      <div className='header-bottom'>
        <Menu />
      </div>
    </header>
  );
};

export default Header;

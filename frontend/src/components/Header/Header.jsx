import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

const Header = () => {
  return (
    <header className="header-container">
      <div className='header-left'>
        <Link to="/">
          <h1>Plant Palace</h1>
        </Link>
      </div>
      <div className='header-right'>
        <Link to="/shop">
          <h1>Explore</h1>
        </Link>
      </div>
    </header>
  );
};

export default Header;
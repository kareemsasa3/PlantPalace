import React from 'react';
import { Link } from 'react-router-dom';
import NavBar from '../NavBar';
import Menu from '../Menu';

import './Header.css';

const Header = () => {
  return (
    <header>
      <div className="header-top">
        <div className="logo">
          <Link to="/">
            <h1>Plant Palace</h1>
          </Link>
        </div>
        <div className="search-bar">
          <input type="text" placeholder="Search" />
          <button type="button">🔍</button>
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

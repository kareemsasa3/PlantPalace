import React from 'react';
import { Link } from 'react-router-dom';
import './NavBar.css';

const NavBar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <ul className="navbar-left">
          <li><Link to="/specials">SPECIALS</Link></li>
          <li><Link to="/rewards">REWARD PROGRAM</Link></li>
          <li className="dropdown-container">
            <Link to="/categories">
              CATEGORIES
              <span className="dropdown-icon">▼</span>
            </Link>
            <ul className="dropdown">
              <li><Link to="/categories/flower">Flower</Link></li>
              <li><Link to="/categories/concentrates">Concentrates</Link></li>
              <li><Link to="/categories/edibles">EDIBLES</Link></li>
              <li><Link to="/categories/pre-rolls">PRE-ROLLS</Link></li>
              <li><Link to="/categories/vaporizers">VAPORIZERS</Link></li>
              <li><Link to="/categories/topicals">TOPICALS</Link></li>
              <li><Link to="/categories/tinctures">TINCTURES</Link></li>
              <li><Link to="/categories/accessories">ACCESSORIES</Link></li>
            </ul>
          </li>
          <li className="dropdown-container">
            <Link to="/resources">
              RESOURCES
              <span className="dropdown-icon">▼</span>
            </Link>
            <ul className="dropdown">
              <li><Link to="/contact">CONTACT US</Link></li>
              <li><Link to="/more">MORE</Link></li>
            </ul>
          </li>
        </ul>
        <ul className="navbar-right">
          <li><Link to="/login">ACCOUNT</Link></li>
        </ul>
      </div>
    </nav>
  );
};

export default NavBar;

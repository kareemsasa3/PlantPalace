import React from 'react';
import { Link } from 'react-router-dom';
import './Menu.css'; // Create and style Menu in a separate CSS file

const Menu = ({ onMenuItemClick }) => {
  const handleClick = () => {
    if (onMenuItemClick) {
      onMenuItemClick();
    }
  };

  return (
    <nav className="menu">
      <ul>
        <li><Link to="/shop" onClick={handleClick}>SHOP ALL</Link></li>
        <li><Link to="/categories/flower" onClick={handleClick}>FLOWER</Link></li>
        <li><Link to="/categories/concentrates" onClick={handleClick}>CONCENTRATES</Link></li>
        <li><Link to="/categories/edibles" onClick={handleClick}>EDIBLES</Link></li>
        <li><Link to="/categories/pre-rolls" onClick={handleClick}>PRE-ROLLS</Link></li>
        <li><Link to="/categories/vaporizers" onClick={handleClick}>VAPORIZERS</Link></li>
        <li><Link to="/categories/topicals" onClick={handleClick}>TOPICALS</Link></li>
        <li><Link to="/categories/tinctures" onClick={handleClick}>TINCTURES</Link></li>
        <li><Link to="/categories/accessories" onClick={handleClick}>ACCESSORIES</Link></li>
      </ul>
    </nav>
  );
};

export default Menu;

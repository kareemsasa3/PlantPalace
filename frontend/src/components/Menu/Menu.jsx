import React from 'react';
import { Link } from 'react-router-dom';
import './Menu.css'; // Create and style Menu in a separate CSS file

const Menu = () => {
  return (
    <nav className="menu">
      <ul>
        <li><Link to="/shop">SHOP ALL</Link></li>
        <li><Link to="/categories/flower">FLOWER</Link></li>
        <li><Link to="/categories/concentrates">CONCENTRATES</Link></li>
        <li><Link to="/categories/edibles">EDIBLES</Link></li>
        <li><Link to="/categories/pre-rolls">PRE-ROLLS</Link></li>
        <li><Link to="/categories/vaporizers">VAPORIZERS</Link></li>
        <li><Link to="/categories/topicals">TOPICALS</Link></li>
        <li><Link to="/categories/tinctures">TINCTURES</Link></li>
        <li><Link to="/categories/accessories">ACCESSORIES</Link></li>
      </ul>
    </nav>
  );
};

export default Menu;

import React from 'react';
import { Link } from 'react-router-dom';
import './Menu.css'; // Create and style Menu in a separate CSS file

const Menu = () => {
  return (
    <nav className="menu">
      <ul>
        <li><Link to="/shop">SHOP ALL</Link></li>
        <li><Link to="/shop/flower">FLOWER</Link></li>
        <li><Link to="/shop/concentrates">CONCENTRATES</Link></li>
        <li><Link to="/shop/edibles">EDIBLES</Link></li>
        <li><Link to="/shop/pre-rolls">PRE-ROLLS</Link></li>
        <li><Link to="/shop/vaporizers">VAPORIZERS</Link></li>
        <li><Link to="/shop/topicals">TOPICALS</Link></li>
        <li><Link to="/shop/tinctures">TINCTURES</Link></li>
        <li><Link to="/shop/accessories">ACCESSORIES</Link></li>
      </ul>
    </nav>
  );
};

export default Menu;

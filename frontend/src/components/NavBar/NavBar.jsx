import React from 'react';
import { Link } from 'react-router-dom';

import './NavBar.css';

const NavBar = () => {
  return (
    <nav>
      <ul>
        <li><Link to="/specials">SPECIALS</Link></li>
        <li><Link to="/rewards">REWARD PROGRAM</Link></li>
        <li>
          <Link to="/categories">CATEGORIES</Link>
          <ul className="dropdown">
            <li><Link to="/categories/flower">Flower</Link></li>
            <li><Link to="/categories/concentrates">Concentrates</Link></li>
          </ul>
        </li>
        <li>
          <Link to="/resources">RESOURCES</Link>
          <ul className="dropdown">
            <li><Link to="/resource1">Resource 1</Link></li>
            <li><Link to="/resource2">Resource 2</Link></li>
          </ul>
        </li>
      </ul>
    </nav>
  );
};

export default NavBar;

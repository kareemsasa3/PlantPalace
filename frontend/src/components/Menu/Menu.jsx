import React from 'react';
import { Link } from 'react-router-dom';
import './Menu.css';
import { categories } from '../../data/categoriesData';

const Menu = ({ onMenuItemClick }) => {
  const handleClick = () => {
    if (onMenuItemClick) {
      onMenuItemClick();
    }
  };

  return (
    <nav className="menu">
      <ul>
        {categories.map((category) => (
          <li key={category.name}>
            <Link to={category.path} onClick={handleClick}>
              {category.name}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
};

export default Menu;
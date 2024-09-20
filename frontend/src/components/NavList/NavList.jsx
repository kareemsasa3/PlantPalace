import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Icon } from 'semantic-ui-react';
import { categories } from '../../data/categoriesData';

import './NavList.css';

const NavList = ({ className, closeSidebar }) => {
  const [hoveredDropdown, setHoveredDropdown] = useState(null);

  const handleMouseEnter = (index) => {
    setHoveredDropdown(index);
  };

  const handleMouseLeave = () => {
    setHoveredDropdown(null);
  };

  return (
    <ul className={`navlist-container ${className}`}>

      <li className="dropdown-container">
        <div
          className={`dropdown-header ${className === 'sidebar' ? '' : 'navbar'}`}
          onMouseEnter={() => handleMouseEnter(1)}
          onMouseLeave={handleMouseLeave}
        >
          <Link to="/resources" onClick={closeSidebar}>
            <span className="dropdown-text">
              Resources
              <Icon name={hoveredDropdown === 1 ? 'angle right' : 'angle down'} className="icon" />
            </span>
          </Link>
        </div>
        <ul
          className="dropdown-list"
          onMouseEnter={() => handleMouseEnter(1)}
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/contact" onClick={closeSidebar}>Contact</Link></li>
          <li><Link to="/more" onClick={closeSidebar}>More</Link></li>
        </ul>
      </li>

      <li className="dropdown-container">
        <div
          className={`dropdown-header ${className === 'sidebar' ? '' : 'navbar'}`}
          onMouseEnter={() => handleMouseEnter(2)}
          onMouseLeave={handleMouseLeave}
        >
          <Link to="/education" onClick={closeSidebar}>
            <span className="dropdown-text">
              Education
              <Icon name={hoveredDropdown === 2 ? 'angle right' : 'angle down'} className="icon" />
            </span>
          </Link>
        </div>
        <ul
          className="dropdown-list"
          onMouseEnter={() => handleMouseEnter(2)}
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/strain-guide" onClick={closeSidebar}>Strain Guide</Link></li>
          <li><Link to="/terp-profiles" onClick={closeSidebar}>Terpene Profile</Link></li>
          <li><Link to="/effect-categories" onClick={closeSidebar}>Effect Categories</Link></li>
        </ul>
      </li>

      <li className="dropdown-container">
        <div
          className={`dropdown-header ${className === 'sidebar' ? '' : 'navbar'}`}
          onMouseEnter={() => handleMouseEnter(4)}
          onMouseLeave={handleMouseLeave}
        >
          <Link to="/news" onClick={closeSidebar}>
            <span className="dropdown-text">
              News
              <Icon name={hoveredDropdown === 4 ? 'angle right' : 'angle down'} className="icon" />
            </span>
          </Link>
        </div>
        <ul
          className="dropdown-list"
          onMouseEnter={() => handleMouseEnter(4)}
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/news/articles" onClick={closeSidebar}>Interesting Articles</Link></li>
          <li><Link to="/news/recent" onClick={closeSidebar}>Recent News</Link></li>
          <li><Link to="/news/events" onClick={closeSidebar}>Upcoming Events</Link></li>
        </ul>
      </li>

      <li className="dropdown-container">
        <div
          className={`dropdown-header ${className === 'sidebar' ? '' : 'navbar'}`}
          onMouseEnter={() => handleMouseEnter(3)}
          onMouseLeave={handleMouseLeave}
        >
          <Link to="/categories" onClick={closeSidebar}>
            <span className="dropdown-text">
              Categories
              <Icon name={hoveredDropdown === 3 ? 'angle right' : 'angle down'} className="icon" />
            </span>
          </Link>
        </div>
        <ul
          className="dropdown-list"
          onMouseEnter={() => handleMouseEnter(3)}
          onMouseLeave={handleMouseLeave}
        >
          {categories.map((category) => (
            <li key={category.name}>
              <Link to={category.path} onClick={closeSidebar}>
                {category.name}
              </Link>
            </li>
          ))}
        </ul>
      </li>
    </ul>
  );
};

export default NavList;
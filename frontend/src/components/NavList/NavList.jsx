import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Icon } from 'semantic-ui-react';
import './NavList.css';

const NavList = ({ className, closeSidebar }) => {
  // Separate state to track the dropdown index being hovered
  const [hoveredDropdown, setHoveredDropdown] = useState(null);

  const handleMouseEnter = (index) => {
    setHoveredDropdown(index); // Set the hovered dropdown index
  };

  const handleMouseLeave = () => {
    setHoveredDropdown(null); // Reset when leaving dropdown
  };

  return (
    <ul className={`navlist-container ${className}`}>
      {/* First Dropdown */}
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
          onMouseEnter={() => handleMouseEnter(1)} // Keep icon right when hovering the list
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/contact" onClick={closeSidebar}>Contact</Link></li>
          <li><Link to="/more" onClick={closeSidebar}>More</Link></li>
        </ul>
      </li>

      {/* Second Dropdown */}
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
          onMouseEnter={() => handleMouseEnter(2)} // Keep icon right when hovering the list
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/strain-guide" onClick={closeSidebar}>Strain Guide</Link></li>
          <li><Link to="/terp-profiles" onClick={closeSidebar}>Terpene Profile</Link></li>
          <li><Link to="/effect-categories" onClick={closeSidebar}>Effect Categories</Link></li>
        </ul>
      </li>

      {/* Third Dropdown */}
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
          onMouseEnter={() => handleMouseEnter(4)} // Keep icon right when hovering the list
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/news/articles" onClick={closeSidebar}>Interesting Articles</Link></li>
          <li><Link to="/news/recent" onClick={closeSidebar}>Recent News</Link></li>
          <li><Link to="/news/events" onClick={closeSidebar}>Upcoming Events</Link></li>
        </ul>
      </li>

      {/* Fourth Dropdown */}
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
          onMouseEnter={() => handleMouseEnter(3)} // Keep icon right when hovering the list
          onMouseLeave={handleMouseLeave}
        >
          <li><Link to="/shop" onClick={closeSidebar}>Shop All</Link></li>
          <li><Link to="/categories/flower" onClick={closeSidebar}>Flower</Link></li>
          <li><Link to="/categories/concentrates" onClick={closeSidebar}>Concentrates</Link></li>
          <li><Link to="/categories/edibles" onClick={closeSidebar}>Edibles</Link></li>
          <li><Link to="/categories/pre-rolls" onClick={closeSidebar}>Pre-rolls</Link></li>
          <li><Link to="/categories/vaporizers" onClick={closeSidebar}>Vaporizers</Link></li>
          <li><Link to="/categories/topicals" onClick={closeSidebar}>Topicals</Link></li>
          <li><Link to="/categories/tinctures" onClick={closeSidebar}>Tinctures</Link></li>
          <li><Link to="/categories/accessories" onClick={closeSidebar}>Accessories</Link></li>
        </ul>
      </li>
    </ul>
  );
};

export default NavList;
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Icon } from 'semantic-ui-react';
import { useMediaQuery } from 'react-responsive';
import Sidebar from '../Sidebar';
import NavList from '../NavList';

import './NavBar.css';

const NavBar = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [sidebarOpen, setSidebarOpen] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      setIsLoggedIn(true);
    }
  }, []);

  const toggleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
  };

  const isMobile = useMediaQuery({ maxWidth: 1080 });

  return (
    <nav className="navbar">
      <div className="navbar-container">
        {isMobile ? (
          <div onClick={toggleSidebar}>
            <Icon name="sidebar" className='sidebar-icon' />
          </div>
        ) : (
          <NavList className="navbar"/>
        )}
        <ul className="navbar-right">
          <li>
            <Link to="/cart">
              <Icon name="shopping cart" className='cart-icon' />
            </Link>
          </li>
          <li>
            <Link to={isLoggedIn ? "/account" : "/login"}>
              <Icon name="user" className='user-icon' />
            </Link>
          </li>
        </ul>
      </div>
      
      {isMobile && <Sidebar sidebarOpen={sidebarOpen} toggleSidebar={toggleSidebar} />}
    </nav>
  );
};

export default NavBar;
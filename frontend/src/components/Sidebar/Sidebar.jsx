import React from 'react';
import { Link } from 'react-router-dom';
import Menu from '../Menu';
import './Sidebar.css';

const Sidebar = ({ sidebarOpen, toggleSidebar }) => {
  return (
    <div className={`sidebar ${sidebarOpen ? 'active' : ''}`}>
      <div className="logo">
        <Link to="/" onClick={toggleSidebar}>
          <h1>Plant Palace</h1>
        </Link>
      </div>
      <div className="sidebar-icons">
        <Link to="/account" className="sidebar-icon" onClick={toggleSidebar}>
          <i className="user icon"></i>
        </Link>
        <Link to="/cart" className="sidebar-icon" onClick={toggleSidebar}>
          <i className="shopping cart icon"></i>
        </Link>
      </div>
      <button className="close-btn" onClick={toggleSidebar}>×</button>
      <Menu onMenuItemClick={toggleSidebar} />
    </div>
  );
};

export default Sidebar;

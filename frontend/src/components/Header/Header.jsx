import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import NavBar from '../NavBar';
import Sidebar from '../Sidebar'; // Import the Sidebar component
import Menu from '../Menu';
import { searchProducts } from '../../api/fetchProducts';
import './Header.css';
import { Button } from 'semantic-ui-react';

const Header = ({ onVisibilityChange }) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [showHeader, setShowHeader] = useState(true);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setSearchTerm('');
  }, [location]);

  useEffect(() => {
    let lastScrollTop = 0;

    const handleScroll = () => {
      const currentScrollTop = window.pageYOffset || document.documentElement.scrollTop;
      const isHeaderVisible = currentScrollTop <= lastScrollTop;
      setShowHeader(isHeaderVisible);
      onVisibilityChange(isHeaderVisible);
      lastScrollTop = currentScrollTop <= 0 ? 0 : currentScrollTop;
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, [onVisibilityChange]);

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSearchSubmit = async () => {
    try {
      const results = await searchProducts(searchTerm);
      navigate('/shop', { state: { searchResults: results } });
    } catch (error) {
      console.error('Error searching products:', error);
    }
  };

  const toggleSidebar = () => {
    setSidebarOpen(!sidebarOpen);
  };

  return (
    <header className={`header ${showHeader ? 'show' : 'hide'}`}>
      <div className="header-top">
        <div className="logo">
          <Link to="/">
            <h1>Plant Palace</h1>
          </Link>
        </div>
        <div className="search-bar">
          <input
            type="text"
            placeholder="Search"
            value={searchTerm}
            onChange={handleSearchChange}
          />
          <Button type="button" onClick={handleSearchSubmit}>
            🔍
          </Button>
        </div>
        <button className="sidebar-toggle" onClick={toggleSidebar}>
          ☰
        </button>
      </div>
      <div className="header-middle">
        <NavBar />
      </div>
      <div className="header-bottom">
        <Menu />
      </div>
      <Sidebar sidebarOpen={sidebarOpen} toggleSidebar={toggleSidebar} />
    </header>
  );
};

export default Header;

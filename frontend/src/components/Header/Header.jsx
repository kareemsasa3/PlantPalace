// src/components/Header.js
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
  const [lastScrollTop, setLastScrollTop] = useState(0); // Track the last scroll position
  const [isScrollingUp, setIsScrollingUp] = useState(false); // Track scroll direction
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setSearchTerm('');
  }, [location]);

  useEffect(() => {
    const handleScroll = () => {
      const currentScrollTop = window.pageYOffset || document.documentElement.scrollTop;
      const isScrollingUpNow = currentScrollTop < lastScrollTop;

      if (isScrollingUpNow !== isScrollingUp) {
        setIsScrollingUp(isScrollingUpNow);
      }

      if (isScrollingUpNow && !showHeader) {
        setShowHeader(true);
        onVisibilityChange(true);
      } else if (!isScrollingUpNow && currentScrollTop > 100 && showHeader) {
        setShowHeader(false);
        onVisibilityChange(false);
      }

      setLastScrollTop(currentScrollTop <= 0 ? 0 : currentScrollTop);
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, [isScrollingUp, lastScrollTop, onVisibilityChange, showHeader]);

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

import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import NavBar from '../NavBar';
import Menu from '../Menu';
import { searchProducts } from '../../api/fetchProducts';
import './Header.css';
import { Button } from 'semantic-ui-react';

const Header = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    setSearchTerm('');
  }, [location]);

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
    <header>
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
      <div className={`sidebar ${sidebarOpen ? 'active' : ''}`}>
        <div className="logo">
          <Link to="/">
            <h1>Plant Palace</h1>
          </Link>
        </div>
        <button className="close-btn" onClick={toggleSidebar}>×</button>
        <Menu onMenuItemClick={toggleSidebar} />
      </div>
    </header>
  );
};

export default Header;

import React from 'react';
import PropTypes from 'prop-types';
import { useMediaQuery } from 'react-responsive';
import NavBar from '../NavBar';
import Menu from '../Menu';
import './Layout.css';

const Layout = ({ children }) => {
  const isMobile = useMediaQuery({ maxWidth: 1080 });

  return (
    <div className="layout-container">
      <NavBar />
      {!isMobile && <Menu />}
      <main>
        {children}
      </main>
    </div>
  );
};

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};

export default Layout;
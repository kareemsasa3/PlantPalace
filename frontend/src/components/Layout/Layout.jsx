// src/components/Layout.js
import React from 'react';
import Header from '../Header';
import Footer from '../Footer';
import './Layout.css'; // Create this file for specific layout styling if needed

const Layout = ({ children }) => {
  return (
    <div className="App">
      <Header className="App-header" />
      <main className="main-content">
        {children}
      </main>
      <Footer className="App-footer" />
    </div>
  );
};

export default Layout;

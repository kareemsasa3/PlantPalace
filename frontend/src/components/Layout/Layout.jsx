// src/components/Layout.js
import React, { useState } from 'react';
import Header from '../Header';
import Footer from '../Footer';
import './Layout.css';

const Layout = ({ children }) => {
  const [isHeaderVisible, setIsHeaderVisible] = useState(true);

  const handleHeaderVisibility = (isVisible) => {
    setIsHeaderVisible(!isVisible);
  };

  return (
    <div className="App">
      <Header className="App-header" onVisibilityChange={handleHeaderVisibility} />
      <main className={`main-content ${isHeaderVisible ? 'header-visible' : 'header-hidden'}`}>
        {children}
      </main>
      <Footer className="App-footer" />
    </div>
  );
};

export default Layout;

// src/App.js
import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Home from './components/Home';
import Shop from './components/Shop';
import Product from './components/Product';
import Terms from './components/Terms/Terms';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Account from './components/Account';
import AgeVerification from './components/AgeVerification';
import Layout from './components/Layout'; // Import the Layout component

import './App.css';

function App() {
  const [isVerified, setIsVerified] = useState(false);

  const handleAgeVerification = () => {
    setIsVerified(true);
  };

  return (
    <div className="App">
      {!isVerified && <AgeVerification onConfirm={handleAgeVerification} />}
      {isVerified && (
        <Router>
          <Layout>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/shop" element={<Shop />} />
              <Route path="/categories/:categoryName" element={<Shop />} />
              <Route path="/products/:productId" element={<Product />} />
              <Route path="/contact" element={<div>Contact Page</div>} />
              <Route path="/more" element={<div>More Page</div>} />
              <Route path="/cart" element={<div>Cart Page</div>} />
              <Route path="/account" element={<Account />} />
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/terms" element={<Terms />} />
            </Routes>
          </Layout>
        </Router>
      )}
    </div>
  );
}

export default App;

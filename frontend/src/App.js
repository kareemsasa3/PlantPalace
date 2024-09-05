// src/App.js
import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Home from './pages/Home';
import Shop from './pages/Shop';
import Product from './pages/Product';
import Cart from './pages/Cart';
import Checkout from './pages/Checkout';
import Terms from './pages/Terms/Terms';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Account from './pages/Account';
import AgeVerification from './components/AgeVerification';
import Layout from './components/Layout'; // Import the Layout component

import './App.css';
import NotLoggedInSection from './components/NotLoggedInSection';

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
              <Route path="/cart" element={<Cart />} />
              <Route path="/checkout" element={<Checkout />} />
              <Route path="/checkout/login" element={<NotLoggedInSection />} />
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

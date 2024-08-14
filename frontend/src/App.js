import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Header from './components/Header';
import Home from './components/Home';
import Shop from './components/Shop';
import Product from './components/Product';  // Import the new Product component
import Footer from './components/Footer';
import Terms from './components/Terms/Terms';
import Login from './components/Login';
import SignUp from './components/SignUp';

import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
          <Route path="/categories/:categoryName" element={<Shop />} />
          <Route path="/products/:productId" element={<Product />} /> {/* Route for product details */}
          <Route path="/contact" element={<div>Contact Page</div>} /> {/* Replace with actual component */}
          <Route path="/more" element={<div>More Page</div>} /> {/* Replace with actual component */}
          <Route path="/cart" element={<div>Cart Page</div>} /> {/* Replace with actual component */}
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/terms" element={<Terms />} />
        </Routes>
        <Footer />
      </Router>
    </div>
  );
}

export default App;

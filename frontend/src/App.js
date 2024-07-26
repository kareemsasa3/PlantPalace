import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Header from './components/Header';
import Shop from './components/Shop';

import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/" />
          <Route path="/shop" element={<Shop />} />
          <Route path="/categories" />
          <Route path="/categories/flower" element={<Shop />} />
          <Route path="/categories/concentrates" element={<Shop />} />
          <Route path="/categories/edibles" element={<Shop />} />
          <Route path="/categories/pre-rolls" element={<Shop />} />
          <Route path="/categories/vaporizers" element={<Shop />} />
          <Route path="/categories/topicals" element={<Shop />} />
          <Route path="/categories/tinctures" element={<Shop />} />
          <Route path="/categories/accessories" element={<Shop />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Header from './components/Header';

import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/" />
          <Route path="/shop" />
          <Route path="/categories" />
          <Route path="/categories/flower" />
          <Route path="/categories/concentrates" />
          <Route path="/categories/edibles" />
          <Route path="/categories/pre-rolls" />
          <Route path="/categories/vaporizers" />
          <Route path="/categories/topicals" />
          <Route path="/categories/tinctures" />
          <Route path="/categories/accessories" />
        </Routes>
      </Router>
    </div>
  );
}

export default App;

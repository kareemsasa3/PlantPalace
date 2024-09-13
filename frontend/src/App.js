import React, { useState } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';

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
import RouteWrapper from './components/RouteWrapper';
import NotLoggedInSection from './components/NotLoggedInSection';
import Footer from './components/Footer';

import './App.css';
import Header from './components/Header';

function App() {
  const [isVerified, setIsVerified] = useState(false);

  const handleAgeVerification = () => {
    setIsVerified(true);
  };

  const routes = [
    { path: '/', element: <Home /> },
    { path: '/shop', element: <Shop /> },
    { path: '/categories/:categoryName', element: <Shop /> },
    { path: '/products/:productId', element: <Product /> },
    { path: '/contact', element: <div>Contact Page</div> },
    { path: '/more', element: <div>More Page</div> },
    { path: '/cart', element: <Cart /> },
    { path: '/checkout', element: <Checkout /> },
    { path: '/checkout/login', element: <NotLoggedInSection /> },
    { path: '/account', element: <Account /> },
    { path: '/login', element: <Login /> },
    { path: '/signup', element: <SignUp /> },
    { path: '/terms', element: <Terms /> },
  ];

  return (
    <div className="App">
      {!isVerified && <AgeVerification onConfirm={handleAgeVerification} />}
      {isVerified && (
        <Router>
          <Header />
          <RouteWrapper routes={routes} />
          <Footer />
        </Router>
      )}
    </div>
  );
}

export default App;
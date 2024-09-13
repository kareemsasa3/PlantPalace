import React from 'react';
import PropTypes from 'prop-types';
import { Route, Routes, useLocation } from 'react-router-dom';
import Layout from '../Layout';

const routePropType = PropTypes.shape({
  path: PropTypes.string.isRequired,
  element: PropTypes.element.isRequired
});

const RouteWrapper = ({ routes }) => {
  const location = useLocation();
  const layoutExcludedPaths = ['/', '/login', '/signup', '/checkout', '/checkout/login', '/cart'];

  const isExcluded = layoutExcludedPaths.includes(location.pathname);

  console.log(isExcluded);

  return isExcluded ? (
    <Routes>
      {routes.map((route) => (
        <Route key={route.path} path={route.path} element={route.element} />
      ))}
    </Routes>
  ) : (
    <Layout>
      <Routes>
        {routes.map((route) => (
          <Route key={route.path} path={route.path} element={route.element} />
        ))}
      </Routes>
    </Layout>
  );
};

RouteWrapper.propTypes = {
  routes: PropTypes.arrayOf(routePropType).isRequired
};

export default RouteWrapper;
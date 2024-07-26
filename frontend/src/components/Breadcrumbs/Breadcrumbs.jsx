import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import './Breadcrumbs.css';

// Function to generate breadcrumb items from the path
const generateBreadcrumbs = (pathname) => {
    const parts = pathname.split('/').filter(part => part); // Split path and remove empty parts
    return parts.map((part, index) => ({
        name: part.charAt(0).toUpperCase() + part.slice(1), // Capitalize first letter
        path: '/' + parts.slice(0, index + 1).join('/') // Build breadcrumb path
    }));
};

const Breadcrumbs = () => {
    const location = useLocation();
    const breadcrumbs = generateBreadcrumbs(location.pathname);

    return (
        <nav className="breadcrumbs">
            {breadcrumbs.map((breadcrumb, index) => (
                <span key={breadcrumb.path} className="breadcrumb-item">
                    <Link to={breadcrumb.path}>{breadcrumb.name}</Link>
                    {index < breadcrumbs.length - 1 && ' / '}
                </span>
            ))}
        </nav>
    );
};

export default Breadcrumbs;

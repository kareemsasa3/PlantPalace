import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import './Breadcrumbs.css';

// Function to generate breadcrumb items from the path
const generateBreadcrumbs = (pathname) => {
    const parts = pathname.split('/').filter(part => part); // Split path and remove empty parts

    // If the path is exactly "/shop", return only the "Shop" breadcrumb
    if (pathname === '/shop') {
        return [{ name: 'Shop', path: '/shop' }];
    }

    // Add "Shop" as the first breadcrumb and generate the rest
    const breadcrumbs = [
        { name: 'Shop', path: '/shop' },
        ...parts.map((part, index) => ({
            name: capitalizeName(part), // Capitalize first letter and handle multi-word names
            path: '/' + parts.slice(0, index + 1).join('/') // Build breadcrumb path
        }))
    ];

    return breadcrumbs;
};

// Function to capitalize breadcrumb names
const capitalizeName = (name) => {
    return name.split('-').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' '); // Handle hyphenated words
};

const Breadcrumbs = () => {
    const location = useLocation();
    const breadcrumbs = generateBreadcrumbs(location.pathname);

    return (
        <nav className="breadcrumbs" aria-label="Breadcrumb">
            {breadcrumbs.map((breadcrumb, index) => (
                <span key={breadcrumb.path} className="breadcrumb-item">
                    <Link to={breadcrumb.path}>{breadcrumb.name}</Link>
                    {index < breadcrumbs.length - 1 && <span className="breadcrumb-separator"> / </span>}
                </span>
            ))}
        </nav>
    );
};

export default Breadcrumbs;

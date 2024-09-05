import React from 'react';
import { useLocation, Link } from 'react-router-dom';
import './Breadcrumbs.css';

const generateBreadcrumbs = (pathname) => {
    const parts = pathname.split('/').filter(part => part);
    const normalizedPathname = pathname.replace(/\/$/, '');

    const capitalizeName = (name) => {
        return name.split('-').map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');
    };

    if (normalizedPathname === '/shop') {
        return [{ name: 'Shop', path: '/shop' }];
    }

    const breadcrumbs = [
        { name: 'Shop', path: '/shop' },
        ...parts.map((part, index) => ({
            name: capitalizeName(part),
            path: '/' + parts.slice(0, index + 1).join('/')
        }))
    ];

    return breadcrumbs;
};

const Breadcrumbs = () => {
    const location = useLocation();
    const breadcrumbs = generateBreadcrumbs(location.pathname);

    return (
        <nav className="breadcrumbs" aria-label="Breadcrumb">
            <ol>
                {breadcrumbs.map((breadcrumb, index) => (
                    <li key={breadcrumb.path} className="breadcrumb-item">
                        {index < breadcrumbs.length - 1 ? (
                            <>
                                <Link to={breadcrumb.path}>{breadcrumb.name}</Link>
                                <span className="breadcrumb-separator"> / </span>
                            </>
                        ) : (
                            <span aria-current="page">{breadcrumb.name}</span>
                        )}
                    </li>
                ))}
            </ol>
        </nav>
    );
};

export default Breadcrumbs;

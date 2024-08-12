import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import './Footer.css';

const Footer = () => {
    // State to track the current window width
    const [windowWidth, setWindowWidth] = useState(window.innerWidth);

    // Effect to update the window width on resize
    useEffect(() => {
        const handleResize = () => {
            setWindowWidth(window.innerWidth);
        };

        // Add event listener for window resize
        window.addEventListener('resize', handleResize);

        // Remove event listener on cleanup
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    return (
        <footer className='footer'>
            <p className='footer-text'>
                Cannabis and Marijuana are for use only by persons 21 years of age or older. Such use may be prohibited in your location. The statements on this website have not been evaluated by the FDA. Products sold or advertised on this website are not intended to diagnose, treat, cure, or prevent any disease.
            </p>
            <div className='footer-bottom'>
                <p className='footer-copyright'>
                    Copyright © 2024 Mr. Tastedeez - All Rights Reserved.
                </p>
                <Link to='/terms' className='footer-link'>
                    Terms & Conditions
                </Link>
            </div>
            <div className='footer-window-width'>
                <p>Current window width: {windowWidth}px</p>
            </div>
        </footer>
    );
};

export default Footer;

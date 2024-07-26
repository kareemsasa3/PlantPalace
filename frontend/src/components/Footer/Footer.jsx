import React from 'react';
import { Link } from 'react-router-dom';

import './Footer.css';

const Footer = () => {
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
        </footer>
    );
};

export default Footer;

import React from 'react';
import PropTypes from 'prop-types';
import { useMediaQuery } from 'react-responsive';
import './Home.css';

// Reusable Section Component
const Section = ({ title, description }) => (
    <div className='home-section'>
        <div className='section-text'>
            <h3 className='home-details'>{title}</h3>
            <p className='home-description'>{description}</p>
        </div>
    </div>
);

Section.propTypes = {
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
};

const getHomeContentClassName = (isDesktop, isTablet) => {
    if (isDesktop) {
        return 'desktop';
    } else if (isTablet) {
        return 'tablet';
    } else {
        return 'mobile';
    }
};

const Home = () => {
    // Define breakpoints
    const isDesktop = useMediaQuery({ minWidth: 1024 });
    const isTablet = useMediaQuery({ minWidth: 768, maxWidth: 1023 });
    const isMobile = useMediaQuery({ maxWidth: 767 });

    const homeContentClass = getHomeContentClassName(isDesktop, isTablet);

    let title;
    let subtitle;

    if (isMobile) {
        title = 'Cultivate Your Lifestyle';
        subtitle = 'Discover natural healing!';
    } else {
        title = 'Cultivate Your Lifestyle With Us';
        subtitle = 'Discover a natural healing approach that your mind will thank you for!';
    }

    return (
        <div className={`home-content ${homeContentClass}`}>
            <h1 className='home-title'>{title}</h1>
            <h3 className='home-subtitle'>{subtitle}</h3>
            <Section 
                title='Quality Compassionate Care'
                description='Our company is dedicated to providing high-quality, all-natural hemp products with a focus on compassion and convenience. We advocate for a homeopathic approach to healing and meticulously select terpene profiles to maximize benefits for the CBD endocrine system.'
            />
            <Section 
                title='Hemp & Healing'
                description='We offer a broad range of hemp products designed to enhance comfort and peace of mind. Our team is here to guide you through your options, ensuring you find the perfect product for your unique needs and lifestyle.'
            />
            <Section 
                title='Our Promise'
                description='We are committed to creating a one-of-a-kind experience through exceptional quality products and knowledgeable representatives, providing the highest level of service to our customers.'
            />
        </div>
    );
};

export default Home;
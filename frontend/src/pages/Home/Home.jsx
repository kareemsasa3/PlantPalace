import React, { useState, useEffect, useRef } from 'react';
import PropTypes from 'prop-types';
import './Home.css';

const Section = ({ title, description }) => (
    <div className='home-section'>
        <div className='section-text'>
            <h3 className='section-title'>{title}</h3>
            <p className='section-description'>{description}</p>
        </div>
    </div>
);

Section.propTypes = {
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
};

const Home = () => {
    const [activeSection, setActiveSection] = useState(0);
    const intervalRef = useRef(null);

    const sections = [
        {
            title: "Cultivate Your Lifestyle",
            description: "Discover a natural approach to healing",
            image: '/section1.jpg'
        },
        {
            title: "High Quality Products",
            description: "We advocate for a homeopathic approach to healing and meticulously select terpene profiles to maximize benefits for the CBD endocrine system",
            image: '/section2.jpg'
        },
        {
            title: "Hemp & Healing",
            description: "Our team is here to guide you through your options, ensuring you find the perfect product for your unique needs and lifestyle",
            image: '/section3.jpg'
        },
        {
            title: "Our Promise",
            description: "We are committed to creating a one-of-a-kind experience through exceptional quality products and knowledgeable representatives",
            image: '/section4.jpg'
        }
    ];

    const startInterval = () => {
        if (intervalRef.current) {
            clearInterval(intervalRef.current);
        }
        intervalRef.current = setInterval(() => {
            setActiveSection((prevSection) => (prevSection + 1) % sections.length);
        }, 4000);
    };

    useEffect(() => {
        startInterval();

        return () => {
            clearInterval(intervalRef.current);
        };
    }, [sections.length]);

    const handleDotClick = (index) => {
        setActiveSection(index);
        startInterval();
    };

    return (
        <div className="home-container">
            <div 
                className='content-wrapper'
                style={{
                    backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.9), rgba(0, 0, 0, 0.3)), url(${sections[activeSection].image})`,
                }}
            >
                <Section
                    title={sections[activeSection].title}
                    description={sections[activeSection].description}
                />
                <div className="navigation-dots">
                    {sections.map((_, index) => (
                        <div
                            key={index}
                            className={`circle ${index === activeSection ? 'active' : ''}`}
                            onClick={() => handleDotClick(index)}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Home;
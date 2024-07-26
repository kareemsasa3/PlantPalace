import React from 'react';
import './Home.css';

const Home = () => {
    return (
        <div className='home-content'>
            <h1 className='home-title'>Cultivate Your Lifestyle With Us</h1>
            <h3 className='home-details'>Discover a natural healing approach that your mind will thank you for!</h3>
            <div className='home-section'>
                <img src='/cannabis-grow.jpg' alt='Quality Compassionate Care' className='section-image-left' />
                <div className='section-text'>
                    <h3 className='home-details'>Quality Compassionate Care</h3>
                    <p className='home-description'>
                        Our company is dedicated to providing high-quality, all-natural hemp products with a focus on compassion and convenience. We advocate for a homeopathic approach to healing and meticulously select terpene profiles to maximize benefits for the CBD endocrine system.
                    </p>
                </div>
            </div>
            <div className='home-section'>
                <div className='section-text'>
                    <h3 className='home-details'>Hemp & Healing</h3>
                    <p className='home-description'>
                        We offer a broad range of hemp products designed to enhance comfort and peace of mind. Our team is here to guide you through your options, ensuring you find the perfect product for your unique needs and lifestyle.
                    </p>
                </div>
                <img src='/granny-smoking.jpg' alt='Hemp and Healing' className='section-image-right' />
            </div>
            <div className='home-section'>
                <img src='/maryjane.png' alt='Marijuana' className='section-image-left' />
                <div className='section-text'>
                    <h3 className='home-details'>Our Promise</h3>
                    <p className='home-description'>
                        We are committed to creating a one-of-a-kind experience through exceptional quality products and knowledgeable representatives, providing the highest level of service to our customers.
                    </p>
                </div>
            </div>
        </div>
    );
};

export default Home;

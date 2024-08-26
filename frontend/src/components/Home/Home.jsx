import React from 'react';
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

const Home = () => {
    return (
        <div className='home-content'>
            <h1 className='home-title'>Cultivate Your Lifestyle With Us</h1>
            <h3 className='home-subtitle'>Discover a natural healing approach that your mind will thank you for!</h3>
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

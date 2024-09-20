import React from 'react';
import { Link } from 'react-router-dom';
import './Categories.css';
import { categories } from '../../data/categoriesData';

const Categories = () => {
  return (
    <div className="categories-grid">
      {categories.map((category) => (
        <Link to={category.path} key={category.name} className="category-item">
          <div className="category-content">
            <h3>{category.name}</h3>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default Categories;
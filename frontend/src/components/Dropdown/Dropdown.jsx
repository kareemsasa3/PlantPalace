import React, { useRef, useEffect } from 'react';
import './Dropdown.css';

const Dropdown = ({ suggestions, onSelect, showSuggestions, setShowSuggestions }) => {
  const dropdownRef = useRef(null);

  // Handle clicks outside the dropdown to hide suggestions
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setShowSuggestions(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [setShowSuggestions]);

  return (
    <div className="dropdown-container" ref={dropdownRef}>
      {showSuggestions && suggestions.length > 0 && (
        <div className="dropdown-menu" role="listbox">
          {suggestions.map((suggestion, index) => (
            <div
              key={index}
              className="dropdown-item"
              role="option"
              tabIndex="0"
              onClick={() => {
                onSelect(suggestion);
                setShowSuggestions(false);
              }}
              onKeyDown={(e) => {
                if (e.key === 'Enter') {
                  onSelect(suggestion);
                  setShowSuggestions(false);
                }
              }}
            >
              {suggestion.label}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Dropdown;
import React, { useRef, useEffect } from 'react';
import { Icon } from 'semantic-ui-react';
import NavList from '../NavList';
import './Sidebar.css';

const Sidebar = ({ sidebarOpen, toggleSidebar }) => {
  const sidebarRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (sidebarRef.current && !sidebarRef.current.contains(event.target)) {
        toggleSidebar();
      }
    };

    if (sidebarOpen) {
      document.addEventListener('mousedown', handleClickOutside);
    }

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [sidebarOpen, toggleSidebar]);

  const closeSidebar = () => {
    toggleSidebar();
  };

  return (
    <div className={`sidebar-container ${sidebarOpen ? 'active' : ''}`} ref={sidebarRef}>
      <button className="close-btn" onClick={toggleSidebar}>
        <Icon name='close' className='icon'/>
      </button>
      <NavList className='sidebar' closeSidebar={closeSidebar} />
    </div>
  );
};

export default Sidebar;
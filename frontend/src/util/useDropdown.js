import { useState } from 'react';

const useDropdown = () => {
  const [openDropdown, setOpenDropdown] = useState(null);

  const handleDropdownToggle = (index) => {
    setOpenDropdown(openDropdown === index ? null : index);
  };

  return { openDropdown, handleDropdownToggle };
};

export default useDropdown;
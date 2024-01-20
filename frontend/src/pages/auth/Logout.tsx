import React, { useEffect } from 'react';
import { useNavigate } from 'react-router';
import { userLogout } from 'services/UserService';

const Logout = () => {
  const navigate = useNavigate();

  useEffect(() => {
    userLogout();
    navigate("/login")
  }, [])

  return null
};

export default Logout;
import React from 'react';
import {
  Outlet
} from 'react-router-dom';


const PageLayout = () => {
  return (
    <div>
      NAVBAR --<br />
      <Outlet />
    </div>
  );
};

export default PageLayout;
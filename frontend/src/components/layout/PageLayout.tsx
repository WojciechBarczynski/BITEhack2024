import React from 'react';
import {
  Outlet
} from 'react-router-dom';
import SideBar from './sidebar/SideBar';
import ProfileBar from './profilebar/ProfileBar';


const PageLayout = () => {
  return (
    <div className="page">
      <div className="content">
        <div>
          <SideBar />
        </div>
        <main>
          <Outlet />
        </main>
        <div>
          <ProfileBar />
        </div>
      </div>
    </div>
  );
};

export default PageLayout;
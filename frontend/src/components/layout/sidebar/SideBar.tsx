import React from 'react';
import SideBarLink from './SideBarLink';
import HomeOutlinedIcon from '@mui/icons-material/HomeOutlined';
import PeopleAltOutlinedIcon from '@mui/icons-material/PeopleAltOutlined';
import SmokingRoomsOutlinedIcon from '@mui/icons-material/SmokingRoomsOutlined';
import AutoGraphOutlinedIcon from '@mui/icons-material/AutoGraphOutlined';
import SettingsOutlinedIcon from '@mui/icons-material/SettingsOutlined';
import LogoutOutlinedIcon from '@mui/icons-material/LogoutOutlined';

const SideBar = () => {
  return (
    <div className="sidebar">
      <SideBarLink
        to="/"
        icon={<HomeOutlinedIcon />}
        label="Main Page"
      />
      <SideBarLink
        to="/"
        icon={<PeopleAltOutlinedIcon />}
        label="Friends"
      />
      <SideBarLink
        to="/"
        icon={<SmokingRoomsOutlinedIcon />}
        label="Addictions"
      />
      <SideBarLink
        to="/"
        icon={<AutoGraphOutlinedIcon />}
        label="Progress"
      />
      <div style={{ marginTop: "50px" }} />
      <SideBarLink
        to="/"
        icon={<SettingsOutlinedIcon />}
        label="Settings"
      />
      <SideBarLink
        to="/login"
        icon={<LogoutOutlinedIcon />}
        label="Logout"
      />
    </div>
  );
};

export default SideBar;
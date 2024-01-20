import React from 'react';
import LightbulbOutlinedIcon from '@mui/icons-material/LightbulbOutlined';

const Profile = () => {
  return (
    <div className="profilePage">
      <div className="container">
        <b>Welcome to Quit It!</b><br />
        Our belief is, that your friends can be of great help when trying to overcome your addictions!
        Hence, we're offering you the best <b>Social Network</b> out there to let them do that!<br /><br />

        <LightbulbOutlinedIcon /> The <b>idea</b> behind our system is to allow your friends to report cases,
        when you break your own resolutions, and let you control your <b>healing process</b>, while providing
        all statistics and necessary advice to help you with it!
      </div>
    </div>
  );
};

export default Profile;
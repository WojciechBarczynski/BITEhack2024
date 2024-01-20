import React from 'react';
import HelpIcon from '@mui/icons-material/Help';
import LightbulbOutlinedIcon from '@mui/icons-material/LightbulbOutlined';
import SupervisedUserCircleIcon from '@mui/icons-material/SupervisedUserCircle';

const FriendsList = () => {
  return (
    <div className="friendsListPage">
      <div className="container info">
        <div className="icon">
          <HelpIcon />
        </div>
        <div className="content">
          <div>
            In this panel you can see the list of the friends that requested <b>YOUR SUPPORT</b><br />
            You can help them by simply reporting if they break their own resolutions.<br />
            You can do so by clicking <b>REPORT</b> next to one of your friend's addictions.<br /><br />
            If this list is empty - don't worry! Ask your friend's to assign you to specific addiction.
          </div>
        </div>
      </div>
      <div className="container">
        <div className="friendName">
          <SupervisedUserCircleIcon /> <b>Arek</b>
        </div>

        <div className="dataItems">
          <div className="container dataItem">
            <div className="col">
              <h3>alcohol</h3>
            </div>
            <div>
              <button className="showMore">
                Report
              </button>
            </div>
          </div>
        </div>

      </div>
    </div>
  );
};

export default FriendsList;
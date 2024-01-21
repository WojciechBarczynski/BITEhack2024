import React from 'react';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import { useParams } from 'react-router';

const ReportFriend = () => {
  const { addictionId, friendId } = useParams();

  return (
    <div>
      <div className="container info warning">
        <div className="icon">
          <WarningAmberIcon />
        </div>
        <div className="content">
          <div>
            You are about to remind your friend of their resolutions.<br />
            Please remember to be <b>supporting</b> in your message!<br /><br />
            False reports will significantly lower your trust score.
          </div>
        </div>
      </div>
      Bleh: {addictionId} {friendId}
    </div>
  );
};

export default ReportFriend;
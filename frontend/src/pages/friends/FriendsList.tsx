import React, { useState, useEffect } from 'react';
import HelpIcon from '@mui/icons-material/Help';
import SupervisedUserCircleIcon from '@mui/icons-material/SupervisedUserCircle';
import { getAllAddictsWithAddictions } from 'services/FriendRelationService';
import { AllAddictsWithAddictionsDto as AddictWithAddictionsDto } from 'services/dtos/friendRelationTypes';
import { AddictionDto } from 'services/dtos/addictionTypes';
import { useNavigate } from 'react-router';

const FriendsList = () => {
  const navigate = useNavigate();
  const [addicts, setAddicts] = useState<AddictWithAddictionsDto[]>([]);

  useEffect(() => {
    getAllAddictsWithAddictions().then((newAddicts: AddictWithAddictionsDto[]) => {
      setAddicts(newAddicts)
    })
  }, [])

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
      {addicts.map((addict: AddictWithAddictionsDto, index: number) => (
        <div className="container" key={index}>
          <div className="friendName">
            <SupervisedUserCircleIcon /> <b>{addict?.name}</b>
          </div>

          <div className="dataItems">
            {addict?.addictions?.map((addiction: AddictionDto) => (
              <div className="container dataItem" style={{ marginBottom: 0 }}>
                <div className="col">
                  <h3>{addiction?.name}</h3>
                </div>
                <div>
                  <button className="showMore" onClick={() => navigate(`/report/${addiction?.id}/${addict?.addictId}`)}>
                    Report
                  </button>
                </div>
              </div>
            ))}
          </div>

        </div>
      ))}
    </div>
  );
};

export default FriendsList;
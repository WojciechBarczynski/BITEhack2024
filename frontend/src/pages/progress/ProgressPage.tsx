import React from 'react';
import HelpIcon from '@mui/icons-material/Help';

const ProgressPage = () => {
  return (
    <div>
      <div className="container info">
        <div className="icon">
          <HelpIcon />
        </div>
        <div className="content">
          <div>
            Below you can see the graph of all your reports.
          </div>
        </div>
      </div>
      <div className="container">
        <h2 style={{ textAlign: "center", margin: 0 }}>Your Progress</h2>
        <div style={{ textAlign: "center", marginBottom: 15 }}>Day by Day</div>
        <img
          className="graphImg"
          src={process.env.PUBLIC_URL + '/images/bar_chart.png'} alt="My cool image"
        />
      </div>
    </div>
  );
};

export default ProgressPage;
import React from 'react';

const ProfileBar = () => {
  return (
    <div className="profilebar">
      <img src={process.env.PUBLIC_URL + '/images/avatar.jpg'} alt="My cool image" />
      <div className="helloSection">
        <h3>Hi, <span className="accent">Szymon</span></h3>
        <span>
          You are{" "}
          <span className="accent">3 days</span>{" "}
          clean
        </span>
        <span>Keep it up!</span>
      </div>
      <div className="helloSection">
        <h3>Statistics</h3>
        <span>
          Weight <span className="accent">96 kg</span>{" "}
        </span>
        <span>
          Height <span className="accent">182 cm</span>{" "}
        </span>
      </div>
    </div>
  );
};

export default ProfileBar;
import React from 'react';

const ProfileBar = () => {
  const username = localStorage.getItem("username");
  const weight = localStorage.getItem("weight");
  const height = localStorage.getItem("height");
  const birthyear = localStorage.getItem("birthyear");
  
  return (
    <div className="profilebar">
      <img src={process.env.PUBLIC_URL + '/images/avatar.jpg'} alt="My cool image" />
      <div className="helloSection">
        <h3>Hi, <span className="accent">{username}</span></h3>
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
          Weight <span className="accent">{height} kg</span>{" "}
        </span>
        <span>
          Height <span className="accent">{weight} cm</span>{" "}
        </span>
      </div>
    </div>
  );
};

export default ProfileBar;
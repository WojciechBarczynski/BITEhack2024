import React from 'react';

type AddictionProps = {
  name: string
}

const Addiction: React.FC<AddictionProps> = ({
  name
}) => {
  return (
    <div className="addiction">
      <div className="col">
        <h3>{name}</h3>
        <span>You are <b className="accent">3 days</b> free</span>
      </div>
      <div>
        <button className="showMore">
          Show more
        </button>
      </div>
    </div>
  );
};

export default Addiction;
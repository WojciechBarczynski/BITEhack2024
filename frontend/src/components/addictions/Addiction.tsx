import React from 'react';
import { useNavigate } from 'react-router';

type AddictionProps = {
  id: number,
  name: string
}

const Addiction: React.FC<AddictionProps> = ({
  id, name
}) => {
  const navigate = useNavigate();

  return (
    <div className="addiction">
      <div className="col">
        <h3>{name}</h3>
        <span>You are <b className="accent">3 days</b> clean</span>
      </div>
      <div>
        <button className="showMore" onClick={() => navigate(`/addiction/${id}`)}>
          Show more
        </button>
      </div>
    </div>
  );
};

export default Addiction;
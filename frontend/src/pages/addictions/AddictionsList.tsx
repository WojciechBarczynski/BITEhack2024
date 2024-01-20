import React, { useState, useEffect } from 'react';
import HelpIcon from '@mui/icons-material/Help';
import Addiction from 'components/addictions/Addiction';
import { getUserAddictions } from 'services/UserService';
import { AddictionDto } from 'services/dtos/addictionTypes';

const AddictionsList = () => {
  const [addictions, setAddictions] = useState<AddictionDto[] | null>([])

  useEffect(() => {
    getUserAddictions().then((userAddictions) => {
      setAddictions(userAddictions)
    })
  }, [])

  return (
    <div className="addictionsListPage">
      <div className="container info">
        <div className="icon">
          <HelpIcon />
        </div>
        <div className="content">
          <div>
            In this panel you can see your <b>addictions</b> and also <b>add new ones</b>
          </div>
        </div>
      </div>
      <div className="container addAddiction">
        <div>
          Want to <b>Quit</b> something? Add <b>It</b> here!
        </div>
        <div>
          <select>
            <option selected disabled>Select Addiction</option>
            <option>e</option>
          </select>
        </div>
      </div>
      <div className="container addictions">
        {addictions?.map((addiction) => (
          <Addiction id={+addiction?.id} name={addiction?.name} />
        ))}
      </div>
    </div>
  );
};

export default AddictionsList;
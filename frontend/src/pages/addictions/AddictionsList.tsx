import React, { useState, useEffect } from 'react';
import HelpIcon from '@mui/icons-material/Help';
import Addiction from 'components/addictions/Addiction';
import { addAddiction, getUserAddictions } from 'services/UserService';
import { AddictionDto } from 'services/dtos/addictionTypes';
import { getAllAddictions } from 'services/AddictionService';

const AddictionsList = () => {
  const [addictions, setAddictions] = useState<AddictionDto[]>([])
  const [allAddictions, setAllAddictions] = useState<AddictionDto[]>([])

  useEffect(() => {
    refreshAddictions();
  }, [])

  const refreshAddictions = () => {
    getUserAddictions().then((userAddictions) => {
      setAddictions(userAddictions)
    })
    getAllAddictions().then(setAllAddictions)
  }

  const filterAddictions = (addToFilter: AddictionDto) => {
    return !addictions.map(add => add.id).includes(addToFilter.id)
  }

  const addictionsToAdd = allAddictions.filter(filterAddictions)
  
  const handleChange = async (event: any) => {
    await addAddiction(event.target.value)
    refreshAddictions();
  }

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
        <div>8
          <select onChange={handleChange}> {/** TODO: make it return to default option */} 
            <option selected disabled>Select Addiction</option>
            {addictionsToAdd.map(add => (<option key={add.id} value={add.id}> {add.name} </option>))}
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
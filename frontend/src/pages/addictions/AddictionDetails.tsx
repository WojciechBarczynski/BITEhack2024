import React, { useEffect } from 'react';
import { useParams } from 'react-router';
import { getReportsForAddiction } from 'services/ReportsService';

const AddictionDetails = () => {
  const { addictionId } = useParams()

  useEffect(() => {
    getReportsForAddiction(addictionId)
  }, [])

  return (
    <div>
      Showing addiction: {addictionId}
    </div>
  );
};

export default AddictionDetails;
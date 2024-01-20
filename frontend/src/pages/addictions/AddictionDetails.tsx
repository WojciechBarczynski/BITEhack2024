import React, { useEffect } from 'react';
import { useParams } from 'react-router';
import { getReportsForAddiction } from 'services/ReportService';
import ThumbUpOffAltIcon from '@mui/icons-material/ThumbUpOffAlt';
import SentimentVeryDissatisfiedIcon from '@mui/icons-material/SentimentVeryDissatisfied';
import { AllReportsDto } from 'services/dtos/reportTypes';

const AddictionDetails = () => {
  const { addictionId } = useParams()

  useEffect(() => {
    addictionId && getReportsForAddiction(+addictionId).then((reports: AllReportsDto | null) => {
      console.log(reports)
    })
  }, [])

  return (
    <div>
      <div className="container thumbsup">
        <div className="icon">
          <ThumbUpOffAltIcon />
        </div>
        <div className="content">
          <div>
            In this panel you can see your <b>addictions</b> and also <b>add new ones</b>
          </div>
        </div>
      </div>
      <div className="container thumbsdown">
        <div className="icon">
          <SentimentVeryDissatisfiedIcon />
        </div>
        <div className="content">
          <div>
            In this panel you can see your <b>addictions</b> and also <b>add new ones</b>
          </div>
        </div>
      </div>
      Showing addiction: {addictionId}
    </div>
  );
};

export default AddictionDetails;
import React, { useEffect, useState } from 'react';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import { useParams } from 'react-router';
import { getReportsForAddiction } from 'services/ReportService';
import ThumbUpOffAltIcon from '@mui/icons-material/ThumbUpOffAlt';
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm';
import StickyNote2Icon from '@mui/icons-material/StickyNote2';
import { AllReportsDto } from 'services/dtos/reportTypes';

const AddictionDetails = () => {
  const { addictionId } = useParams()
  const [addictionInfo, setAddictionInfo] = useState<AllReportsDto | null>(null)

  useEffect(() => {
    addictionId && getReportsForAddiction(+addictionId).then((reports: AllReportsDto | null) => {
      setAddictionInfo(reports)
    })
  }, [])

  const daysPassed = addictionInfo?.milestones?.archived?.day || 0

  return (
    <div className="addictionDetailsPage scrollable">
      {daysPassed >= 0 && <div className="container thumbsup">
        <div className="icon">
          <ThumbUpOffAltIcon />
        </div>
        <div className="content">
          <div>
            You've been clean from <b>{addictionInfo?.name}</b> for <b>{addictionInfo?.daysClean}</b> days!<br /><br />
            On day <b>{addictionInfo?.milestones?.next?.day}</b> you will see the following results:<br />
            {addictionInfo?.milestones?.next?.message}
          </div>
        </div>
      </div>}

      {addictionInfo?.milestones?.predictionMsg && <div className="container info warning">
        <div className="icon">
          <WarningAmberIcon />
        </div>
        <div className="content">
          <div>
            {addictionInfo.milestones.predictionMsg}
          </div>
        </div>
      </div>}


      {addictionInfo?.reports?.map((report) => (
        <div className="container report">
          Your friend <b>{report?.nick}</b> noticed you breaking your resolutions!<br />
          <StickyNote2Icon /> {report?.postContent}
          <div className="footer"><AccessAlarmIcon /> {report?.date}</div>
        </div>
      ))}

      { }
    </div>
  );
};

export default AddictionDetails;
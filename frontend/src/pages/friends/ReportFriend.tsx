import React, { useEffect, useState } from 'react';
import WarningAmberIcon from '@mui/icons-material/WarningAmber';
import { useParams } from 'react-router';
import { getUserById } from 'services/UserService';
import { UserDto } from 'services/dtos/userTypes';
import { getAddictionById } from 'services/AddictionService';
import { AddictionDto } from 'services/dtos/addictionTypes';
import TextInput from 'components/interface/TextInput';
import ChatIcon from '@mui/icons-material/Chat';
import FormButton from 'components/interface/FormButton';
import { reportUser } from 'services/ReportService';

const ReportFriend = () => {
  const { addictionId, friendId } = useParams();
  const [username, setUsername] = useState<string>("")
  const [addiction, setAddiction] = useState<string>("")
  const [comment, setComment] = useState("")

  useEffect(() => {
    friendId && getUserById(+friendId).then((value: UserDto | null) => {
      setUsername(value?.nick || "")
    })

    addictionId && getAddictionById(+addictionId).then((value: AddictionDto | null) => {
      setAddiction(value?.name || "")
    })
  })

  const submitComment = async () => {
    (friendId && addictionId) && reportUser(+friendId, +addictionId, comment)
    setComment("")
  }

  return (
    <div>
      <div className="container info warning">
        <div className="icon">
          <WarningAmberIcon />
        </div>
        <div className="content">
          <div>
            You are about to remind your friend of their resolutions.<br />
            Please remember to be <b>supporting</b> in your message!<br /><br />
            False reports will significantly lower your trust score.
          </div>
        </div>
      </div>
      <div className="container reportForm">
        <div>
          Reporting <b>{username}</b> for <b>{addiction}</b>
        </div>
        <TextInput
          placeholder="Enter comment"
          icon={<ChatIcon />}
          value={comment}
          onChange={(e: React.FormEvent<HTMLInputElement>) => setComment(e?.currentTarget?.value)}
        />
        <FormButton
          label="Submit"
          onClick={submitComment}
        />
      </div>
    </div>
  );
};

export default ReportFriend;
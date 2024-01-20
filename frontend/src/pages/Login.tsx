import React from 'react';
import AccountCircleOutlinedIcon from '@mui/icons-material/AccountCircleOutlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import TextInput from 'components/interface/TextInput';
import FormButton from 'components/interface/FormButton';

const Login = () => {
  return (
    <div>
      <div className="actionBlock">
        <h1>Login</h1>
        <TextInput
          icon={<AccountCircleOutlinedIcon />}
          placeholder="Username"
        />
        <TextInput
          icon={<LockOutlinedIcon />}
          placeholder="Password"
        />
        <FormButton
          label="Login"
        />
      </div>
    </div>
  );
};

export default Login;
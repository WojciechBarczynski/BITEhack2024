import React from 'react';
import Person2OutlinedIcon from '@mui/icons-material/Person2Outlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import TextInput from 'components/interface/TextInput';
import FormButton from 'components/interface/FormButton';
import { Link } from 'react-router-dom';

const Login = () => {
  return (
    <div className="actionBlock">
      <h1>Login</h1>
      <TextInput
        icon={<Person2OutlinedIcon />}
        placeholder="Username"
      />
      <TextInput
        icon={<LockOutlinedIcon />}
        placeholder="Password"
        type="password"
      />
      <FormButton
        label="Login"
      />
      <div>
        New user?{"  "}
        <Link to="/register">Sign up here!</Link>
      </div>
    </div>
  );
};

export default Login;
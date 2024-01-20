import React, { useState, useEffect, ChangeEvent } from 'react';
import Person2OutlinedIcon from '@mui/icons-material/Person2Outlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import TextInput from 'components/interface/TextInput';
import FormButton from 'components/interface/FormButton';
import { Link, useNavigate } from 'react-router-dom';
import { userLogin } from 'services/LoginService';

const Login = () => {
  const navigate = useNavigate();

  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [failedLogin, setFailedLogin] = useState(false)

  const onLogin = async () => {
    const userId = await userLogin(username, password);
    if (userId) {
      navigate("/profile")
    } else {
      setFailedLogin(true)
    }
  }

  return (
    <div className="actionBlock">
      <h1>Login</h1>
      <TextInput
        icon={<Person2OutlinedIcon />}
        placeholder="Username"
        value={username}
        onChange={(e: React.FormEvent<HTMLInputElement>) => setUsername(e?.currentTarget?.value)}
      />
      <TextInput
        icon={<LockOutlinedIcon />}
        placeholder="Password"
        type="password"
        value={password}
        onChange={(e: React.FormEvent<HTMLInputElement>) => setPassword(e?.currentTarget?.value)}
      />
      <FormButton
        label="Login"
        onClick={onLogin}
      />
      {failedLogin && <div className="errorMsg">
        Incorrect login or password
      </div>}
      <div>
        New user?{"  "}
        <Link to="/register">Sign up here!</Link>
      </div>
    </div>
  );
};

export default Login;
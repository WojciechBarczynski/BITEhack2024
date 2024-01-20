import TextInput from 'components/interface/TextInput';
import Person2OutlinedIcon from '@mui/icons-material/Person2Outlined';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import ScaleOutlinedIcon from '@mui/icons-material/ScaleOutlined';
import HeightOutlinedIcon from '@mui/icons-material/HeightOutlined';
import CakeOutlinedIcon from '@mui/icons-material/CakeOutlined';
import React from 'react';
import FormButton from 'components/interface/FormButton';
import { Link } from 'react-router-dom';

const Register = () => {
  return (
    <div className="actionBlock">
      <h1>Register</h1>
      <TextInput
        icon={<Person2OutlinedIcon />}
        placeholder="Username"
      />
      <TextInput
        icon={<LockOutlinedIcon />}
        placeholder="Password"
      />
      <TextInput
        icon={<LockOutlinedIcon />}
        placeholder="Repeat Password"
      />
      <div className="doubleRow">
        <TextInput
          icon={<ScaleOutlinedIcon />}
          placeholder="Weight (kg)"
        />
        <TextInput
          icon={<HeightOutlinedIcon />}
          placeholder="Height (cm)"
        />
      </div>
      <TextInput
        icon={<CakeOutlinedIcon />}
        placeholder="Birth Year"
      />
      <FormButton
        label="Register"
      />
      <div>
        Already have an account?{"  "}
        <Link to="/login">Login</Link>
      </div>
    </div>
  );
};

export default Register;
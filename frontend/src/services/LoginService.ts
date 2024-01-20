import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';

const userLogin = async (username: string, password: string) => {
  try {
    const response = await axios.post('/user/login', {
      nick: username,
      password: password,
    });

    if (response.status === 200 && response?.data?.id) {
      localStorage.setItem('userID', response.data.id);
      return response.data.id
    } else {
      return null
    }

  } catch (error) {
    return null
  }
};

export default userLogin;
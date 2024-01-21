import axios from "axios";
import { putUserIdInHeader } from "./requestsHelpers";
import { FullUserDto, UserDto } from "./dtos/userTypes";
import { AddictionDto } from "./dtos/addictionTypes";

axios.defaults.baseURL = 'http://localhost:8080';

export const userLogin = async (username: string, password: string): Promise<FullUserDto | null> => {
  try {
    const response = await axios.post('/user/login', {
      nick: username,
      password: password,
    });

    if (response.status === 200 && response?.data?.id) {
      localStorage.setItem('UserID', response.data.id);
      return response.data
    } else {
      return null
    }

  } catch (error) {
    return null
  }
};

export const userRegister = async (
  nick: string,
  password: string,
  weight: number,
  height: number,
  birthyear: number
) => {
  const body = {
    nick, password, weight, height, birthyear
  }
  try {
    const response = await axios.post('/user/register', body);
    if (response.status === 200) {
      return true
    }
    return false
  } catch (error) {
    return false
  }
}

export const userLogout = () => {
  localStorage.removeItem('UserID');
}

export const getUserById = async (userId: number): Promise<UserDto | null> => {
  try{
    const response = await axios.get(`/user/${userId}`);

    if (response.status !== 200){
      return null
    }

    return response.data
  }catch(error){
    console.error(error)
    return null;
  }
}

export const getAllUsers = async (): Promise<UserDto | null> => {
  const response = await axios.get("/user/all");

  if (response.status !== 200) {
    return null
  }

  return response.data;
}

export const getUserAddictions = async (): Promise<AddictionDto[]> => {
  try {
    const response = await axios.get("/user/addictions", { headers: putUserIdInHeader() })
    if (response.status !== 200) {
      return [];
    }

    return response.data
  } catch (error) {
    return [];
  }
}

export const addAddiction = async (addictionId: number) => {
  try {
    const response = await axios.post("/user/add-addiction", { id: addictionId }, {
      headers: putUserIdInHeader()
    })

    if (response.status === 200) {
      return true
    }
    return false
  } catch (error) {
    return false
  }
}
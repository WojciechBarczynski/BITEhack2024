import axios from "axios";
import { putUserIdInHeader } from "./requestsHelpers";

axios.defaults.baseURL = 'http://localhost:8080';

export const getReportsForAddiction = async (addictionId?: string): Promise<any> => {
  const response = await axios.get("/report", {
    headers: putUserIdInHeader(),
    params: {
      addictionId: addictionId
    }
  },);

  if (response.status !== 200) {
    return null;
  }

  return response.data;
}
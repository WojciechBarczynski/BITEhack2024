import axios from "axios";
import { AddictionDto } from "./dtos/addictionTypes";

axios.defaults.baseURL = 'http://localhost:8080';

export const getAllAddictions = async (): Promise<AddictionDto[] | null> => {
    const response = await axios.get("/addictions/all");

    if (response.status !== 200){
        return null;
    }

    return response.data;
}
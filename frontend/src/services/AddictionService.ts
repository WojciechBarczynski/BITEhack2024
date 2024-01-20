import axios from "axios";
import { AddictionDto } from "./dtos/addictionTypes";

axios.defaults.baseURL = 'http://localhost:8080';

export const getAllAddictions = async (): Promise<AddictionDto[]> => {
    const response = await axios.get("/addiction/all");

    if (response.status !== 200){
        return [];
    }

    return response.data;
}
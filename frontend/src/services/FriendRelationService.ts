import axios from "axios"
import { putUserIdInHeader } from "./requestsHelpers"
import { AllAddictsWithAddictionsDto } from "./dtos/friendRelationTypes"

export const getAllAddictsWithAddictions = async (): Promise<AllAddictsWithAddictionsDto[]> => {
    try{
        const response = await axios.get("/friend/allAddicts", {
            headers: putUserIdInHeader()
        })
    
        if (response.status !== 200){
            return []
        }
    
        return response.data
    }catch (error){
        console.error(error)
        return [];
    }
    
}
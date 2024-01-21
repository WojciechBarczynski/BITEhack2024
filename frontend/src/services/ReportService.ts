import axios from "axios"
import { putUserIdInHeader } from "./requestsHelpers"
import { AllReportsDto } from "./dtos/reportTypes"

export const getReportsForAddiction = async (addictionId: number): Promise<AllReportsDto | null> => {
    
    const response = await axios.get("/report", {
        params: {
            addictionId: addictionId
        },
        headers: putUserIdInHeader()
    })

    if (response.status !== 200){
        console.error(response.status)
        return null
    }

    return response.data;
}

export const reportUser = async (addictId: number, addictionId: number, postContent: string) => {
    try{
        const body = {
            addictId,
            addictionId,
            postContent
        }
    
        const response = await axios.post("/report", body, { 
            headers: putUserIdInHeader()
        })
    
        if (response.status !== 200){
            return false
        }
    
        return true
    }catch(error){
        console.error(error)
        return false
    }
    
}
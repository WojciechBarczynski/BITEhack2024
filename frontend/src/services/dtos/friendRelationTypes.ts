import { AddictionDto } from "./addictionTypes"

export interface AllAddictsWithAddictionsDto {
    id: number
    name: string
    addictions: AddictionDto[] 
}
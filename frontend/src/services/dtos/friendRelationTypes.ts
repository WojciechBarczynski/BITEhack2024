import { AddictionDto } from "./addictionTypes"

export interface AllAddictsWithAddictionsDto {
    addictId: number
    name: string
    addictions: AddictionDto[]
}
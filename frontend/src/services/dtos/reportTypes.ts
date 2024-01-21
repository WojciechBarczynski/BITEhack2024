interface ReportDto {
    nick: string,
    postContent: string,
    date: string
}

interface MilestoneDto {
    day: number,
    message: string
}

export interface AllReportsDto {
    milestones: {
        archived: MilestoneDto,
        next: MilestoneDto,
        predictionMsg: string
    },
    daysClean: number,
    name: string,
    reports: ReportDto[]
}
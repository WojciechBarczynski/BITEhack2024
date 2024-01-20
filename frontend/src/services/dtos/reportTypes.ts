interface ReportDto {
    nick: string,
    postContent: string,
    date: string
}

export interface AllReportsDto {
    name: string,
    reports: ReportDto[]
}
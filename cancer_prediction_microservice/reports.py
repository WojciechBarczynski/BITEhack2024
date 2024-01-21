import numpy as np


class ReportValidation:
    def __init__(
            self,
            reportersId: list = [],
            reportersScores: list = [],
            reportersDecision: list = [],
            reportedId: list = [],
            reportedScore: list = []) -> None:

        if (reportersId == [] or reportersId == None or \
                reportersScores == [] or reportersScores == None or \
                reportersDecision == [] or reportersDecision == None or \
                reportedId == [] or reportedId == None or \
                reportedScore == [] or reportedScore == None):
            raise ValueError("Invalid input")

        if (len(reportersId) != len(reportersScores) or \
                len(reportersId) != len(reportersDecision) or \
                len(reportedId) != len(reportedScore)):
            raise ValueError("Invalid input")

        self.reported = np.ndarray(shape=(len(reportedId), 2))
        self.reported[:, 0] = reportedId
        self.reported[:, 1] = reportedScore

        all_reporters = np.ndarray(shape=(len(reportersId), 3))
        all_reporters[:, 0] = reportersId
        all_reporters[:, 1] = reportersScores
        all_reporters[:, 2] = reportersDecision

        attackers_num = np.sum(all_reporters[:, 2] == True)
        self.attackingTeam = np.ndarray(shape=(attackers_num, 2))
        self.attackingTeam[:, 0] = all_reporters[all_reporters[:, 2] == True][:, 0]
        self.attackingTeam[:, 1] = all_reporters[all_reporters[:, 2] == True][:, 1]

        defenders_num = np.sum(all_reporters[:, 2] == False)
        self.defendingTeam = np.ndarray(shape=(defenders_num, 2))
        self.defendingTeam[:, 0] = all_reporters[all_reporters[:, 2] == False][:, 0]
        self.defendingTeam[:, 1] = all_reporters[all_reporters[:, 2] == False][:, 1]

        self.make_team_scores()

    def make_team_scores(self) -> np.array:
        self.attackingTeamScore = np.sum(np.log(self.attackingTeam[:, 1]))
        self.defendingTeamScore = np.sum(np.log(self.defendingTeam[:, 1]))
        self.reportedScore = np.sum(np.log(self.reported[:, 1]))

    def agree_with_report(self) -> np.array:
        self.attackingTeam[:, 1] = np.minimum(self.attackingTeam[:, 1] + 1, 100)
        self.defendingTeam[:, 1] = np.maximum(self.defendingTeam[:, 1] - 2, 1)
        self.reported[:, 1] = np.minimum(self.reported[:, 1] + 1, 100)
        return np.concatenate((self.attackingTeam, self.defendingTeam, self.reported), axis=0)

    def try_to_remove_report(self) -> (bool, np.array):
        if (np.sqrt(2) * self.attackingTeamScore < self.defendingTeamScore + self.reportedScore / np.sqrt(2)):
            return True, np.concatenate((self.attackingTeam, self.defendingTeam, self.reported), axis=0)
        else:
            self.attackingTeam[:, 1] = np.minimum(self.attackingTeam[:, 1] + 1, 100)
            self.defendingTeam[:, 1] = np.maximum(self.defendingTeam[:, 1] - 1, 1)
            self.reported[:, 1] = np.maximum(self.reported[:, 1] - 3, 1)
            return False, np.concatenate((self.attackingTeam, self.defendingTeam, self.reported), axis=0)

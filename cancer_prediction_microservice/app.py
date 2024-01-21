import numpy as np
from flask import Flask, request

import predictions
from reports import ReportValidation

app = Flask(__name__)


@app.route("/predictions/lungCancer", methods=["GET"])
def lung_cancer_prediction():
    args = request.args
    ageYears = int(args.get("age"))
    weightKg = int(args.get("weight"))
    heightCm = int(args.get("height"))
    cleanDays = int(args.get("cleanFor"))

    msg = predictions.predict_lung_cancer(ageYears, weightKg, heightCm, cleanDays)

    return msg


@app.route("/reports/tryToRemoveReport", methods=["GET"])
def try_to_remove_report() -> (bool, np.array):
    args = request.args
    reportersId = int(args.get("reportersId"))
    reportersScores = int(args.get("reportersScores"))
    reportersDecision = bool(args.get("reportersDecision"))
    reportedId = int(args.get("reportedId"))
    reportedScore = int(args.get("reportedScores"))

    report_service = ReportValidation(
        reportersId=reportersId,
        reportersScores=reportersScores,
        reportersDecision=reportersDecision,
        reportedId=reportedId,
        reportedScore=reportedScore)

    return report_service.try_to_remove_report()


@app.route("/reports/agreeWithReport", methods=["GET"])
def agree_with_report() -> np.array:
    args = request.args
    reportersId = int(args.get("reportersId"))
    reportersScores = int(args.get("reportersScores"))
    reportersDecision = bool(args.get("reportersDecision"))
    reportedId = int(args.get("reportedId"))
    reportedScore = int(args.get("reportedScores"))

    report_service = ReportValidation(
        reportersId=reportersId,
        reportersScores=reportersScores,
        reportersDecision=reportersDecision,
        reportedId=reportedId,
        reportedScore=reportedScore)

    return report_service.agree_with_report()


if __name__ == "__main__":
    app.run()

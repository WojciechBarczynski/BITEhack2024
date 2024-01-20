from flask import Flask, request

import predictions
import reports

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

@app.route("/reports/tryToRemove", methods=["GET"])
def try_to_remove_report():
    args = request.args
    
    return None

@app.route("/reports/agree", methods=["GET"])
def agree_with_report():
    args = request.args
    
    return None


if __name__ == "__main__":
    app.run()

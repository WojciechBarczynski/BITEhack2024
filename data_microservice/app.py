from flask import Flask, request

import predictions

app = Flask(__name__)


@app.route("/predictions/lungCancer", methods=["GET"])
def hello_world():
    args = request.args
    ageYears = int(args.get("age"))
    weightKg = int(args.get("weight"))
    heightCm = int(args.get("height"))
    cleanDays = int(args.get("cleanFor"))

    (revert_probability, clean_probability) = predictions.predict_lung_cancer(
        ageYears, weightKg, heightCm, cleanDays
    )

    return {
        "returnsCancerProbability": revert_probability,
        "cleanCancerProbability": clean_probability,
    }


if __name__ == "__main__":
    app.run()

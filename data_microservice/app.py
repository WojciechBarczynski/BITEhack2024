from flask import Flask, request

import predictions

app = Flask(__name__)


@app.route('/predictions/lungCancer', methods=['GET'])
def hello_world():
    args = request.args
    # years
    age = int(args.get('age'))
    # kg
    weight = int(args.get('weight'))
    # cm
    height = int(args.get('height'))
    (revert_probability, clean_probability) = predictions.predict_lung_cancer(age, weight, height)

    return {'returnsCancerProbability': revert_probability, 'cleanCancerProbability': clean_probability}



if __name__ == '__main__':
    app.run()

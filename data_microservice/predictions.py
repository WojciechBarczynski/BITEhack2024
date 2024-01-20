import numpy as np

class LungCancerPred:
    a_coff = {
        0: 0.0536,
        30: 0.0903,
        35: 0.1045,
        40: 0.1166,
        45: 0.1283,
        50: 0.1365,
        55: 0.1439,
        60: 0.15,
        65: 0.1561,
        70: 0.1647,
        75: 0.1731,
        80: 0.184,
    }
    b_coff = {
        0: -4.7167,
        30: -6.3647,
        35: -7.0058,
        40: -7.5473,
        45: -8.0778,
        50: -8.4441,
        55: -8.7788,
        60: -9.0523,
        65: -9.3258,
        70: -9.715,
        75: -10.092,
        80: -10.5848,
    }
    days_per_year = 365
    
    @staticmethod
    def get_prob(age: int, cleanDays: int) -> float:
        age = max(age, 30)
        age //= 5
        age *= 5
        age_risk_log = LungCancerPred.a_coff[age]
        age_risk_log *= (age - (cleanDays / LungCancerPred.days_per_year))
        age_risk_log += LungCancerPred.b_coff[age]
        return np.exp(age_risk_log)
        

def bmi_factor(weight: int, height: int) -> float:
    return 1

# Returns tuple (return_prob, no_return_prob)
# no_return_prob - probability of lung cancer if person won't smoke ever again
# return_prob - probability of lung cancer if person returns to smoking in return_prob_age age
# return_prob_age - probability of lung cancer if person returns to smoking 
def predict_lung_cancer(age: int, weight: int, height: int, cleanDays: int) -> (float, float, int):
    future_age = age + 10
    return (
        LungCancerPred.get_prob(future_age, cleanDays) / LungCancerPred.get_prob(age, cleanDays),
        future_age, 
        )

import numpy
import math

def TrapezoidalRule(evaluationPoints, step, reverseSum = 0):

    if len(evaluationPoints) % 2 != 0:
        print("Invalid number of points for Trapezoidal rule.")
        return

    result = 0

    if reverseSum == 0:
        result = evaluationPoints[0] / 2 + sum([evaluationPoints[i] for i in range(1, len(evaluationPoints) - 1)]) + evaluationPoints[-1] / 2
        result *= step
    else:
        result = evaluationPoints[-1] / 2 + sum([evaluationPoints[i] for i in range(len(evaluationPoints) - 2, 0, -1)]) + evaluationPoints[0] / 2
        result *= step
    return result

def OneThirdSimpson(evaluationPoints, step, reverseSum = 0):

    if len(evaluationPoints) % 2 != 1:
        print("Invalid number of points for Simpson rule.")
        return

    result = 0

    if reverseSum == 0:
        result = evaluationPoints[0] + sum([evaluationPoints[i] * (4 if i % 2 == 1 else 2) for i in range(1, len(evaluationPoints) - 1)]) + evaluationPoints[-1]
        result *= step / 3
    else:
        result = evaluationPoints[-1] + sum([evaluationPoints[i] * (4 if i % 2 == 1 else 2) for i in range(len(evaluationPoints) - 2, 0, -1)]) + evaluationPoints[0]
        result *= step / 3
    return result

def ThreeEighthsSimpson(evaluationPoints, step, reverseSum = 0):

    if len(evaluationPoints) % 2 != 1:
        print("Invalid number of points for Simpson rule.")
        return

    result = 0

    if reverseSum == 0:
        result = evaluationPoints[0] + sum([evaluationPoints[i] * (3 if i % 3 != 0 else 2) for i in range(1, len(evaluationPoints) - 1)]) + evaluationPoints[-1]
        result *= 3 * step / 8
    else:
        result = evaluationPoints[-1] + sum([evaluationPoints[i] * (3 if i % 3 != 0 else 2) for i in range(len(evaluationPoints) - 2, 0, -1)]) + evaluationPoints[0]
        result *= 3 * step / 8
    return result


def Romberg(estimates, step = 2, convergenece = 2):
    iterations = len(estimates) - 1
    exponent = step ** convergenece

    for i in range(1, iterations + 1):
        for j in range(iterations + 1 - i):
            estimates[j] = (exponent ** i * estimates[j + 1] - estimates[j]) / (exponent ** i - 1)

    return estimates[0]

def MonteCarlo(evaluationPoints, interval, reverseSum = 0):
    result = 0
    if reverseSum == 0:
        result = sum([val for val in evaluationPoints])
    else:
        result = sum([val for val in reversed(evaluationPoints)])
    return result * (interval[1] - interval[0]) / len(evaluationPoints)

def OneThirdSimpson2D(evaluationPoints, step):
    dimension = numpy.shape(evaluationPoints)

    if dimension[0] % 2 != 1:
        print("Invalid number of points for Simpson rule.")
        return

    if dimension[1] % 2 != 1:
        print("Invalid number of points for Simpson rule.")
        return

    result = sum([evaluationPoints[i][j] * (4 if i % 2 == 1 else 2) * (4 if j % 2 == 1 else 2) for i in range(1, dimension[0] - 1) for j in range(1, dimension[1] - 1)])
    result += sum([evaluationPoints[0][j] * (4 if j % 2 == 1 else 2) for j in range(1, dimension[1] - 1)])
    result += sum([evaluationPoints[-1][j] * (4 if j % 2 == 1 else 2) for j in range(1, dimension[1] - 1)])
    result += sum([evaluationPoints[i][0] * (4 if i % 2 == 1 else 2) for i in range(1, dimension[0] - 1)])
    result += sum([evaluationPoints[i][-1] * (4 if i % 2 == 1 else 2) for i in range(1, dimension[0] - 1)])
    result += evaluationPoints[0][0] + evaluationPoints[0][-1] + evaluationPoints[-1][0] + evaluationPoints[-1][-1]
    result *= step[0] * step[1] / 9

    return result

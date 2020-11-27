import json

###########
## Usage ##
###########

# This script compares the specified file version with the one (results.json) present in judy's folder.

fileVersion = "V0.json"
judyFileVersion = "R0.json"

# Get difference of two lists
def diff(list1, list2):
    return (list(list(set(list1)-set(list2)) + list(set(list2)-set(list1))))

with open('./mutants_results/' + fileVersion) as file:
    fileV0 = json.load(file)

with open('../judy-3.0.0-M1/' + judyFileVersion) as file:
    fileV1 = json.load(file)

dataV0 = list(filter(lambda x: x["name"] == "com.google.gson.stream.JsonWriter", fileV0["classes"]))[0]
dataV0Mutants = list(map(lambda x: x["operators"][0] + " | " + str(x["lines"][0]), list(filter(lambda x: x["lines"][0] >= 0, sorted(dataV0["notKilledMutant"], key=lambda mutant: mutant["lines"][0])))))

dataV1 = list(filter(lambda x: x["name"] == "com.google.gson.stream.JsonWriter", fileV1["classes"]))[0]
dataV1Mutants = list(map(lambda x: x["operators"][0] + " | " + str(x["lines"][0]), list(filter(lambda x: x["lines"][0] >= 0, sorted(dataV1["notKilledMutant"], key=lambda mutant: mutant["lines"][0])))))

dataDiff = diff(dataV0Mutants, dataV1Mutants)

print("Operator | Line | " + str(len(dataV0Mutants)))
print("--------------------------------")


for mutant in dataV0Mutants:
    print(mutant)
    print("--------------------------------")


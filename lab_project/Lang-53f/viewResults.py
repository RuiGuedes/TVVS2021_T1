import json

###########
## Usage ##
###########

# This script compares the specified file version with the one (results.json) present in judy's folder.

fileDir = "./mutants_results/V0.json"
fileDirJudy = "../judy-3.0.0-M1/result.json"

with open(fileDirJudy) as file:
    fileV0 = json.load(file)

dataV0 = list(filter(lambda x: x["name"] == "org.apache.commons.lang.time.DateUtils", fileV0["classes"]))[0]
dataV0Mutants = list(map(lambda x: x["operators"][0] + " | " + str(x["lines"][0]), list(filter(lambda x: x["lines"][0] >= 0, sorted(dataV0["notKilledMutant"], key=lambda mutant: mutant["lines"][0])))))

print("Operator | Line | " + str(len(dataV0Mutants)))
print("--------------------------------")

for mutant in dataV0Mutants:
    print(mutant)
    print("--------------------------------")


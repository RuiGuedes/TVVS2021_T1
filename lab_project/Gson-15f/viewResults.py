import json

###########
## Usage ##
###########

# This script compares the specified file version with the one (results.json) present in judy's folder.

fileDir = "./mutants_results/Mutants" + "Before" + "StudentTest.json"
fileDirJudy = "../judy-3.0.0-M1/result.json"

with open(fileDirJudy) as file:
    fileV0 = json.load(file)

dataV0 = list(filter(lambda x: x["name"] == "com.google.gson.stream.JsonWriter", fileV0["classes"]))[0]
dataV0Mutants = list(map(lambda x: x["operators"][0] + " | " + str(x["lines"][0]), list(filter(lambda x: x["lines"][0] >= 0, sorted(dataV0["notKilledMutant"], key=lambda mutant: mutant["lines"][0])))))

# Filter Non-Killable Mutants
syntaxErrorMutants = [447, 467, 482, 495, 529]
equivalentMutants = [156, 354, 355, 529]
nonKillableBugMutants = [213, 346, 366, 392]

excludedMutants = syntaxErrorMutants + equivalentMutants + nonKillableBugMutants

dataV1Mutants = list(map(lambda x: x["operators"][0] + " | " + str(x["lines"][0]), list(filter(lambda x: x["lines"][0] >= 0 and x["lines"][0] not in excludedMutants, sorted(dataV0["notKilledMutant"], key=lambda mutant: mutant["lines"][0])))))

## Choose which data to display
datatoDisplay = dataV1Mutants

print("Operator | Line | " + str(len(datatoDisplay)))
print("--------------------------------")

for mutant in datatoDisplay:
    print(mutant)
    print("--------------------------------")

import copy
import time
import sys

#to read input file
def readInput(input_file):
    with open(input_file) as file:
        content = file.read().splitlines()
        for row in content:
            current_row = row.split();
            teams.append([current_row[0]])
            current_response = []
            
            prefrred_list = set()
            [prefrred_list.add(x) for x in current_row[2].split(",")]
            
            not_prefrred_list = set()
            [not_prefrred_list.add(x) for x in current_row[3].split(",")]

            current_response.append(current_row[1])
            current_response.append(prefrred_list)
            current_response.append(not_prefrred_list)

            survey[current_row[0]] = current_response
            
#students requested but not assigned 
def requestedButNotAssigned(team):
    total = 0
    for person in team:
        prefred = survey[person][1]
        if(len(prefred) == 1 and '_' in prefred):
            continue
        total = total + len(prefred)
        for partner in team:
            if(partner != person):
                if(partner in prefred):
                    total = total - 1
    return total
                        
#students assigned but not requested
def assignedButNotrequested(team):
    total = 0
    for person in team:
        non_prefred = survey[person][2]
        for partner in team:
            if(partner != person):
                if(partner in non_prefred):
                    total = total + 1
    return total


'''def group_size_costf(team):
    total = 0
    team_size = teamSize(team)
    for person in team:
        #store in set as a string
        prefred_size = int(survey[person][0])
        if(prefred_size >= 1 and prefred_size != team_size):
            total = total + 1
    return total'''
    

#calculate total cost
def calculateCost(teams):
    total_cost = 0
    group_size_cost = 0
    total_cost = total_cost + (len(teams)*k)

    for team in teams:

        if(cost.get(str(team),-1) == -1):
            group_size_cost = 0
            team_size = teamSize(team)
            for person in team:
                prefred_size = int(survey[person][0])
                if(prefred_size >= 1 and prefred_size != team_size):
                    group_size_cost = group_size_cost + 1
            cuurentTeamCost = group_size_cost + (requestedButNotAssigned(team)*n) + (assignedButNotrequested(team)*m)
            total_cost = total_cost + cuurentTeamCost
            cost[str(team)] = cuurentTeamCost
        else:
            total_cost = total_cost + cost.get(str(team))
    return total_cost

#calculate team size
def teamSize(team):
    return len(team)
                                

#generate successors
def successors(teams):
    s = []
    for team1 in range(0,len(teams)):
        if(len(teams[team1]) == 3):
                continue
        team2 = team1+1
        while(team2 < len(teams)):
            new_team = teams[team1] + teams[team2]
            if(len(new_team) > 3):
                team2 = team2 + 1
                continue

            newTeamForm = []
            newTeamForm.append(new_team)
            for x in range(0,len(teams)):
                if(x!= team1 and x!= team2):
                    newTeamForm.append(teams[x])

            s.append(newTeamForm)
            team2 = team2 + 1
        
    return s


def solve(teams):
    fringe = [teams]
    min_cost = calculateCost(teams)
    while len(fringe) > 0:
        for s in successors( fringe.pop(0) ):
            cur_succ_cost = calculateCost(s)
            #print(cur_succ_cost)
            #print(s)
            if(cur_succ_cost < min_cost):
                min_cost = cur_succ_cost
                current_state = s
                fringe = []
                fringe.append(s)
    #print(min_cost)
    for team in current_state:
        teamFormation = ""
        for person in team:
            teamFormation = teamFormation + str(person) + " "
        print(teamFormation)
        #print("\n")

    print(min_cost)
    return min_cost


input_file = str(sys.argv[1])
k = int(sys.argv[2])
m = int(sys.argv[3])
n = int(sys.argv[4])

teams = []
# survey taken form students
# it will be dictionary containg the person id as key and its response as value
#the value will be list of integer,dict of preferred students,dict of non preffred students
survey = {}
cost = {}

readInput(input_file)
#print(survey)
start_time = time.time()
minCost = solve(teams)

print("--- %s seconds ---" % (time.time() - start_time))


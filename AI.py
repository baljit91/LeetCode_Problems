# put your group assignment problem here!
"""
(Question 2 from Assignment 1)
1. a) The state space contains ever team combination possible whether team is of size 1,2 and 3.
   b) The successor function generates the possible team combinations from the current team formation.
      by combing the teams with each other.if the team formation has a team of size greater than 3
      it would not be added in to the successors.
   
      for example the current team formation is:
      [[djcran],[zehzhang],[chen464],[kapadia]]
      possible team formations from here are:
      [[djcran,zehzhang],[chen464],[kapadia]] , [[djcran,chen464],[zehzhang],[kapadia]] ,
      [[djcran,kapadia],[zehzhang],[chen464]] , [[djcran],[zehzhang],[chen464],[kapadia]] ,
      [[djcran],[zehzhang,chen464],,[kapadia]] , [[djcran],[zehzhang,kapadia],[chen464]] ,
      [[djcran],[zehzhang],[chen464,kapadia]] , [[djcran],[zehzhang],[chen464],[kapadia]] ,
   c) goal state : Teams formation that minimizes the total amount of work the course staff needs to do
   
   d) cost function calculates the cost as per the rules defined in the problem statement. To Optimize the
      cost function, dynamic programming is used as similar combinations
      of team occurred in different teams formations.
2. Search Algorithm makes new teams formations of students by combining teams with each other.it then finds the cost for
   the new team's formations and add it to the fringe which has minimum cost. This process repeats until the fringe is empty.
   Algorithm avoids the team's formations having a team of
   size greater than 3.It returns the team's formation  with the minimum cost
3. Initially, the algorithm I devised taking a long time for a large input file, because I was inserting multiples successors
   of the current state into the fringe, to optimize the algorithm/reduce the execution time, the only successor with the minimum
   cost is inserted into the fringe.As there are similar combinations of team occurred in different team formations, I used dynamic programming approach is used to optimize the cost calculation.due to this execution time
   for the large input files is significantly reduced
"""


import copy
import time
import sys

#to read input file
def readInput(input_file):
    with open(input_file) as file:
        input_data = file.read().splitlines()
        for row in input_data:
            current_row = row.split();
            teams.append([current_row[0]])
            current_response = []
            preferred_list = set()
            [preferred_list.add(x) for x in current_row[2].split(",")]
            not_preferred_list = set()
            [not_preferred_list.add(x) for x in current_row[3].split(",")]
            current_response.append(current_row[1])
            current_response.append(preferred_list)
            current_response.append(not_preferred_list)
            survey[current_row[0]] = current_response
            
#students requested but not assigned 
def requestedButNotAssigned(team):
    total_complaints = 0
    for student in team:
        preferred = survey[student][1]
        if(len(preferred) == 1 and '_' in preferred):
            continue
        total_complaints = total_complaints + len(preferred)
        for partner in team:
            if(partner != student and partner in preferred):
                total_complaints = total_complaints - 1
    return total_complaints
                        
#students assigned but not requested
def assignedButNotrequested(team):
    total_complaints = 0
    for student in team:
        non_preferred = survey[student][2]
        for partner in team:
            if(partner != student and partner in non_preferred):
                total_complaints = total_complaints + 1
    return total_complaints


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
def calculate_cost(teams):
    total_cost = 0
    group_size_cost = 0
    total_cost = total_cost + (len(teams)*k)

    for team in teams:
        if(cost.get(str(team),-1) == -1):
            group_size_cost = 0
            team_size = teamSize(team)
            for student in team:
                preferred_size = int(survey[student][0])
                if(preferred_size >= 1 and preferred_size != team_size):
                    group_size_cost = group_size_cost + 1
            cuurent_team_cost = group_size_cost + (requestedButNotAssigned(team)*n) + (assignedButNotrequested(team)*m)
            total_cost = total_cost + cuurent_team_cost
            cost[str(team)] = cuurent_team_cost
        else:
            total_cost = total_cost + cost.get(str(team))
    return total_cost

#calculate team size
def teamSize(team):
    return len(team)
                                

#generate successors
def successors(teams):
    succ = []
    for team1 in range(0,len(teams)):
        #team1 size equal to 3
        if(len(teams[team1]) == 3):
                continue
        team2 = team1+1
        while(team2 < len(teams)):
            new_team = teams[team1] + teams[team2]
            #if team size greater than 3 then continue
            if(len(new_team) > 3):
                team2 = team2 + 1
                continue
            new_team_formation = []
            new_team_formation.append(new_team)
            for x in range(0,len(teams)):
                if(x!= team1 and x!= team2):
                    new_team_formation.append(teams[x])

            succ.append(new_team_formation)
            team2 = team2 + 1
        
    return succ


def solve(teams):
    fringe = [teams]
    min_cost = calculate_cost(teams)
    while len(fringe) > 0:
        for s in successors( fringe.pop(0) ):
            cur_succ_cost = calculate_cost(s)
            #print(cur_succ_cost)
            #print(s)
            if(cur_succ_cost < min_cost):
                min_cost = cur_succ_cost
                current_state = s
                fringe = []
                fringe.append(s)
    #print(min_cost)
    #print output
    for team in current_state:
        team_formation = ""
        for person in team:
            team_formation = team_formation + str(person) + " "
        print(team_formation)
    print(min_cost)


input_file = str(sys.argv[1])
k = int(sys.argv[2])
m = int(sys.argv[3])
n = int(sys.argv[4])
teams = []
# survey taken form students
# it will be dictionary containing the student id as key and its response as value
# value is list of integer,set of preferred students,set of non preffred students
# using set to optimize cost calculation 
survey = {}
#dp table
cost = {}

readInput(input_file)
solve(teams)

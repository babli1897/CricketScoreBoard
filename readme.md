Problem:
Write a cricket scorecard that will show the score for a team along with score of each player.
You will be given the number of players in each team, the number of overs and their batting
order as input. Then, we can input overs ball by ball with the runs scored on that ball (could be
wide, no ball or a wicket as well). <br />
You are expected to print individual scores, number of balls faced, number of 4s, number of 6s
for all the players from the batting side at the end of every over. You also need to print total
score, total wickets. Essentially, you need to keep a track of all the players, strike changes (at
the end of the over or after taking singles or 3s) and maintain their scores, also keep track of
extra bowls that are being bowled (like wides or no balls). You also need to print which team
won the match at the end. <br />
This is the bare minimum solution which is expected for the problem. You can add some more
features once you are done with these, like maintaining bowlers record (total overs bowled, runs
conceded, wickets taken, maiden overs, dot balls, economy, etc.). Total team extras, batsman
strike rates, etc. can be added too. But these are "good to have" features, please try to complete
the bare minimum first. <br />
Make sure your code is readable and maintainable and preferably object oriented. It should be
modular and extensible, to add new features if needed.
Sample input and output: <br />
Number of Players in Each team <br />
5
Number of Overs <br />
2
Game Initialized with Id 8 <br />
Batting Order for Team 1 <br />
P1
P2
P3
P4
P5 <br />
Over 1 <br />
1
1
1
1
1
2 <br />
Score Card for Team 1 <br />
Player Name         score               4s                  6s                  Balls <br />
P1*                 3                   0                   0                   3 <br />
P2*                 4                   0                   0                   3 <br />
P3                  0                   0                   0                   0 <br />
P4                  0                   0                   0                   0 <br />
P5                  0                   0                   0                   0 <br />
Total: 7/0 <br />
Overs: 1 <br />
Over 2 <br />
W
4
4
Wd
W
1
6 <br />
Score Card for Team 1 <br />
Player Name         score               4s                  6s                  Balls <br />
P1                  3                   0                   0                   4 <br />
P2*                 10                  0                   1                   4 <br />
P3                  8                   2                   0                   3 <br />
P4*                 1                   0                   0                   1 <br />
P5                  0                   0                   0                   0 <br />
Total: 23/2 <br />
Overs: 2 <br />
Batting Order for Team 2 <br />
P6
P7
P8
P9
P10 <br />
Over 1 <br />
4
6
W
W
1
1 <br />
Score Card for Team 2 <br />
Player Name         score               4s                  6s                  Balls <br />
P6                  10                  1                   1                   3 <br />
P7*                 1                   0                   0                   1 <br />
P8                  0                   0                   0                   1 <br />
P9*                 1                   0                   0                   1 <br />
P10                 0                   0                   0                   0 <br />
Total: 12/2 <br />
Overs: 1 <br />
Over 2 <br />
6
1
W
W <br />
Score Card for Team 2 <br />
Player Name         score               4s                  6s                  Balls <br />
P6                  10                  1                   1                   3 <br />
P7*                 8                   0                   1                   3 <br />
P8                  0                   0                   0                   1 <br />
P9                  1                   0                   0                   2 <br />
P10                 0                   0                   0                   1 <br />
Total: 19/4 <br />
Overs: 1.4 <br />
Result: Team 1 won the match by 4 runs <br />

It's a spring boot project (JAVA) <br />

Change db properties (url, username, password) in application.properties file <br />
Run db_scripts.sql  on mysql instance(5.x) <br />
Run the main application

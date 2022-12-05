# UBC TTC League Leaderboard

### Ranking system for tournaments

*---------------for the end of semesters---------------*

### *The uses of the system*:
- Store players' information.
  > Players must have club membership.
  > 
  > *Membership information is stored in an additional Excel file.*
- Store players' points.
  > ### **For every player**
  > - The base score is 0.
  > - Ranking based on points' accumulation.
  > ### **For each round between two players:**
  > - Both players will earn 1 base point.
  > - The winner will earn 2 extra points.

### *User Stories*:
- As a user, I want to be able to add multiple players into the list.
- As a user, I want to be able to add multiple results to a player.
- As a user, I want to be able to check the player's score.
- As a user, I want to be able to check the player's ranking.
- As a user, I want to be able to save my player list to file.
- As a user, I want to be able to be able to load my player list from file. 

# Instructions for Grader

- You can generate the first required event related to adding players into the list by AddPlayer.
- You can generate the second required event related to adding results to a player by AddResult.
- You can locate my visual component by mainMenu.
- You can save the state of my application by Save button.
- You can reload the state of my application by Load button.

# Phase 4: Task 2
- Add a player(e.g. A), then return the result "Added Player: A"

# Phase 4: Task 3
- UML is loaded in "data" file
- 
- Change: 
- While the new player is added into the list, it won't show up in the very first place.
- Only after the data is loaded and reopen the platform then it'll show up.
- So I would adjust the order of the addPlayer function in GUI to let it show on time.
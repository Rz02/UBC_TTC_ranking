package ui;

import model.Player;
import model.PlayerList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Represent the rank system of the players.
public class Rank {
    private static final String JSON_STORE = "./data/playerList.json";
    private Scanner input;
    private PlayerList playerList;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // EFFECTS: constructs ranking and runs application
    public Rank() throws FileNotFoundException {
        input = new Scanner(System.in);
        playerList = new PlayerList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runRank();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runRank() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            System.out.println("This is UBC_TTC ranking system.");
            displayMenu();
            System.out.println("Please enter command to check info: ");
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Thank you for using the system.");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select from: ");
        System.out.println("\t0 -> Add new player");
        System.out.println("\t1 -> Add result on a player");
        System.out.println("\t2 -> Show the ranking");
        System.out.println("\t3 -> Show the player's info");
        System.out.println("\ts -> Save the ranking info.");
        System.out.println("\tl -> load the ranking info.");
        System.out.println("\tq -> Quit the system.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "0":
                newPlayer();
                break;
            case "1":
                addResult();
                break;
            case "2":
                playerRank();
                break;
            case "3":
                playerInfo();
                break;
            case "s":
                savePlayerList();
                break;
            case "l":
                loadPlayerList();
                break;
            default:
                System.out.println("The selection is invalid.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for name and membership and adds to the ranking
    public void newPlayer() {
        Scanner name = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String savedName = name.nextLine();
        System.out.println("Your name is : " + savedName);

        Scanner memB = new Scanner(System.in);
        System.out.println("Please enter your membership validity: ");
        boolean savedMemB = Boolean.parseBoolean(memB.nextLine());
        System.out.println("Your membership is: " + savedMemB);

        List<Boolean> resulting = new ArrayList<>();

        Player player = new Player(savedName, savedMemB, resulting);
        playerList.addPlayer(player);
        System.out.println("Player " + player.getName() + " has been added.");
    }

    // MODIFIES: player
    // EFFECTS: add new result on a player
    private void searchFromName(String b, PlayerList playerList) {
        int i;
        for (i = 0; i < playerList.players().size(); i++) {
            Player p = playerList.players().get(i);
            if (b.equals(p.getName())) {
                System.out.println("Please enter the new result: ");
                Scanner userInput4 = new Scanner(System.in);
                boolean c;
                c = userInput4.nextBoolean();
                p.getResult().add(c);
                System.out.println("The result has been updated.");
            }
        }
    }

    // MODIFIES: player
    // EFFECTS: add new result on a player
    private void addResult() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the Player: ");
        String b = userInput.nextLine();
        if (playerList.listPlayer().contains(b)) {
            searchFromName(b, playerList);
        } else {
            System.out.println("The player doesn't exist.");
        }
    }

    // Build up the ranking list.
    private void playerRank() {
        if (playerList.players().size() == 0) {
            System.out.println("The system hasn't had any player yet.");
        } else {
            playerList.players().sort(Collections.reverseOrder());
            System.out.println("\t" + playerList.listPlayer());
        }
    }

    // EFFECT: get the player's name or rank by the command.
    public Integer searchByName(PlayerList pl, String m, String n) {
        int i;
        int result = -1;
        for (i = 0; i < pl.players().size(); i++) {
            Player p = pl.players().get(i);
            if (m.equals(p.getName())
                    && n.equals("score")) {
                result = p.getScore();
            } else if (m.equals(p.getName())
                    && n.equals("ranking")) {
                result = pl.players().indexOf(p) + 1;
            }
        }
        return result;
    }

    // EFFECT: keep searching if the user willing to do.
    private void keepSearching() {
        Scanner userInput2 = new Scanner(System.in);
        System.out.println("Searching another player?: ");
        String b = userInput2.nextLine();
        if (b.equals("Yes")) {
            playerInfo();
        } else {
            System.out.println("Thanks for browsing.");
        }
    }

    // Search up a player's specific information.
    private void playerInfo() {
        Scanner userInput = new Scanner(System.in);
        String a;
        System.out.println("Enter the Player: ");
        a = userInput.next();
        if (playerList.listPlayer().contains(a)) {
            Scanner userInput1 = new Scanner(System.in);
            System.out.println("Player's score or ranking: ");
            String type1 = userInput1.nextLine();
            if (searchByName(playerList, a, type1) != -1) {
                System.out.println(searchByName(playerList, a, type1));
                keepSearching();
            } else {
                System.out.println("The player isn't a member.");
                keepSearching();
            }
        } else {
            System.out.println("The player doesn't exist.");
            keepSearching();
        }
    }

    // EFFECTS: saves the workroom to file
    private void savePlayerList() {
        try {
            jsonWriter.open();
            jsonWriter.write(playerList);
            jsonWriter.close();
            System.out.println("Saved " + playerList.listPlayer() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPlayerList() {
        try {
            playerList = jsonReader.read();
            System.out.println("Loaded " + playerList.listPlayer() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
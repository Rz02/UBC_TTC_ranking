package ui;

import model.Player;
import model.PlayerList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/playerList.json";
    private PlayerList playerList;

    private JPanel mainMenu;
    private JPanel addPlayer;
    private JPanel doneAdding;
    private JPanel addResult;
    private JPanel doneResult;
    private JPanel ranking;
    private JPanel searchInfo;
    private JPanel checkingInfo;
    private JLabel label;
    private JButton b0;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JTextField t0;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;


    public GUI() {
        super("Rank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        playerList = new PlayerList();
        initializeMenu();
        addPlayerPanel();
        doneAddingPanel();
        addResultPanel();
        doneResultPanel();
        addRankingPanel();
        searchInfoPanel();
        checkingInfoPanel();

        welcome();
        initializeMB();
        mainMenu.add(b0);
        mainMenu.add(b1);
        mainMenu.add(b2);
        mainMenu.add(b3);
        mainMenu.add(b4);
        mainMenu.add(b5);
        mainMenu.add(b6);
        addActionForButton();

        setVisible(true);
    }

    public void welcome() {
        JLabel welcome = new JLabel("UBC-TTC Ranking");
        mainMenu.add(welcome);
        JLabel j = new JLabel();
        j.setIcon(new ImageIcon(
                new ImageIcon("./data/logo-2.png").getImage().getScaledInstance(
                        60, 60, Image.SCALE_DEFAULT)));
        j.setMinimumSize(new Dimension(1, 1));
        mainMenu.add(j);
    }

    public void initializeMenu() {
        mainMenu = new JPanel();
        add(mainMenu);
        label = new JLabel();
        label.setText("No players in the ranking.");
    }

    public void initializeMB() {
        b0 = new JButton("Add player");
        b1 = new JButton("Add result");
        b2 = new JButton("The ranking");
        b3 = new JButton("Player info");
        b4 = new JButton("Save");
        b5 = new JButton("Load");
        b6 = new JButton("Quit");
    }

    public void addActionForButton() {
        b0.addActionListener(this);
        b0.setActionCommand("ap");
        b1.addActionListener(this);
        b1.setActionCommand("ar");
        b2.addActionListener(this);
        b2.setActionCommand("tr");
        b3.addActionListener(this);
        b3.setActionCommand("pi");
        b4.addActionListener(this);
        b4.setActionCommand("s");
        b5.addActionListener(this);
        b5.setActionCommand("l");
        b6.addActionListener(this);
        b6.setActionCommand("q");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("ap")) {
            showPlayerPanel();
        } else if (ae.getActionCommand().equals("ar")) {
            showResultPanel();
        } else if (ae.getActionCommand().equals("tr")) {
            showRanking();
        } else if (ae.getActionCommand().equals("pi")) {
            showInfoPanel();
        } else if (ae.getActionCommand().equals("s")) {
            saving();
        } else if (ae.getActionCommand().equals("l")) {
            loading();
        } else if (ae.getActionCommand().equals("q")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Rmm")) {
            returnToMM();
        } else if (ae.getActionCommand().equals("AP")) {
            addPlayers();
        } else if (ae.getActionCommand().equals("AR")) {
            addResults();
        } else if (ae.getActionCommand().equals("S")) {
            checkInfo();
        }
    }

    // ADD PLAYER PART

    public void showPlayerPanel() {
        add(addPlayer);
        addPlayer.setVisible(true);
        doneAdding.setVisible(false);
        mainMenu.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(false);
        ranking.setVisible(false);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(false);
    }

    public void addPlayerPanel() {
        addPlayer = new JPanel();

        addPlayer.setVisible(true);
        createPlayer();

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        addPlayer.add(mainMenuButton);
    }

    public void createPlayer() {
        JLabel name = new JLabel("The player's name: ");
        t0 = new JTextField(10);
        addPlayer.add(name);
        addPlayer.add(t0);

        JLabel membership = new JLabel("The player's membership(Enter y if yes): ");
        t1 = new JTextField(1);
        addPlayer.add(membership);
        addPlayer.add(t1);

        JButton addPlayers = new JButton("Add Player");
        addPlayers.setActionCommand("AP");
        addPlayers.addActionListener(this);
        addPlayer.add(addPlayers);
    }

    public boolean member() {
        return t1.getText().equals("y");
    }

    public void addPlayers() {
        List<Boolean> resulting = new ArrayList<>();
        Player player = new Player(t0.getText(), member(), resulting);
        playerList.addPlayer(player);
        doneAdding();
    }

    public void doneAddingPanel() {
        doneAdding = new JPanel();

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        doneAdding.add(mainMenuButton);
    }

    public void showDoneAdding() {
        add(doneAdding);
        addPlayer.setVisible(false);
        doneAdding.setVisible(true);
        mainMenu.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(false);
        ranking.setVisible(false);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(false);
    }

    public void doneAdding() {
        JLabel done = new JLabel("The player is added.");
        doneAdding.add(done);
        showDoneAdding();
    }


    // ADD RESULT PART

    public void showResultPanel() {
        add(addResult);
        addPlayer.setVisible(false);
        doneAdding.setVisible(false);
        mainMenu.setVisible(false);
        addResult.setVisible(true);
        doneResult.setVisible(false);
        ranking.setVisible(false);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(false);
    }

    public void addResultPanel() {
        addResult = new JPanel();

        addResult.setVisible(true);
        createResult();

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        addResult.add(mainMenuButton);
    }

    public void createResult() {
        JLabel name = new JLabel("The player's name: ");
        t2 = new JTextField(10);
        addResult.add(name);
        addResult.add(t2);

        JLabel membership = new JLabel("The new result (Enter y if win): ");
        t3 = new JTextField(1);
        addResult.add(membership);
        addResult.add(t3);

        JButton addResults = new JButton("Add Result");
        addResults.setActionCommand("AR");
        addResults.addActionListener(this);
        addResult.add(addResults);
    }

    public boolean result() {
        return t3.getText().equals("y");
    }

    public void addResults() {
        int i;
        if (playerList.listPlayer().contains(t2.getText())) {
            for (i = 0; i < playerList.players().size(); i++) {
                Player p = playerList.players().get(i);
                if (t2.getText().equals(p.getName())) {
                    p.getResult().add(result());
                    JLabel done = new JLabel("The result is added.");
                    doneResult.add(done);
                    showDoneResult();
                }
            }
        } else {
            JLabel error0 = new JLabel("The player doesn't exist.");
            doneResult.add(error0);
            showDoneResult();
        }
    }

    public void doneResultPanel() {
        doneResult = new JPanel();

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        doneResult.add(mainMenuButton);
    }

    public void showDoneResult() {
        add(doneResult);
        addPlayer.setVisible(false);
        doneAdding.setVisible(false);
        mainMenu.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(true);
        ranking.setVisible(false);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(false);
    }

    // CHECK RANKING PART

    public void showRanking() {
        add(ranking);
        addPlayer.setVisible(false);
        doneAdding.setVisible(false);
        mainMenu.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(false);
        ranking.setVisible(true);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(false);
    }

    public void addRankingPanel() {
        ranking = new JPanel();
        ranking.setVisible(true);

        createRanking();

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        ranking.add(mainMenuButton);
    }

    public void createRanking() {
        if (playerList.players().size() == 0) {
            JLabel error = new JLabel("The system hasn't had any player yet.");
            ranking.add(error);
        } else {
            playerList.players().sort(Collections.reverseOrder());
            JLabel ranks = new JLabel("\t" + playerList.listPlayer());
            ranking.add(ranks);
        }
    }

    // PLAYER INFO PART

    public void showInfoPanel() {
        add(searchInfo);
        addPlayer.setVisible(false);
        doneAdding.setVisible(false);
        mainMenu.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(false);
        ranking.setVisible(false);
        searchInfo.setVisible(true);
        checkingInfo.setVisible(false);
    }

    public void searchInfoPanel() {
        searchInfo = new JPanel();

        JLabel search0 = new JLabel("Enter the player: ");
        t4 = new JTextField(10);
        searchInfo.add(search0);
        searchInfo.add(t4);

        JButton searching = new JButton("Search");
        searching.setActionCommand("S");
        searching.addActionListener(this);
        searchInfo.add(searching);

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        searchInfo.add(mainMenuButton);
    }

    public void checkingInfoPanel() {
        checkingInfo = new JPanel();

        JButton mainMenuButton = new JButton("Return to Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setActionCommand("Rmm");
        checkingInfo.add(mainMenuButton);
    }

    public void showCheckingPanel() {
        add(checkingInfo);
        addPlayer.setVisible(false);
        doneAdding.setVisible(false);
        mainMenu.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(false);
        ranking.setVisible(false);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(true);
    }

    public void checkInfo() {
        String m = t4.getText();
        if (playerList.listPlayer().contains(m)) {
            int i;
            String result = "";
            for (i = 0; i < playerList.players().size(); i++) {
                Player p = playerList.players().get(i);
                if (m.equals(p.getName())) {
                    result = p.getName() + ": Score: " + p.getScore()
                            + " Rank: " + (playerList.players().indexOf(p) + 1);
                }
            }
            JLabel results = new JLabel(result);
            checkingInfo.add(results);
            showCheckingPanel();
        } else {
            JLabel error = new JLabel("The player doesn't exist.");
            checkingInfo.add(error);
            showCheckingPanel();
        }
    }

    // SAVE RECORDS
    public void saving() {
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(playerList);
            jsonWriter.close();
            System.out.println("Saved " + playerList.listPlayer() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // LOAD RECORDS
    public void loading() {
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        try {
            playerList = jsonReader.read();
            System.out.println("Loaded " + playerList.listPlayer() + " from " + JSON_STORE);
            addRankingPanel();
        } catch (IOException e) {
            label.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    // RETURN TO MAIN MENU
    public void returnToMM() {
        mainMenu.setVisible(true);
        addPlayer.setVisible(false);
        doneAdding.setVisible(false);
        addResult.setVisible(false);
        doneResult.setVisible(false);
        ranking.setVisible(false);
        searchInfo.setVisible(false);
        checkingInfo.setVisible(false);
    }
}

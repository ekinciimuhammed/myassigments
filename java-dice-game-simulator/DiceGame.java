import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiceGame {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Kullanım: java DiceGame.java <input_dosyasının_adı.türü> <çıkış_dosyasının_adı.türü> ");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        try {
            File inputFile = new File(inputFileName);

            if (!inputFile.exists()) {
                System.out.println("İnput  dosyası bulunamadı.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter writer = new FileWriter(outputFileName);

            int howmuchplayer = Integer.parseInt(reader.readLine());
            String[] playersname = reader.readLine().split(",");

            // Oyuncu listesi oluşturma
            List<Player> players = new ArrayList<>();
            for (String playerName : playersname) {
                players.add(new HumanPlayer(playerName)); // Başlangıç puanlarını sıfırla
            }

            String line;
            int currentPlayerIndex = 0;

            // Oyunun ana döngüsü
            while ((line = reader.readLine()) != null) {
                String[] diceRoll = line.split("-");
                int dice1 = Integer.parseInt(diceRoll[0]);
                int dice2 = Integer.parseInt(diceRoll[1]);
                Player currentPlayer = players.get(currentPlayerIndex);
                if ((dice1 == 0) && (dice2 == 0)) {
                    String result = currentPlayer.getName() + " skipped the turn and "
                            + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".";
                    writer.write(result + "\n");
                    currentPlayerIndex = (currentPlayerIndex + 1) % howmuchplayer; // Sıradaki oyuncuya geç
                    continue;
                } else if ((dice1 == 1) && (dice2 == 1)) {
                    String result = currentPlayer.getName() + " threw " + dice1 + "-" + dice2 + ". Game over "
                            + currentPlayer.getName() + "!";
                    writer.write(result + "\n");
                    currentPlayer.setScore(0);
                    players.remove(currentPlayerIndex);
                    howmuchplayer--; // Bir oyuncu kaldı, oyuncu sayısını azalt
                    currentPlayerIndex %= howmuchplayer;
                    continue;
                }
                if ((dice1 != 1 && dice2 != 1) && (dice1 != 0 && dice2 != 0)) {
                    currentPlayer.addScore(dice1 + dice2);
                }
                String result = currentPlayer.getName() + " threw " + dice1 + "-" + dice2 + " and "
                        + currentPlayer.getName() + "’s score is " + currentPlayer.getScore() + ".";
                writer.write(result + "\n");
                currentPlayerIndex = (currentPlayerIndex + 1) % howmuchplayer; // Sıradaki oyuncuya geç
            }

            // Oyun bittiğinde kazananı belirleme
            if (line == null) {
                int maxScore = players.get(0).getScore();
                Player winner = players.get(0);
                for (Player player : players) {
                    if (player.getScore() > maxScore) {
                        maxScore = player.getScore();
                        winner = player;
                    }
                }
                String result = winner.getName() + " is the winner of the game with the score of " + maxScore
                        + ". Congratulations " + winner.getName() + "!";
                writer.write(result);
            }

            // Dosyaları kapatma
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

abstract class Player {
    protected String name;
    protected int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    // Set method for score
    public void setScore(int score) {
        this.score = score;
    }

    public abstract void playTurn();
}

class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    public void playTurn() {
        // İnsan oyuncunun oyun hamlesini burada gerçekleştir
    }
}

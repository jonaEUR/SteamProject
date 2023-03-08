import java.io.*;
import java.util.ArrayList;

public class Main {
    static File csv = new File("steam.csv");
    public static ArrayList<Game> gamesDatabase = new ArrayList<>();

    public static void main(String[] args) {

        gamesDatabase = Game.createGamesFrom(csv);
    }
}
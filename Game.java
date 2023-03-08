import java.io.*;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Game {
    String name;    // Name of the game
    LocalDate releaseDate;  // Release date of the game
    int requiredAge;    // Required age of the game
    int rating;  // Rating of the game
    double price;   // Price of the game

    // Constructor method for creating a new Game object.
    public Game(String name, LocalDate releaseDate, int requiredAge, double price) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.requiredAge = requiredAge;
        this.rating = 0;   // TODO: initial value = 0?
        this.price = price;
    }

    /* Constructor method for creating a new Game object from a line in a CSV-file where every attribute is read as a
    String. When necessary, the method parses the attribute to the correct type. */
    public Game(String name, String releaseDate, String requiredAge, String price) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");    // Date format of method argument

        this.name = name;
        this.releaseDate = LocalDate.parse(releaseDate, formatter);
        this.requiredAge = Integer.parseInt(requiredAge);
        this.rating = 0;   // TODO: initial value = 0?
        this.price = Double.parseDouble(price);
    }

    // Method creates an ArrayList of Game objects from a CSV file.
    public static ArrayList<Game> createGamesFrom(File csv) {
        ArrayList<Game> listOfObjects = new ArrayList<>();

        try {
            String currentLine;

            BufferedReader br = new BufferedReader(new FileReader(csv));

            int iteration = 0;

            while ((currentLine = br.readLine()) != null) {
                // Skip the header line of the CSV file
                if (iteration == 0) {
                    iteration++;
                    continue;
                }

                listOfObjects.add(createGameFrom(currentLine));
            }
        }
        catch (IOException e) {
            System.out.println("File does not exist.");
        }

        return listOfObjects;
    }

    // Method creates a Game object from a String
    static Game createGameFrom(String line) {
        /* Regular expression ensures that the String argument is split on every comma, except when the comma is in-
        between quotation marks */
        String[] columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");   // TODO: ReGex is not covered in lectures.

        return new Game(columns[1], // Name
                columns[2], // Release date
                columns[7], // Required age
                columns[17]);   // Price
    }
}
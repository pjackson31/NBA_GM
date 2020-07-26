import java.io.IOException;

public class Main {

    public static void main(String[] args) throws java.io.IOException {
        // Add the players into a java literate file!
        String fileContents;
        String[] players;


        System.out.println("Loading in players 100");
        fileContents = new String(java.nio.file.Files.readAllBytes
                (java.nio.file.Paths.get("src/NBAplayerScoring.txt")));
        players = fileContents.split("\\r?\\n");

        //Data Structure where we add the players to hash table
        System.out.println("Successfully put " +
              hashNBA(players, 1) + " players in hash table."); //creates the table



    }

    static int hashNBA(String[] nba, int whichHash) throws IOException {
        //pretty much just starting the game
        int width = 1000;
        int height = 100;
        Table table = new Table(width, whichHash);
        int step;
        for (step = 0; step < width * height && step < nba.length; step++) {
            table.add(nba[step]);

        }


        //lets rank the players in the table
        System.out.println("Lets see who the top 20 players in the NBA are (1-20)");
        table.rank20();

        //lets generate a random team of ten players
        table.randomTeam();


        //Besides the players in the NBA who's the Best Basketball Player Ever?
        System.out.println("Luckily, if you were able to draft "+table.findByRank(0)
                +" then your team will win the NBA Championship");

        //how many players were put in the tree
        return step;
    }

}


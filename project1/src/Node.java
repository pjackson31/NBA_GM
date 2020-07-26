import java.util.Scanner;

public class Node {
    public String value;
    public Node next,previous;
    public int rankOverall;
    public String name;

    public Node(String s){
        this.value=s;
    }

    public int playerRank(){
        Scanner in = new Scanner(value).useDelimiter("[^0-9]+");
        rankOverall = in.nextInt();
        return rankOverall;
    }

    public String giveName(){
        name = value.replaceAll("[^a-zA-Z]", " ");
        return name;
    }
}
//add in the int to string part

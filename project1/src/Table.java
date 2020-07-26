import javax.swing.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Table {
    private int size;
    private int hashFn;
    private List[] table;
    private int chainsize;

    public Table(int tableSize, int whichHash) {
        size = tableSize;
        table = new List[size];
        for (int i = 0; i < size; i++)
            table[i] = new List();
        hashFn = whichHash;
        chainsize = 0;
    }

    private int hash(String s) {
        int y = 0;
        long hash = 0;
        int x = 0;
        int num = s.length();
        for (int i = 0; i < s.length() - 1; i++) {
            y = s.charAt(i);
            hash = hash + (y * 31 ^ (num - (i)));
        }
        x = (int) (hash % size);
        return x;
    }


    public void add(String s) {
        table[hash(s)].add(s);
        table[hash(s)].count++;
        if (table[hash(s)].count > chainsize) {
            chainsize = table[hash(s)].count;
        }
        return;
    }

    public String find(String s) {
        if (table[hash(s)].find(s) == null) {
            return "We could not find " + s;
        } else
            return "We have found " + table[hash(s)].find(s).giveName();
    }

    public void remove(String s) {
        table[hash(s)].remove(s);   //account for chainsize decreasing in maxinslot
        return;
    }


    public String findByRank(int n){
        Node current=null;
        for (int i = 0; i < size; i++) {
            if (table[i].first != null) {
                current = table[i].first;
                while (current != null) {
                    if (current.playerRank() == n) {
                        return current.giveName();
                    }
                    current = current.next;
                }
            }
        }
        return null;
    }

    public void rank20() {
        Node current = null;
        String[] rank= new String[21];
        for (int i = 0; i < size; i++) {
            if (table[i].first != null) {
                current = table[i].first;
                while (current != null) {
                    if (current.playerRank() <= 20) {
                        int r = current.playerRank();
                        rank[r] = current.giveName();
                    }
                    current = current.next;
                }
            }
        }
        for(int s=1; s<rank.length;s++){
            System.out.println(rank[s]);
        }
    }

    public void randomTeam()throws IOException {
        String[] team= new String[10];
        int probability=0;
        int bestPlayers= 0;

        //creating an array of random numbers that will be translated to player names
        int[] arr= new int[10];
        for(int i=0; i<arr.length; i++){
            arr[i]= (int) (Math.random()*100);
        }

        //populating a new string array for finding our random team
        for(int j=0; j<arr.length;j++){
            int player=arr[j];
            String p=findByRank(player);
            team[j]=p;
            if(arr[j]<=15){//if team has a player that is within the top 10
                bestPlayers++;
            }
        }


        for( int y=0; y<arr.length;y++){
            probability=probability+arr[y];
        }

        //how will players contribute to a good team and season?
        teamStats squad = new teamStats(team, probability, bestPlayers);
        squad.seasonTotals();

        //use rank 20 to tell how well they will actually do


    }





}

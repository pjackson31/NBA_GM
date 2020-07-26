import java.io.IOException;
import java.lang.String;
import java.lang.System;
import java.util.Random;

public class teamStats {
    String fileContents;
    public String team;
    public String[] teamsNBA;
    public String[] teammates;
    public int probability;
    public int bestPlayers;
    public boolean champions;


    public teamStats(String[] string, int prob, int b)throws IOException {

        this.probability=prob;
        bestPlayers=b;
        teammates = new String[10];
        for (int i=0; i<string.length; i++){
            teammates[i]=string[i];
        }
        fileContents = new String(java.nio.file.Files.readAllBytes //gets the string from the NBAplayerScoring.txt
                (java.nio.file.Paths.get("src/NBAteams.txt")));
        teamsNBA = fileContents.split("\\r?\\n");
        assignTeam();
    }

    public void assignTeam() {
        Random rand = new Random();
        int random = rand.nextInt(30);
        team = teamsNBA[random];
    }

    public int winPredict(){
        double wp=.25;
        int games;
        Random rand = new Random();
        int random = rand.nextInt(30);
        if (bestPlayers==1) {
            wp=wp+.30;
        }
        if (bestPlayers==2){
            wp=wp+.40;
        }
        if (bestPlayers==3){
            wp=wp+.50;
        }
        if (bestPlayers>=4){
            wp=wp+.65;
        }
        wp=wp+(random/100);
        games=(int)(wp*72);
        return games;
    }

    //based of the whole line-up how well will the team do?
    public void championProb(){
        if (probability>0 && probability<=300 ){
            if(scandel()>=5){
                System.out.println("Your team has won the finals this year congrats!");
                champions=true;
                return;
            }
            System.out.println(
                    "Based off overall team chemistry, you have a championship contending team");
        }
        if (probability>300 && probability<=600 ){
            if(scandel()>=8){
                System.out.println("Your team caught momentum going into the playoffs and has" +
                        " won the finals this year congrats!");
                champions=true;
                return;
            }
                System.out.println("Based off overall team chemistry, you have a playoff team");
        }
        if (probability>600){
            if(scandel()>=10){
                System.out.println("By some miracle, your team has won the finals this year congrats!");
                champions=true;
                return;
            }
            System.out.println("Sadly, your team will not have many wins");
        }
    }

    //how many top 15 players are on the team?
    public void keyContributors(){
        if (bestPlayers==1) {
        System.out.println(
                "Additionally our analysts suggest, you have" +
                        " a key player that will help lead your team throughout the season");
        }
        if (bestPlayers==2){
            System.out.println(
                    "Additionally our analysts suggest," +
                            " your team has a great two player-combo that will make it tough on defenses");
        }
        if (bestPlayers==3){
            System.out.println(
                    "Additionally our analysts suggest," +
                            " your team is a super team and will play have a dangerous rotation");
        }
        if (bestPlayers>=4){
            System.out.println(
                    "Additionally our analysts suggest," +
                            " this is a championship team, this year is your year");
        }
    }

    //based off the winning percentage and location of your team how much money will your team bring in??
    public int revenue(){
        int r=180;
        if (bestPlayers==1) {
            r=r+25;
        }
        if (bestPlayers==2){
            r=r+50;
        }
        if (bestPlayers==3){
            r=r+75;
        }
        if (bestPlayers>=4){
            r=r+100;
        }
        if (scandel()>=8) {
            System.out.println("However, you and your team have been caught in controversial events" +
                    "resulting in loss of 20 million in revenue to your "+r+" million.");
            r = r - 20;
        }
        if (champions){
            r=r+100;
            System.out.println("Because you have won the finals your team has earned " +
                    "a 100 million dollar boost in revenue!");
        }
        return r;
    }


    public int scandel() {
        Random rand = new Random();
        int random1 = rand.nextInt(10);
        return random1;
    }


    public void seasonTotals(){
        System.out.println("");
        System.out.println("Welcome to the NBA GM simulator your team is the "+team);
        System.out.println("For the 2020 NBA Draft the "+team+" select:");
        for (int a=0;a<teammates.length;a++){
            System.out.println(teammates[a]);
        }
        System.out.println("Our analysts predict that your team will win " + winPredict()+ " games.");
        keyContributors();
        championProb();
        System.out.println("The team will make about " + revenue()+ " million in revenue this year.");
        if(revenue()>=300){
            System.out.println("The board of the "+team+" would like to give you a 10 million dollar bonus for " +
                    "your great leadership, " +
                    "Thanks for a great year");
        }
    }


}

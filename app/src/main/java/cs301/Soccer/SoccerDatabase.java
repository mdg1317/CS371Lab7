package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    HashMap<String, SoccerPlayer> map = new HashMap<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            return false;
        } else {
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName,
                    uniformNumber, teamName);
            map.put(key, newPlayer);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.remove(key);
            return true;
        } else {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            return map.get(key);
        } else {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpGoals();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpAssists();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpShots();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpSaves();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpFouls();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpYellowCards();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = appendName(firstName, lastName);
        if(map.get(key) != null){
            map.get(key).bumpRedCards();
            return true;
        } else {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if(teamName == null){
            return map.size();
        } else {
            int count = 0;
            for(String key : map.keySet()){
                if(map.get(key).getTeamName().equals(teamName)){
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int count = 0;
        if(teamName == null){
            for(String key : map.keySet()){
                if(count == idx){
                    return map.get(key);
                } else {
                    count++;
                }
            }
        } else {
            for(String key : map.keySet()){
                if(map.get(key).getTeamName().equals(teamName) && count == idx){
                    return map.get(key);
                } else if(map.get(key).getTeamName().equals(teamName)){
                    count++;
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return file.exists();
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for(String key : map.keySet()){
                SoccerPlayer player = map.get(key);
                pw.println(logString(player.getFirstName()));
                pw.println(logString(player.getLastName()));
                pw.println(logString(player.getTeamName()));
                pw.println(logString(Integer.toString(player.getUniform())));
                pw.println(logString(Integer.toString(player.getGoals())));
                pw.println(logString(Integer.toString(player.getAssists())));
                pw.println(logString(Integer.toString(player.getShots())));
                pw.println(logString(Integer.toString(player.getFouls())));
                pw.println(logString(Integer.toString(player.getSaves())));
                pw.println(logString(Integer.toString(player.getYellowCards())));
                pw.println(logString(Integer.toString(player.getRedCards())));
            }
            return true;
        } catch(Exception FileNotFoundException){
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

    public String appendName(String firstName, String lastName){
        return firstName + "##" + lastName;
    }

}

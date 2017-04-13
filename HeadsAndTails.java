/*
 * @authors Adam Elder and Robert Young
 * Etude 11 Solution for COSC326
 */
import java.util.*;

/*
 * Given the number of heads followed by the number of tails, finds a way
 *  to move the coins to make an alternating pattern.
 */
public class HeadsAndTails{
  
  /* 
   * Reads the number of heads and tails from stdin, outputs every step of
   *  the solution to stdout.
   */
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    //Do this for every coin scenario given.
    while(s.hasNext()){
      String[] input = s.nextLine().split("\\s+");
      int numHeads = Integer.parseInt(input[0]);
      int numTails = Integer.parseInt(input[1]);
      ArrayList<String> coins = new ArrayList<String>();
      String starter;
      int steps = 0;
      //Set up the initial coin sequence.
      if(numTails > numHeads){
        starter = "T";
      }else{
        starter = "H";
      }
      while(numHeads > 0){
        coins.add("H");
        numHeads--;
      }
      while(numTails > 0){
        coins.add("T");
        numTails--;
      }
      printCoins(coins);
      
      //Get the appropriate coin type to the start of the sequence.
      while(!starter.equals(coins.get(0))){
        fixStart(coins);
        printCoins(coins);
        steps++;
      }
      
      //Find a way to fix double ups until there are no more.
      while(!swapCoins(coins)){
        printCoins(coins);
        steps++;
      }
      System.out.println(steps + " steps.\n");
    }
  }
  
  /*
   * This method moves the coin type of greater number to the start of the 
   *  sequence of coins.
   * @param coins is an ArrayList of "H" and "T" strings.
   */
  public static void fixStart(ArrayList<String> coins){
    for(int i = 0; i < coins.size(); i++){
      if(coins.get(i).equals("T")){
        Collections.swap(coins, i, i-1);
        return;
      }
    }
  }
  
  /*
   * This method finds the leftmost point where coins can be swapped to fix a
   *  double-up and then swaps the coins at that point.
   * @param coins is an ArrayList of "H" and "T" strings.
   */
  public static boolean swapCoins(ArrayList<String> coins){
    for(int i = 1; i < coins.size() - 1; i++){
      if(coins.get(i).equals(coins.get(i-1)) && !coins.get(i).equals(coins.get(i+1))){
        Collections.swap(coins, i, i+1);
        return false;
      }
    }
    return true;
  }
  
  /*
   * This method prints out a coin sequence represented by an ArrayList.
   * @param coins is an ArrayList of "H" and "T" strings.
   */
  public static void printCoins(ArrayList<String> coins){
    String line = "";
    for(int i = 0; i < coins.size(); i++){
      line = line + coins.get(i) + " ";
    }
    System.out.println(line);
  }
}
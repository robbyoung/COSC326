import java.util.*;

public class HeadsAndTails{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String[] input = s.nextLine().split("\\s+");
      int numHeads = Integer.parseInt(input[0]);
      int numTails = Integer.parseInt(input[1]);
      ArrayList<String> coins = new ArrayList<String>();
      String starter;
      int steps = 0;
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
      
      while(!starter.equals(coins.get(0))){
        fixStart(coins);
        printCoins(coins);
        steps++;
      }
      
      while(!swapCoins(coins)){
        printCoins(coins);
        steps++;
      }
      System.out.println(steps + " steps.\n");
    }
  }
  
  public static void fixStart(ArrayList<String> coins){
    for(int i = 0; i < coins.size(); i++){
      if(coins.get(i).equals("T")){
        Collections.swap(coins, i, i-1);
        return;
      }
    }
  }
  
  public static boolean swapCoins(ArrayList<String> coins){
    for(int i = 1; i < coins.size() - 1; i++){
      if(coins.get(i).equals(coins.get(i-1)) && !coins.get(i).equals(coins.get(i+1))){
        Collections.swap(coins, i, i+1);
        return false;
      }
    }
    return true;
  }
  
  public static void printCoins(ArrayList<String> coins){
    String line = "";
    for(int i = 0; i < coins.size(); i++){
      line = line + coins.get(i) + " ";
    }
    System.out.println(line);
  }
}
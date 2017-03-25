/*
 * @authors Adam Elder, Josh Tell, Robert Young
 * Solution to COSC326 Etude 4 - Peanuts and Pretzels
 */
import java.util.*;

/*
 * Solves the combinatorial Peanuts and Pretzels game introduced in Etude 4
 *  for COSC326.
 */
public class PeanutsAndPretzels{
  /*
   * Takes the initial number of peanuts and pretzels from stdin, followed by 
   *  the available combinations. Will print out the move that will guarantee
   *  a win for the player, or '0 0' if there is none.
   */
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    //A hashmap is used to prevent repetitive game state calculations.
    HashMap<Long, Boolean> states;
    //All possible peanut/pretzel combinations are stored in an ArrayList.
    ArrayList<int[]> combos;
    int peanuts, pretzels;
    while(s.hasNext()){
      combos = new ArrayList<int[]>();
      states = new HashMap<Long, Boolean>();
      String[] bowlCount = s.nextLine().split("\\s+");
      peanuts = Integer.parseInt(bowlCount[0]);
      pretzels = Integer.parseInt(bowlCount[1]);
      boolean doNext = true;
      while(doNext && s.hasNext()){
        String newRule = s.nextLine();
        if(!newRule.equals("")){
          splitCombo(combos, newRule, peanuts, pretzels);
        }else{
          doNext = false;
        }
      }
      //The combinations '1 0' and '0 1' are always available to the player.
      combos.add(new int[]{1, 0});
      combos.add(new int[]{0, 1});
      //Check each available combination and check if it is beatable later.
      for(int i = 0; i < combos.size(); i++){
        if(takeTurn(peanuts - combos.get(i)[0], pretzels - combos.get(i)[1], 
                    combos, false, states)){
          System.out.println(combos.get(i)[0] + " " + combos.get(i)[1]);
          break;
        }
        if(i == combos.size()-1){
          System.out.println("0 0");
        }
      }
    }
  }
  
  /*
   * Convert a combination of the form =x, >x, or <x into a sequence of int
   *  arrays that can be understood by the program.
   * @param combos is an ArrayList storing all of the available combinations.
   * @param c is the player input to be stored in the combos ArrayList.
   * @param peanuts is the number of peanuts at the start of the scenario.
   * @param pretzels is the number of pretzels at the start of the scenario.
   */
  public static void splitCombo(ArrayList<int[]> combos, String c, int peanuts, int pretzels){
    String[] ruleStrings = c.split("\\s+");
    /*if(ruleStrings[0].charAt(0) == '=' && ruleStrings[1].charAt(0) == '='){
      int[] newCombo = new int[2];
      newCombo[0] = Integer.parseInt(ruleStrings[0].substring(1));
      newCombo[1] = Integer.parseInt(ruleStrings[1].substring(1));
      combos.add(newCombo);
    }else{*/
      int[] pAndP = new int[]{ peanuts, pretzels };
      int[] max = new int[2];
      int[] min = new int[2];
      for(int i = 0; i < 2; i++){
        if(ruleStrings[i].charAt(0) == '<'){
          max[i] = Integer.parseInt(ruleStrings[i].substring(1))-1;
          min[i] = 1;
        }else if(ruleStrings[i].charAt(0) == '>'){
          max[i] = pAndP[i];
          min[i] = Integer.parseInt(ruleStrings[i].substring(1))+1;
        }else{
          max[i] = Integer.parseInt(ruleStrings[i].substring(1));
          min[i] = max[i];
        }
      }
      for(int i = min[0]; i <= max[0]; i++){
        for(int j = min[1]; j <= max[1]; j++){
          combos.add(new int[]{i, j});
        }
      }
    //}
  }
  
  public static boolean takeTurn(int peanuts, int pretzels, ArrayList<int[]> combos, boolean myTurn, HashMap<Long, Boolean> states){
    long key = (((long)peanuts) << 32) + pretzels;
    if(myTurn && states.containsKey(key)){
      return states.get(key);
    }
    if(peanuts < 0 && pretzels < 0){
      return false;
    }
    if(peanuts == 0 && pretzels == 0){
      return !myTurn;
    }
    for(int i = 0; i < combos.size(); i++){
      if(peanuts >= combos.get(i)[0] && pretzels >= combos.get(i)[1]){
        if(!takeTurn(peanuts-combos.get(i)[0], pretzels-combos.get(i)[1], combos, !myTurn, states)){
          if(myTurn){
            states.put(key, false);
          }
          return false;
        }
      }
    }
    if(myTurn){
      states.put(key, true);
    }
    return true;
  }
}
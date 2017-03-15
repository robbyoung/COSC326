import java.util.*;

public class PeanutsAndPretzels{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    ArrayList<int[]> combos;
    int peanuts, pretzels;
    while(s.hasNext()){
      combos = new ArrayList<int[]>();
      String[] bowlCount = s.nextLine().split("\\s+");
      peanuts = Integer.parseInt(bowlCount[0]);
      pretzels = Integer.parseInt(bowlCount[1]);
      boolean doNext = true;
      while(doNext){
        String newRule = s.nextLine();
        if(!newRule.equals("")){
          
          splitCombo(combos, newRule);
        }else{
          doNext = false;
        }
      }
      combos.add(new int[]{1, 0});
      combos.add(new int[]{0, 1});
      for(int i = 0; i < combos.size(); i++){
        //System.out.println("Item " + i + ": " + Arrays.toString(combos.get(i)));
      }
      
      for(int i = 0; i < combos.size(); i++){
        if(takeTurns(peanuts, pretzels, combos, combos.get(i))){
          System.out.println(combos.get(i)[0] + " " + combos.get(i)[1]);
          break;
        }
        if(i == combos.size()-1){
          System.out.println("0 0");
        }
      }
    }
    
  }
  
  public static void splitCombo(ArrayList<int[]> combos, String c){
    String[] ruleStrings = c.split("\\s+");
    if(ruleStrings[0].charAt(0) == '=' && ruleStrings[1].charAt(0) == '='){
      int[] newCombo = new int[2];
      newCombo[0] = Integer.parseInt(ruleStrings[0].substring(1));
      newCombo[1] = Integer.parseInt(ruleStrings[1].substring(1));
      combos.add(newCombo);
    }
  }
  
  public static boolean takeTurns(int peanuts, int pretzels, ArrayList<int[]> combos, int[] playerChoice){
    if(peanuts < 0 || pretzels < 0){
      return true;
    }else if(peanuts == 0 && pretzels == 0){
      return false;
    }
    if(peanuts < playerChoice[0] || pretzels < playerChoice[0]){
      return !((peanuts + pretzels)%2 == 0);
    }
    peanuts -= playerChoice[0];
    pretzels -= playerChoice[1];
    if(peanuts == 0 && pretzels == 0){
      return true;
    }
    for(int i = 0; i < combos.size(); i++){
      if(!takeTurns(peanuts-combos.get(i)[0], pretzels-combos.get(i)[1], combos, playerChoice)){
        return false;
      }
    }
    return true;
  }
  
}
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
          splitCombo(combos, newRule, peanuts, pretzels);
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
        System.out.println("Testing " + combos.get(i)[0] + " " + combos.get(i)[1]);
        if(takeTurn(peanuts - combos.get(i)[0], pretzels - combos.get(i)[1], combos, false)){
          System.out.println(combos.get(i)[0] + " " + combos.get(i)[1]);
          break;
        }
        if(i == combos.size()-1){
          System.out.println("0 0");
        }
      }
    }
    
  }
  
  public static void splitCombo(ArrayList<int[]> combos, String c, int peanuts, int pretzels){
    String[] ruleStrings = c.split("\\s+");
    if(ruleStrings[0].charAt(0) == '=' && ruleStrings[1].charAt(0) == '='){
      int[] newCombo = new int[2];
      newCombo[0] = Integer.parseInt(ruleStrings[0].substring(1));
      newCombo[1] = Integer.parseInt(ruleStrings[1].substring(1));
      combos.add(newCombo);
    }else{
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
      //System.out.println("Peanuts: " + Arrays.toString(min) + " , Pretzels: " + Arrays.toString(max));
      for(int i = min[0]; i <= max[0]; i++){
        for(int j = min[1]; j <= max[1]; j++){
          combos.add(new int[]{i, j});
        }
      }
    }
  }
  
  public static boolean takeTurn(int peanuts, int pretzels, ArrayList<int[]> combos, boolean myTurn){
    //System.out.println(peanuts + " and " + pretzels + ", choices:");
    if(peanuts < 0 && pretzels < 0){
      return false;
    }
    if(peanuts == 0 && pretzels == 0){
      return !myTurn;
    }
    for(int i = 0; i < combos.size(); i++){
      //System.out.println(combos.get(i)[0] + " and " + combos.get(i)[1]);
      if(peanuts >= combos.get(i)[0] && pretzels >= combos.get(i)[1]){
        if(!takeTurn(peanuts-combos.get(i)[0], pretzels-combos.get(i)[1], combos, !myTurn)){
          return false;
        }
      }
    }
    return true;
  }
}
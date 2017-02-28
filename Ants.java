/*
 * @Authors Adam Elder and Robert Young
 * Etude 1 for COSC326 "Ants"
 */
import java.util.*;

public class Ants{
  public static void main(String[] args){
    ArrayList<int[]> states = new ArrayList<int[]>();
    ArrayList<String> inputs = new ArrayList<String>();
    Scanner s = new Scanner(System.in);
    int temp = 0;
    while(temp < 2/*s.hasNext()*/){
      inputs.add(s.nextLine());
      temp++;
    }
    for(int i = 0; i < inputs.size(); i++){
      Ants.addState(states, inputs.get(i), inputs);
    }
    
    for(int i = 0; i < states.size(); i++){
      System.out.println(Arrays.toString(states.get(i)));
    }
  }
  
  public static void addState(ArrayList<int[]> states, String dna, ArrayList<String> inputs){
    int[] newState = new int[8];
    newState[0] = Ants.stateToInt(dna.charAt(7), inputs);
    newState[1] = Ants.stateToInt(dna.charAt(7), inputs);
    newState[2] = Ants.stateToInt(dna.charAt(7), inputs);
    newState[3] = Ants.stateToInt(dna.charAt(7), inputs);
    newState[4] = Ants.convertDirection(dna.charAt(2));
    newState[5] = Ants.convertDirection(dna.charAt(3));
    newState[6] = Ants.convertDirection(dna.charAt(4));
    newState[7] = Ants.convertDirection(dna.charAt(5));
    states.add(newState);
  }
  
  public static int stateToInt(char state, ArrayList<String> inputs){
    for(int i = 0; i < inputs.size(); i++){
      if(inputs.get(i).charAt(0) == state){
        return i;
      }
    }
    return -1;
  }
  
  public static int convertDirection(char nsew){
    if(nsew == 'N'){
      return 0;
    }else if(nsew == 'E'){
      return 1;
    }else if(nsew == 'S'){
      return 2;
    }else{
      return 3;
    }
  }
}
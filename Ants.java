/*
 * @Authors Adam Elder and Robert Young
 * Etude 1 for COSC326 "Ants"
 */
import java.util.*;

/*
 * Simulates creatures related to Langton's ant based on a number of
 * 'dna' inputs.
 */ 
public class Ants{
  /*
   * The main method. Takes inputs from stdin and outputs the ant's position 
   *   at the end of each scenario.
   */
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      HashMap<Double, Integer> squares = new HashMap<Double, Integer>();
      ArrayList<int[]> states = new ArrayList<int[]>();
      ArrayList<String> inputs = new ArrayList<String>();
      
      int temp = 0;
      int numSteps = 0;
      int antX = 0, antY = 0, antDirection = 0;
      while(temp != 1){
        String str = s.nextLine();
        if(str.length() < 11 && str.charAt(0) != '#'){
          numSteps = Integer.parseInt(str);
          temp = 1;
        }else if(str.charAt(0) != '#'){
          inputs.add(str);
        }
      }
      if(s.hasNext()){
        s.nextLine();
      }
      
      for(int i = 0; i < inputs.size(); i++){
        Ants.addState(states, inputs.get(i), inputs);
      }
      
      for(int i = 0; i < numSteps; i++){
        Double newState = Double.parseDouble(antX + "." + antY);
        int state = getState(newState, squares);
        if(state == -1){
          state = 0;
          //squares.put(antX + "&" + antY, state);
        }
        int[] dna = states.get(state);
        squares.remove(newState);
        squares.put(newState, dna[antDirection]);
        antDirection = dna[antDirection + 4];
        if(antDirection == 0){
          antY++;
        }else if(antDirection == 1){
          antX--;
        }else if(antDirection == 2){
          antY--;
        }else if(antDirection == 3){
          antX++;
        }
      }
      
      for(int i = 0; i < inputs.size(); i++){
        System.out.println(inputs.get(i));
      }
      System.out.println();
      System.out.println("# " + antX + " " + antY);
      if(s.hasNext()){
        System.out.println();
      }
    }
  }
  
  /*
   * Adds a line of 'dna' to the state array used by the main method using 
   *   input from stdin.
   * @param states is an ArrayList of integer arrays representing the dna lines
   *   for each state.
   * @param dna is the current line of input to be put into the states array.
   * @param inputs is the ArrayList of strings holding the user input for 
   *   the scenario.
   */
  public static void addState(ArrayList<int[]> states, String dna, 
                              ArrayList<String> inputs){
    int[] newState = new int[8];
    newState[0] = Ants.stateToInt(dna.charAt(7), inputs);
    newState[1] = Ants.stateToInt(dna.charAt(8), inputs);
    newState[2] = Ants.stateToInt(dna.charAt(9), inputs);
    newState[3] = Ants.stateToInt(dna.charAt(10), inputs);
    newState[4] = Ants.convertDirection(dna.charAt(2));
    newState[5] = Ants.convertDirection(dna.charAt(3));
    newState[6] = Ants.convertDirection(dna.charAt(4));
    newState[7] = Ants.convertDirection(dna.charAt(5));
    states.add(newState);
  }
  
  /*
   * Converts a state character to it's location in the states array 
   *   used by the main method. 
   * @param state is a character representing a state.
   * @param inputs is the ArrayList of strings holding the user input for the 
   *   scenario.
   * @return int that is the index of the state in the states array.
   */
  public static int stateToInt(char state, ArrayList<String> inputs){
    for(int i = 0; i < inputs.size(); i++){
      if(inputs.get(i).charAt(0) == state){
        return i;
      }
    }
    return -1;
  }
  
  /*
   * Converts directions from a dna character to the integer associated 
   *   with that direction.
   * @param nsew is a character representing a direction (N, E, S, or W).
   * @return int holding the integer representation of the direction.
   */
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
  
  /*
   * This gets the state of a specified square from the squares HashMap.
   * @param key is a string containing the x and y coordinates of the current
   *   square, separated by a '&'.
   * @param hmap is the hashmap containing all of the squares in the scenario.
   * @return int is the integer representation of the specified square's state,
   *   or -1 if it doesn't have one.
   */ 
  public static int getState(Double key, HashMap<Double, Integer> hmap){
    if(hmap.containsKey(key)){
      return hmap.get(key);
    }else{
      return -1;
    }
  }
}

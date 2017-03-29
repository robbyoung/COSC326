/*
 * @authors Declan Bartlett, Adam Elder, Josh Tell, Robert Young
 * Solution for COSC326 Etude 10 - Colouring Maps
 */
import java.util.*;
/*
 * Takes a representation of a map and shows how it can be coloured.
 */
public class ColouringMaps{
  /*
   * The main method. Input is taken from stdin as integers representing regions
   *  followed by the regions that neighbour them.
   */
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String region = s.nextLine();
      ArrayList<int[]> regions = new ArrayList<int[]>();
      while(!region.equals("")){
        String[] inputs = region.split("\\s+");
        int[] adjacents = new int[inputs.length];
        adjacents[0] = -1;
        for(int i = 1; i < inputs.length; i++){
          adjacents[i] = Integer.parseInt(inputs[i]);
        }
        regions.add(adjacents);
        if(s.hasNext()){
          region = s.nextLine();
        }else{
          region = "";
        }
      }
      if(tryColour(0, regions)){
        for(int i = 0; i < regions.size(); i++){
          System.out.println("Region " + i + ": " + regions.get(i)[0]);
        }
      }else{
        System.out.println("NO SOLUTION");
      }
    }
    System.out.println();
  }
  
  /*
   * A recursive function that tries to colour a region based on what its
   *  neighbouring regions are coloured.
   * @param index is an int with the index of the current region to be coloured.
   * @param regions is an ArrayList holding representations of each region and
   *  their neighbours.
   * @return boolean showing whether or not the current region can be coloured
   *  based on the current map colourings of its neighbours.
   */
  public static boolean tryColour(int index, ArrayList<int[]> regions){
    if(index >= regions.size()){
      return true;
    }
    for(int c = 0; c < 4; c++){
      boolean clashes = false;
      for(int i = 1; i < regions.get(index).length; i++){
        if(regions.get(regions.get(index)[i])[0] == c){
          clashes = true;
          break;
        }
      }
      if(!clashes){
        regions.get(index)[0] = c;
        if(tryColour(index+1, regions)){
          return true;
        }
      }
    }
    regions.get(index)[0] = -1;
    return false;
  }
}
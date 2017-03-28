import java.util.*;

//Only four colours are needed to colour any map.
public class ColouringMaps{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      String region = s.nextLine();
      ArrayList<ArrayList<Integer>> regions = new ArrayList<ArrayList<Integer>>();
      while(!region.equals("done")){
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        String[] inputs = region.split("\\s+");
        adjacents.add(-1);
        for(int i = 1; i < inputs.length; i++){
          adjacents.add(Integer.parseInt(inputs[i]));
        }
        regions.add(adjacents);
        if(s.hasNext()){
          region = s.nextLine();
        }else{
          region = "done";
        }
      }
      for(int i = 0; i < regions.size(); i++){
        System.out.println(regions.get(i).toString());
      }
      System.out.println();
      
      if(tryColour(0, regions)){
        for(int i = 0; i < regions.size(); i++){
          System.out.println(regions.get(i).toString());
        }
      }else{
        System.out.print("NO SOLUTION");
      }
    }
  }
  
  public static boolean tryColour(int index, ArrayList<ArrayList<Integer>> regions){
    if(index >= regions.size()){
      return true;
    }
    for(int c = 0; c < 4; c++){
      boolean clashes = false;
      for(int i = 1; i < regions.get(index).size(); i++){
        if(regions.get(index).get(i) == c){
          clashes = true;
        }
      }
      if(!clashes){
        regions.get(index).set(0, c);
        if(tryColour(index+1, regions)){
          return true;
        }
      }
    }
    regions.get(index).set(0, -1);
    return false;
  }
}
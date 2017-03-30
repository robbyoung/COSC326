import java.util.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class AccessPoints{
  public static void main(String[] args){
    ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
    Scanner s = new Scanner(System.in);
    double radius = 10000;
    while(s.hasNext()){
      String[] newPoint = s.nextLine().split("\\s+");
      points.add(new Point2D.Double(Double.parseDouble(newPoint[0]), Double.parseDouble(newPoint[1])));
    }
    
    for(int i = 0; i < points.size(); i++){
      ArrayList<Point2D.Double> temp = new ArrayList<Point2D.Double>(points);
      Point2D.Double startingPoint = temp.remove(i);
      Double tempRadius = irrepressible(radius, startingPoint, temp, points);
      if(tempRadius < radius){
        radius = tempRadius;
      }
    }
    System.out.println("Radius: " + radius + " metres.");
  }

  public static double irrepressible(double radius, Point2D.Double midpoint, ArrayList<Point2D.Double> points, ArrayList<Point2D.Double> allPoints){
    int pointCount = 0;
    double max = 0;
    boolean failed;
    do{
      failed = false;
      for(int i = 0; i < allPoints.size(); i++){
        double distance = Math.abs(midpoint.distance(allPoints.get(i)));
        if(distance <= radius){
          pointCount++;
          if(max < distance){
            max = distance;
          }
        }
        if(pointCount >= 12){
          radius = max - 0.01;
          failed = true;
        }
      }
    }while(failed);
    
    for(int i = 0; i < points.size(); i++){
      ArrayList<Point2D.Double> temp = new ArrayList<Point2D.Double>(points);
      Point2D.Double startingPoint = temp.remove(i);
      startingPoint.x = (startingPoint.x + midpoint.x)/2;
      startingPoint.y =(startingPoint.y + midpoint.y)/2;
      Double tempRadius = irrepressible(radius, null, temp, allPoints);
      if(tempRadius < radius){
        radius = tempRadius;
      }
    }
    return radius;
  }
}
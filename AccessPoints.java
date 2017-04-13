import java.util.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class AccessPoints{
  public static int circleLimit = 3;
  public static int numberOfPoints = 15;
  public static double upperLimit = 10;
  
    public static void main(String[] args){
        for(int p = 0; p < 10; p++){
            ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
            Scanner s = new Scanner(System.in);
            double radius = 10000;
            /*while(s.hasNext()){
            String[] newPoint = s.nextLine().split("\\s+");
            points.add(new Point2D.Double(Double.parseDouble(newPoint[0]), Double.parseDouble(newPoint[1])));
            }*/
            double maxX = upperLimit, maxY = upperLimit, minX = 0, minY = 0;
            Random r = new Random();
            for(int k = 0; k < numberOfPoints; k++){
                double randomX = minX + (maxX - minX) * r.nextDouble();
                double randomY = minY + (maxY - minY) * r.nextDouble();
                points.add(new Point2D.Double(randomX, randomY));
            }
            System.out.println(points.toString());

            for(int i = 0; i < points.size(); i++){
                ArrayList<Point2D.Double> temp = new ArrayList<Point2D.Double>(points);
                Point2D.Double startingPoint = new Point2D.Double(temp.get(i).x, temp.get(i).y);
                temp.remove(i);
                Double tempRadius = irrepressible(radius, startingPoint, temp, points, i, 1);
                if(tempRadius < radius){
                    radius = tempRadius;
                }
            }
            System.out.println("Radius: " + radius + " metres.");
            is_irrepressible(radius, points);
            System.out.println();
            System.out.println();
        }
    }

    public static double irrepressible(double radius, Point2D.Double midpoint, ArrayList<Point2D.Double> points, ArrayList<Point2D.Double> allPoints, int index, int depth){
        //System.out.println("Level " + points.size());
        //System.out.println("Midpoint: (" + midpoint.x + ", " + midpoint.y + ")");
        //System.out.println(allPoints.toString());
        int pointCount = 0;
        double max = 0;
        //System.out.println(points.toString());
        //System.out.println(allPoints.toString());
        for(int i = 0; i < allPoints.size(); i++){
            double distance = Math.abs(midpoint.distance(allPoints.get(i)));
            //System.out.println("Distance: " + distance + " / Radius: " + radius);
            if(distance <= radius){
                pointCount++;
                if(max <= distance){
                    max = distance;
                    //System.out.println("Max distance: " + max);
                }
            }
            if(pointCount >= circleLimit){
                radius = Math.round((max) * 100)/100.00 - 0.01;
                //System.out.println("Radius: " + radius + " metres.");
                i = -1;
                pointCount = 0;
                max = 0;
            }
        }

        for(int i = index; i < points.size(); i++){
            ArrayList<Point2D.Double> temp = new ArrayList<Point2D.Double>(points);
            Point2D.Double startingPoint = new Point2D.Double(temp.get(i).x, temp.get(i).y);
            temp.remove(i);
            startingPoint.x = ((startingPoint.x * depth) + midpoint.x)/(depth + 1);
            startingPoint.y =((startingPoint.y * depth) + midpoint.y)/(depth + 1);
            Double tempRadius = irrepressible(radius, startingPoint, temp, allPoints, i, depth+1);
            if(tempRadius < radius){
                radius = tempRadius;
            }
        }
        return radius;
    }

    public static void is_irrepressible(double radius, ArrayList<Point2D.Double> points){
        double maxX = points.get(0).x, maxY = points.get(0).y, minX = maxX, minY = maxY;
        for(int i = 0; i < points.size(); i++){
            Point2D.Double current = points.get(i);
            if(maxX < current.x){
                maxX = current.x;
            }
            if(minX > current.x){
                minX = current.x;
            }
            if(maxY < current.y){
                maxY = current.y;
            }
            if(minY > current.y){
                minY = current.y;
            }
        }
        Random r = new Random();
        for(int k = 0; k < 100000; k++){
            double randomX = minX + (maxX - minX) * r.nextDouble();
            double randomY = minY + (maxY - minY) * r.nextDouble();
            Point2D.Double current = new Point2D.Double(randomX, randomY);
            int pointCount = 0;
            double max = 0;
            for(int i = 0; i < points.size(); i++){
                double distance = Math.abs(current.distance(points.get(i)));
                if(distance <= radius){
                    pointCount++;
                    if (distance > max){
                        max = distance;
                    }
                }
            }
            if(pointCount >= circleLimit){
                System.out.println("Exception: " + current.x + ", " + current.y + " --> " + max + " metres, " + pointCount + " points.");
            }
        }
        System.out.println("Tests Complete");
    }
}
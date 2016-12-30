import java.util.*;

public class Rectangle {
    private Point[] points;
    private Line[] lines;

    public Rectangle(Point a, Point b, Point c, Point d){
        points = new Point[]{a,b,c,d};
        lines = new Line[]{new Line(a,b), new Line(b,c), new Line(c,d), new Line(d,a)};
    }

    public Rectangle(Point a, Point b){
        this(a, new Point(a.x,b.y),b, new Point(b.x,a.y));
    }

    /**
     * Returns true if the given point is contained in this rectangle.
     * A Point is in a side if its projection on a side is greater than 0 and less than the length of the side
     * If a point is in two adjacent sides, then it is contained in this rectangle.
     */
    public boolean containsPoint(Point p){
        Vector ab = new Vector(points[0],points[1]);
        Vector ap = new Vector(points[0], p);
        Vector bc = new Vector(points[1],points[2]);
        Vector bp = new Vector(points[1], p);
        return ab.dotProduct(ap) >= 0 && ab.dotProduct(ab) >= ab.dotProduct(ap)
                && bc.dotProduct(bp) >= 0 && bc.dotProduct(bc) >= bc.dotProduct(bp);
    }

    public List<Point> getPoints(){ return new ArrayList<>(Arrays.asList(points)); }
    public List<Line> getLines(){ return new ArrayList<>(Arrays.asList(lines)); }

    /**
     * Returns true if all points of @param rect are inside of this rectangle.
     * This is inclusive of the boundaries of the rectangle
     */
    public boolean containsRectangle(Rectangle rect){
        for(Point p: rect.getPoints()){
            if(!containsPoint(p)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this rectangle is adjacent to @param rect.
     */
    public boolean isAdjacent(Rectangle rect){
        Map<Line,Line> map = new HashMap<>();
        for(Line line: lines){
            map.put(line,line);
        }

        for(Line line: rect.getLines()){
            if(map.containsKey(line)){
                if(map.get(line).isOverlapping(line)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bothLinesContainPoint(Line thisLine, Line otherLine, Point p){
        return thisLine.containsPoint(p) && otherLine.containsPoint(p);
    }

    public List<Point> getIntersectionPoints(Rectangle other){
        List<Point> intersectionPoints = new ArrayList<>();

        for(Line otherLine : other.getLines()) {
            for (Line thisLine : this.getLines()) {

                if (thisLine.canIntersect(otherLine) ){
                    Point p = thisLine.getIntersection(otherLine);
                    if(bothLinesContainPoint(thisLine, otherLine, p)) {

                        if (!intersectionPoints.contains(p)) {
                            intersectionPoints.add(p);
                        }
                    }

                }
            }
        }
        return intersectionPoints;
    }

    @Override
    public String toString(){
        return "[ " + lines[0] + " ]" + "[ " + lines[1] + " ]" + "[ " + lines[2] + " ]" + "[ " + lines[3] + " ]" ;
    }
}

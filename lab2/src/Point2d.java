public class Point2d {
    private double xCoord;
    private double yCoord;

    public Point2d() {
        this(0,0);
    }

    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    public double getX() {
        return  xCoord;
    }

    public double getY() {
        return yCoord;
    }

    public void setX(double x) {
        xCoord = x;
    }

    public void setY(double y) {
        yCoord = y;
    }

    public boolean equal(Point2d anotherPoint) {
        return xCoord == anotherPoint.xCoord &&
                yCoord == anotherPoint.yCoord;
    }

    public double distanceTo(Point3d anotherPoint) {
        return (Math.round(Math.sqrt(Math.pow((anotherPoint.getX() - this.getX()), 2) +
                Math.pow((anotherPoint.getY() - this.getY()), 2)) * 100) / 100.0);
    }
}

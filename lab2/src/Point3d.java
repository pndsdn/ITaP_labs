public class Point3d {
    private double xCoord;
    private double yCoord;

    private double zCoord;

    public Point3d() {
        this(0,0,0);
    }

    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public double getX() {
        return  xCoord;
    }

    public double getY() {
        return yCoord;
    }

    public double getZ() {
        return zCoord;
    }

    public void setX(double x) {
        xCoord = x;
    }

    public void setY(double y) {
        yCoord = y;
    }

    public void setZ(double z) {
        zCoord = z;
    }

    public boolean equal(Point3d AnotherPoint) {
        return xCoord == AnotherPoint.xCoord &&
                yCoord == AnotherPoint.yCoord &&
                zCoord == AnotherPoint.zCoord;
    }

    public double distanceTo(Point3d AnotherPoint) {
        return (Math.round(Math.sqrt(Math.pow((AnotherPoint.xCoord - xCoord), 2) +
                Math.pow((AnotherPoint.yCoord - yCoord), 2) +
                Math.pow((AnotherPoint.zCoord - zCoord), 2)) * 100) / 100.0);
    }
}
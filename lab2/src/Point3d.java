public class Point3d extends Point2d {
    private double zCoord;

    public Point3d() {
        this(0,0,0);
    }

    public Point3d(double x, double y, double z) {
        super(x,y);
        zCoord = z;
    }

    public double getZ() {
        return zCoord;
    }

    public void setZ(double z) {
        zCoord = z;
    }

    public boolean equal(Point3d anotherPoint) {
        return super.equal(anotherPoint) &&
                zCoord == anotherPoint.zCoord;
    }

    @Override
    public double distanceTo(Point3d anotherPoint) {
        return (Math.round(Math.sqrt(Math.pow((anotherPoint.getX() - this.getX()), 2) +
                Math.pow((anotherPoint.getY() - this.getY()), 2) +
                Math.pow((anotherPoint.getZ() - this.getZ()), 2)) * 100) / 100.0);
    }
}
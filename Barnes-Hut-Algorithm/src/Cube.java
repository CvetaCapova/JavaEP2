public class Cube {

    private Vector3D absoluteLocation; //the top left corner
    private double size; //length of a side

    public Cube(Vector3D absoluteLocation, double size) {
        this.absoluteLocation = absoluteLocation;
        this.size = size;
    }

    public Vector3D getAbsoluteLocation() {
        return absoluteLocation;
    }

    public double getSize() {
        return size;
    }

    //proves whether a body with coordinates bodyPosition is in this cube
    public boolean contains(Vector3D bodyPosition) {
        return bodyPosition.getX() >= this.absoluteLocation.getX() && bodyPosition.getX() <= (this.absoluteLocation.getX() + size) &&
                bodyPosition.getY() >= this.absoluteLocation.getY() && bodyPosition.getY() <= (this.absoluteLocation.getY() + size) &&
                bodyPosition.getZ() >= this.absoluteLocation.getZ() && bodyPosition.getZ() <= (this.absoluteLocation.getZ() + size);
    }
}

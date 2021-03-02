/**
 * Class ComplexCube represents a whole area in octtree.
 * Each cube is divided in 8 section, which are stored in an array. These sections could contain only one element(CelestialBody) or they can be an octtree.
 * ComplexCube has size, absolute location, total mass and center of mass.
 * If ComplexCube==null, the whole area is empty.
 *
 * Implemented methods:
 * boolean contains(x,y,z)- proves whether a body with coordinates x, y, z is in this cube
 * double size()- returns the length of a side of the cube
 */

public class ComplexCube {

    private Cube cube;
    private Cube[] sections;

    public ComplexCube(Vector3D absoluteLocation, double size) {
        this.cube = new Cube(absoluteLocation, size);
        createSections();
    }

    public ComplexCube(Cube cube) {
        this.cube = cube;
        createSections();
    }

    private void createSections() {
        Vector3D absoluteLocation = cube.getAbsoluteLocation();
        double size = cube.getSize();

        this.sections = new Cube[8];
        sections[0] = new Cube(new Vector3D(absoluteLocation).plus(0,0,0), size/2); //front top left
        sections[1] = new Cube(new Vector3D(absoluteLocation).plus(size/2,0,0), size/2); //front top right
        sections[2] = new Cube(new Vector3D(absoluteLocation).plus(0,size/2,0), size/2); //front bottom left
        sections[3] = new Cube(new Vector3D(absoluteLocation).plus(size/2,size/2,0), size/2); //front bottom right

        sections[4] = new Cube(new Vector3D(absoluteLocation).plus(0,0,size/2), size/2); //back top left
        sections[5] = new Cube(new Vector3D(absoluteLocation).plus(size/2,0,size/2), size/2); //back top right
        sections[6] = new Cube(new Vector3D(absoluteLocation).plus(0,size/2,size/2), size/2); //back bottom left
        sections[7] = new Cube(new Vector3D(absoluteLocation).plus(size/2,size/2,size/2), size/2); //back bottom right
    }

    public Cube getCube() {
        return cube;
    }

    public Cube[] getSections() {
        return sections;
    }
}
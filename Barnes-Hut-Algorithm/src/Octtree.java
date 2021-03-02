public class Octtree {

    private static final double THETA = 1;

    private CelestialBody element;
    private ComplexCube cube;
    private Octtree[] children;

    //constructors
    public Octtree(Vector3D absoluteLocation, double size) {
        this.cube = new ComplexCube(absoluteLocation, size);
    }

    public Octtree(ComplexCube cube) {
        this.cube = cube;
    }

    //adds element to the tree, if this element is in our space
    public void add(CelestialBody newBody) {

        if (cube.getCube().contains(newBody.getPosition())) { //if newBody is in cube
            if (element == null) {                     // if the tree is empty
                element = newBody;                     //now it has only one element (the new element)
            } else {
                if (isLeaf()) {                        //if it is a leaf, creates 8 new octtrees from the sections
                    if (newBody.getPosition().equals(this.element.getPosition())) { //if a body with the same coordinates doesn't exist
                        return;
                    }
                    this.children = new Octtree[8];
                    for (int i = 0; i < 8; i++) {
                        children[i] = new Octtree(new ComplexCube(cube.getSections()[i]));
                    }
                    int childInx = getI(this.element.getPosition());
                    this.children[childInx].add(this.element);
                    this.element = new CelestialBody(this.element.getPosition(), this.element.getMass(), true);
                }
                int childInx = getI(newBody.getPosition());
                this.children[childInx].add(newBody);
                //update the center of mass and change this.element
                //New celestial body is created and this.element is changed to the new celestial body.
                //Before that this.element was only one body(external node), but now it should be internal node (more bodies)
                this.element = this.element.add(newBody);
            }
        }
    }

    //creates method that calculates the force and updates it for each body
    //After we updated the force, we will create new octtree, but this will happen in the simulation
    public void updateAllForces() {
        calculateAllForces(this);
    }

    private void calculateAllForces(Octtree root) {
        if (isLeaf()) {
            if (this.element != null) {
                this.element.resetForce();
                calculateForce(root);
            }
        } else {
            for (Octtree child : this.children) {
                child.calculateAllForces(root);
            }
        }
    }

    //Barnes-Hut algorithm
    private void calculateForce(Octtree octtree) {
        if (octtree.element != null && !this.element.equals(octtree.element)) {
            if (octtree.element.isInternalNode()) {
                double size = octtree.cube.getCube().getSize();
                double x = Math.pow(octtree.element.getPosition().getX() - this.element.getPosition().getX(), 2);
                double y = Math.pow(octtree.element.getPosition().getY() - this.element.getPosition().getY(), 2);
                double z = Math.pow(octtree.element.getPosition().getZ() - this.element.getPosition().getZ(), 2);
                double distance = Math.sqrt(x + y + z);
                if (size / distance < THETA) {
                    Vector3D forceToAdd = this.element.gravitationalForce(octtree.element);
                    this.element.addForce(forceToAdd);
                } else {
                    for (Octtree child : octtree.children) {
                        if (child.element != null) {
                            calculateForce(child);
                        }
                    }
                }
            } else {
                Vector3D forceToAdd = this.element.gravitationalForce(octtree.element);
                this.element.addForce(forceToAdd);
            }
        }
    }

    public void move() {
        if (isLeaf()) {
            if (element != null) {
                element.move();
            }
        } else {
            for (Octtree child : children) {
                child.move();
            }
        }
    }

    public void draw() {
        if (isLeaf()) {
            if (element != null) {
                element.draw();
            }
        } else {
            for (Octtree child : children) {
                child.draw();
            }
        }
    }

    //find the right place of the new body by searching in each section of the cube
    private int getI(Vector3D bodyPosition) {

        for (int i = 0; i < 8; i++) {
            if (cube.getSections()[i].contains(bodyPosition)) {
                return i;
            }
        }

        return -1;
    }

    //proves whether the tree has children
    private boolean isLeaf() {
        return children == null;
    }

}
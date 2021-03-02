public class CelestialSystemIndexTree {


    private MyIndexNode root;
    private int countBodies;
    private int countSystems;

    // Adds a system of bodies to the tree. Since the keys of the tree are the names of bodies,
    // adding a system adds multiple (key, value) pairs to the tree, one for each body of the
    // system, with the same value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists in the tree
    // leaves the tree unchanged and the returned value would be 'false'.
    // For example, it is not allowed to have a system ("Earth", "Moon") and a system ("Jupiter",
    // "Moon") indexed by the tree, since "Moon" is not unique.
    // The method returns 'true' if the tree was changed as a result of the call and
    // 'false' otherwise.

    public MyIndexNode getRoot() {
        return root;
    }

    public boolean add(CelestialSystem system) {

        //proves if there is a body in the system, which already exists; in that case returns false
        for (int i = 0; i < system.size(); i++) {
            CelestialBody body = system.get(i);
            if (get(body.getName()) != null) { //if such body already exists return false
                return false;
            }
        }
        for (int i = 0; i < system.size(); i++) { //for each body in system
            CelestialBody body = system.get(i);
            if (this.root == null) {
                this.root = new MyIndexNode(body.getName(), system);
                countBodies++;
            } else {
                add(body, system, this.root);
                this.countBodies++;
            }
        }
        countSystems++;
        return true;

    }

    //implements recursive method to add a new body
    public void add(CelestialBody body, CelestialSystem system, MyIndexNode node){
         if(isBigger(body.getName(), node)){ //compares the new element body to the current node; if the name is bigger then it goes to the right
            if(node.getRightChild()==null){ //bottom of the recursion
                node.setRightChild(new MyIndexNode(body.getName(), system)); //adds the new element to the tree
            }else{ //if there are elements to the right; deep in to the recursion with the right side of the tree
                add(body,system,node.getRightChild());
            }

        }else if(isSmaller(body.getName(), node)){ //if the name is smaller then it goes to the right
            if(node.getLeftChild()==null){
                node.setLeftChild(new MyIndexNode(body.getName(), system));
            }else {
                add(body,system, node.getLeftChild());
            }

        }

    }

    // Returns the celestial system in which a body with the specified name exists.
    // For example, if the specified name is "Europa", the system of Jupiter (Jupiter, Io,
    // Europa, Ganymed, Kallisto) will be returned.
    // If no such system is found, 'null' is returned.
    public CelestialSystem get(String name) {

        MyIndexNode current = this.root;
        CelestialSystem result = findSystem(name,current);
        return result;

    }
    //Returns with recursion the celestial system in which a body with the specified name exists
    public CelestialSystem findSystem(String name, MyIndexNode current){
        if(current==null){
            return null;
        }
        if(isBigger(name,current)){
            return findSystem(name,current.getRightChild());
        }else if(isSmaller(name,current)){
            return findSystem(name, current.getLeftChild());
        }else if(isEqual(name, current)){
            return current.getCelestialValue();
        }
        return null;
    }

    // Returns the overall number of bodies indexed by the tree.
    public int numberOfBodies() {

        return this.countBodies;

    }

    // Returns the overall number of systems indexed by the tree.
    public int numberOfSystems() {

        return this.countSystems;

    }

    //prints all nodes of the tree
   public void printInOrder(MyIndexNode node){
        if(node!=null){
            printInOrder(node.getLeftChild());
            System.out.println(node.getNameKey());
            printInOrder(node.getRightChild());
        }
   }
    //Compares two bodies according to their names
    public boolean isBigger(String body, MyIndexNode node){
        return body.compareTo(node.getNameKey())>0; //if body> node returns true
    }

    public boolean isSmaller(String body, MyIndexNode node){
        return body.compareTo(node.getNameKey())<0; //if body< node returns true
    }

    public boolean isEqual(String body, MyIndexNode node){
        return body.compareTo(node.getNameKey())==0; //if body == node returns true
    }


    /*
    Zusatzfrage: Um auch mit den Namen von Systemen zu suchen, muss man bei meiner Lösung die Methoden isBigger, isSmaller und isEqual
    verändern, sodass auch den Wert von dem Knoten verglichen wird. Das kann so aussehen:
            return (body.compareTo(node.getNameKey())<0) || (body.compareTo(node.celestialValue())<0);

     */
}



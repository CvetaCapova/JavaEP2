public class ComplexCelestialSystem {

    //TODO: Define variables.
    private MySystemNode head;
    private MySystemNode tail;
    private int size;
    private String name;

    // Initializes this system as an empty system with a name.
    public ComplexCelestialSystem(String name) {
        //TODO: implement constructor.
        this.name = name;
    }

    // Adds a subsystem of bodies to this system if there are no bodies in the subsystem
    // with the same name as a body or subsystem of this system.
    // The method returns 'true' if the collection was changed as a result of the call and
    // 'false' otherwise.
    public boolean add(CelestialSystem subsystem) {
        //TODO: implement method.
        MySystemNode currentNode = new MySystemNode(subsystem);

        if(this.size == 0){  //if the list is empty, the new element is head and tail
            this.head = this.tail = currentNode;
            this.size+=subsystem.size();
            return true;
        }else if(get(subsystem.getName())==null){ //if the list does not already contain a body with the same name as 'body'
            this.tail.setNext(currentNode); // the last node(tail) has reference to the new head
            this.tail = currentNode; //the new tail is the currentNode
            this.size+=subsystem.size();
            return true;
        }


        return false;
    }

    // Returns the single body or subsystem with 'name' or 'null' if no such body or subsystem 
    // exists in this system. In case of a single body, the body is returned as a subsystem of 
    // one body, with the same name as the body.
    public CelestialSystem get(String name) {
        //TODO: implement method.
        MySystemNode currentNode = this.head;

        while (currentNode!= null){
            //if there are bodies in the subsystem with the same name as a body or subsystem of this system
            if(currentNode.getElement().getName().equals(name) ) { //if the subsystem currentNode is with the same name
                return currentNode.getElement();
            }else if(currentNode.getElement().get(name)!=null){ //if there is in the subsystem currentNode a body with the same name
                CelestialSystem result = new CelestialSystem(name);
                result.add(currentNode.getElement().get(name));
                return result;
            }else {
                currentNode = currentNode.getNext();
            }
        }

        return null;
    }

    // Returns the number of bodies of the entire system.
    public int size() {
        //TODO: implement method.
        return this.size;
    }

    //TODO: Define additional class(es) implementing a linked list (either here or outside class).
}



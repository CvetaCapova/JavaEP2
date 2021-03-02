import java.util.Objects;

public class CelestialSystem implements CelestialBodyCollection {

    //TODO: Define variables.
    private MyNode head;
    private MyNode tail;
    private int size;
    private String name;


    // Initialises this system as an empty system with a name.
    public CelestialSystem() {

    }

    public CelestialSystem(String name) {
        //TODO: implement constructor.
        this.name = name;
    }

    public CelestialSystem (CelestialSystemIndexTreeVariantC tree){
        for (CelestialBody body : tree) {
            this.add(body);
        }


    }


    public String getName() {
        return name;
    }


    // Adds 'body' to the list of bodies of the system if the list does not already contain a
    // body with the same name as 'body', otherwise does not change the object state. The method
    // returns 'true' if the list was changed as a result of the call and 'false' otherwise.
    public boolean add(CelestialBody body) { //method for appending a new element as the list tail
        //TODO: implement method.
        MyNode currentNode = new MyNode(body);
        if (body == null) {
            return false;
        }

        if (this.size == 0) {  //if the list is empty, the new element is head and tail
            this.head = this.tail = currentNode;
            this.size++;
            return true;
        } else if (get(body.getName()) == null) { //if the list does not already contain a body with the same name as 'body'
            currentNode.setPrevious(this.tail);
            this.tail.setNext(currentNode); // the last node(tail) has reference to the new head
            this.tail = currentNode; //the new tail is the currentNode
            this.size++;
            return true;
        }


        return false;
    }

    // Inserts the specified 'body' at the specified position
    // in this list if 'i' is a valid index and there is no body
    // in the list with the same name as that of 'body'.
    // Shifts the element currently at that position (if any) and
    // any subsequent elements to the right (adds one to their
    // indices). The first element of the list has the index 0.
    // Returns 'true' if the list was changed as a result of
    // the call, 'false' otherwise.
    public boolean add(int i, CelestialBody body) {
        //TODO: implement method.
        if (i == this.size) {
            this.add(body);
        } else if (getNode(i) != null && get(body.getName()) == null) {
            MyNode current = getNode(i);
            MyNode newNode = new MyNode(body);
            if(i==0){
                head = newNode;
            }else{
                MyNode prev = current.getPrevious();
                newNode.setPrevious(prev);
                prev.setNext(newNode);
            }

            newNode.setNext(current);
            current.setPrevious(newNode);
            size++;


            return true;
        }
        return false;
    }


    // Returns the 'body' with the index 'i'. The body that was first added to the list has the
    // index 0, the body that was most recently added to the list has the largest index (size()-1).
    public CelestialBody get(int i) {
        //TODO: implement method.
        if (i < this.size) { //proves whether i is valid index
            MyNode currentNode = this.head;

            for (int j = 0; j < i; j++) { //reaches the i-element from the list
                currentNode = currentNode.getNext();
            }

            return currentNode.getElement();
        } else {
            return null;
        }

    }

    // Returns  MyNode with the index 'i'. The node that was first added to the list has the
    // index 0, the node that was most recently added to the list has the largest index (size()-1).
    public MyNode getNode(int i) {
        //TODO: implement method.
        if (i < this.size) { //proves whether i is valid index
            MyNode currentNode = this.head;

            for (int j = 0; j < i; j++) { //reaches the i-element from the list
                currentNode = currentNode.getNext();
            }

            return currentNode;
        } else {
            return null;
        }

    }

    // Returns the body with the specified name or 'null' if no such body exits in the list.
    public CelestialBody get(String name) {
        //TODO: implement method.
        MyNode currentNode = this.head;

        while (currentNode != null) {
            if (currentNode.getElement().getName().equals(name)) {
                return currentNode.getElement();
            } else {
                currentNode = currentNode.getNext();
            }
        }

        return null;
    }

    // Returns a new list that contains the same elements as this
// list in reverse order. The list 'this' is not changed and
// bodies are not duplicated (shallow copy).
    public CelestialSystem reverse() {
//TODO: implement method.
        CelestialSystem result = new CelestialSystem("Reversed");
        for (int i = this.size() - 1; i >= 0; i--) {
            result.add(new CelestialBody(get(i)));
        }

        return result;
    }

    // returns the number of entries of the list.
    public int size() {
        //TODO: implement method.
        return this.size;
    }

    // Returns a readable representation with the name of the
// system and all bodies in respective order of the list.
    @Override
    public String toString() {
//TODO: implement method.
        String result = "CelestialSystem name: " + this.getName() + ", Bodies: ";
        for (int i = 0; i < this.size; i++) {
            result += get(i).getName() + ", ";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CelestialSystem system = (CelestialSystem) o;

        if (size != system.size) {
            return false;
        }

        MyNode current = this.head;
        while (current != null) {
            String nameCurrent = current.getElement().getName();
            if (nameCurrent != null && system.get(nameCurrent) == null) {
                return false;
            }
            current = current.getNext();
        }

        return true;
    }

    @Override
    public int hashCode() {
        int h = size;
        MyNode current = this.head;
        while (current != null) {
            String nameCurrent = current.getElement().getName();
            if (nameCurrent != null) {
                h += Objects.hash(nameCurrent);
            }
            current = current.getNext();
        }
        return h;
    }

    @Override
    public CelestialBodyIterator iterator() {
        return new MyCelestialBodyListIterator(head);
    }




    //TODO: Define additional class(es) implementing the linked list (either here or outside class).
}

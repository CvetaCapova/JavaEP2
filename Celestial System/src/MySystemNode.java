public class MySystemNode {
    private MySystemNode next;
    CelestialSystem element;

    public MySystemNode(CelestialSystem element) {
        this.element = element;
    }

    public MySystemNode getNext() {
        return next;
    }

    public void setNext(MySystemNode next) {
        this.next = next;
    }

    public CelestialSystem getElement() {
        return element;
    }

    public void setElement(CelestialSystem element) {
        this.element = element;
    }
}

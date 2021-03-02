public class MyNode {

    private MyNode next;
    private MyNode previous;
    CelestialBody element;

    public MyNode(CelestialBody element) {
        this.element = element;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public MyNode getPrevious(){
        return previous;
    }

    public void setPrevious(MyNode previous){
        this.previous = previous;
    }

    public CelestialBody getElement() {
        return element;
    }

    public void setElement(CelestialBody element) {
        this.element = element;
    }
}

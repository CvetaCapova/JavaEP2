public class MyIndexNode {
    private String nameKey;
    private CelestialSystem celestialValue;
    private MyIndexNode leftChild;
    private MyIndexNode rightChild;

    public MyIndexNode(String nameKey, CelestialSystem celestialValue) {
        this.nameKey = nameKey;
        this.celestialValue = celestialValue;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public CelestialSystem getCelestialValue() {
        return celestialValue;
    }

    public void setCelestialValue(CelestialSystem celestialValue) {
        this.celestialValue = celestialValue;
    }

    public MyIndexNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(MyIndexNode leftChild) {
        this.leftChild = leftChild;
    }

    public MyIndexNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(MyIndexNode rightChild) {
        this.rightChild = rightChild;
    }
}

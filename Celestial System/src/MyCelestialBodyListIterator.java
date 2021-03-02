public class MyCelestialBodyListIterator implements CelestialBodyIterator{

    private MyNode node;

    public MyCelestialBodyListIterator(MyNode node){
        this.node = node;
    }

    @Override
    public boolean hasNext() {
        return node!=null;
    }

    @Override
    public CelestialBody next() {
        if(node==null){
            return null;
        }

        CelestialBody result = node.getElement();
        node = node.getNext();
        return result;
    }
}

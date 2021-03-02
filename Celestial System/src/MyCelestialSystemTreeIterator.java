import java.util.Iterator;

public class MyCelestialSystemTreeIterator implements CelestialBodyIterator {

    private VariantCNode node;
    private MyCelestialSystemTreeIterator parent;
    public MyCelestialSystemTreeIterator(){

    }

    public MyCelestialSystemTreeIterator(VariantCNode node, MyCelestialSystemTreeIterator parent ){
        this.node = parent.node;
        parent.node = node;
        this.parent = parent.parent;
        parent.parent = this;
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

        VariantCNode currentNode = node;
        node = parent.node;
        parent = parent.parent;
        return currentNode.iter(this,true);
    }
}

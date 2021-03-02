public class MyTreeVariantCollection implements CelestialBodyCollection {

    private CelestialSystemIndexTreeVariantC tree;

    public MyTreeVariantCollection(CelestialSystemIndexTreeVariantC tree){
        this.tree = tree;
    }
    @Override
    public boolean add(CelestialBody body) {
        return false;
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public CelestialBodyIterator iterator() {
        return tree.iterator();
    }
}

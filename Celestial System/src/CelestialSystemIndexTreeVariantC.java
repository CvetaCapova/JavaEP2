//TODO: change class definition below according to specification in 'Aufgabenblatt6'.

public class CelestialSystemIndexTreeVariantC implements CelestialSystemIndex, CelestialBodyIterable {

    private VariantCNode root;
    private CelestialBodyComparator comparator;
    private int size;

    // Initialises this index with a 'comparator' for sorting
    // the keys of this index.
    public CelestialSystemIndexTreeVariantC(CelestialBodyComparator comparator) {
        this.comparator = comparator;
    }

    // Adds a system of bodies to the index.
    // Adding a system adds multiple (key, value) pairs to the
    // index, one for each body of the system, with the same
    // value, i.e., reference to the celestial system.
    // An attempt to add a system with a body that already exists
    // in the index leaves the index unchanged and the returned
    // value would be 'false'.
    // The method returns 'true' if the index was changed as a
    // result of the call and 'false' otherwise.
    public boolean add(CelestialSystem system) {

        if (system == null || system.size() == 0) {
            return false;
        }

        for (CelestialBody body : system) {
            if (contains(body)) {
                return false;
            }
        }

        boolean changed = false;

        if (root == null) {
            root = new VariantCNode(system.get(0), system, null, null, comparator);
            changed = true;
        } else {
            changed = root.add(new VariantCNode(system.get(0), system, null, null, comparator));
        }

        for (int i = 1; i < system.size(); i++) {
            changed |= root.add(new VariantCNode(system.get(i), system, null, null, comparator));
        }
        size += system.size();

        return changed;

    }

    // Returns the celestial system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.
    public CelestialSystem get(CelestialBody body) {
        if (root == null) {
            return null;
        }

        return root.get(body);

    }

    // Returns 'true' if the specified 'body' is listed
    // in the index.
    public boolean contains(CelestialBody body) {
        return get(body) != null;
    }

    // Returns a readable representation with (key, value) pairs sorted by the key.
    public String toString() {
        if (root == null) {
            return "{}";
        }

        return "{" + root.toString() + "}";
    }

    // Returns a collection view of all entries of this index.
    public CelestialBodyCollection bodies() {
        return new MyTreeVariantCollection(this);

    }

    // Returns all entries of this as a new collection.
    public CelestialSystem bodiesAsCelestialSystem() {
        return new CelestialSystem(this);
    }

    // Returns the comparator used in this index.
    public CelestialBodyComparator getComparator() {
        return comparator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == null) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        CelestialSystemIndexTreeVariantC treeVariantC = (CelestialSystemIndexTreeVariantC) o;

        if (size != treeVariantC.size) {
            return false;
        }

        for (CelestialBody body : treeVariantC) {
            if(!contains(body)){
                return false;
            }else {
                if(get(body)!=treeVariantC.get(body)){
                    return false;
                }
            }
        }

        return true;

    }

    @Override
    public int hashCode() {
        int h = size;
        CelestialSystem reference = bodiesAsCelestialSystem();
        for (CelestialBody body : reference) {
            size+=body.hashCode();
        }
        return h;
    }

    @Override
    public CelestialBodyIterator iterator() {
        MyCelestialSystemTreeIterator iterator = new MyCelestialSystemTreeIterator();
        if (root != null) {
            root.iter(iterator, false);
        }
        return iterator;
    }

    public int size() {
        return size;
    }

}

class VariantCNode {
    private VariantCNode left;
    private VariantCNode right;
    private CelestialBody key;
    private CelestialSystem cs;
    private CelestialBodyComparator comparator;

    public VariantCNode(CelestialBody key, CelestialSystem cs, VariantCNode left, VariantCNode right,
                        CelestialBodyComparator comparator) {
        this.key = key;
        this.cs = cs;
        this.left = left;
        this.right = right;
        this.comparator = comparator;

    }

    public boolean add(VariantCNode node) {
        if (node.key.equals(this.key)) {
            return false;
        }

        if (this.comparator.compare(this.key, node.key) > 0) {
            if (left == null) {
                left = node;
                return true;
            } else {
                return left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
                return true;
            } else {
                return right.add(node);
            }
        }

    }

    public CelestialSystem get(CelestialBody body) {
        if (key.equals(body)) {
            return cs;
        }

        if (this.comparator.compare(this.key, body) > 0) {
            if (left == null) {
                return null;
            }
            return left.get(body);
        } else {
            if (right == null) {
                return null;
            }
            return right.get(body);
        }

    }

    public String toString() {
        String result;
        result = left == null ? "" : left.toString();
        result += result.isEmpty() ? "" : ",\n";
        result += this.key + " belongs to " + this.cs;
        result += right == null ? "" : ",\n" + right.toString();
        return result;

    }

    public CelestialBody iter(MyCelestialSystemTreeIterator iterator, boolean next) {

        VariantCNode currentNode = next ? right : this;

        while (currentNode != null) {
            new MyCelestialSystemTreeIterator(currentNode, iterator);
            currentNode = currentNode.left;
        }
        return this.key;
    }

}

/*
Zusatzfragen:
1) Nach move() werden sich sowohl die Objekten in Collection (von bodies() erzeugt), als auch die Objekten im CelestialSystem
 (von bodiesAsCelestialSystem erzeugt) ihre Position verändern. Jedoch infolge der Veränderung von Collection,
 werden sich auch die Objekte im Suchbaum verändern(da Collection eine Referen zum Suchbaum ist).
 Wenn wir etwas im CelestialSystem verändern, ändern sich die Objekten in dem Baum nicht, da CelestialSystem eine Kopie vom Baum ist.
 2) Ihre Iteratoren werden sich auch verändern, weil sie ihre Sichtweise sind.
 */



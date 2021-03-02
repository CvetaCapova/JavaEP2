// A possible solution of task 3 of AB6.

import java.util.NoSuchElementException;

public class CelestialSystemIndexTreeVariantD implements CelestialSystemIndex, CelestialBodyIterable {

    private VariantDNode root;
    private CelestialBodyComparator comparator;

    // Initialises this index with a 'comparator' for sorting
    // the keys of this index.
    public CelestialSystemIndexTreeVariantD(CelestialBodyComparator comparator) {
        //comparator == null
        this.comparator = comparator;
        //this.comparator == comparator;

        //root == null
        this.root = VariantDNullNode.NIL; // NIL in the beginning.
        //root == VariantDNullNode
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

        //system == null || system.size ==0 || system.size > 0
        if (system == null || system.size() == 0) {
            //(system == null || system.size == 0) && !system.size>0
            return false;
        }

        //system size >0
        //i <system.size || i> system.size
        for (int i = 0; i < system.size(); i++) {
            //i<system size && i= i+1

            //system.get(i)!=null
            if (root.get(system.get(i)) != null) {
                    return false;
            }

            //root.get(system.get(i)) = null
        }

        //root == null || root!= null
        //system.get(0)!=null
        root = root.add(new VariantDNonNullNode(system.get(0), system, comparator));
        //root != null && (root.left== VariantDNullNode || root.left == VariantDNonNullNode || root.right == VariantDNullNode || root.right == VariantDNonNullNode)

        //i <system.size || i> system.size
        for (int i = 1; i < system.size(); i++) {
            //i<system size && i= i+1

            //root!= null
            //system.get(i)!=null
            root = root.add(new VariantDNonNullNode(system.get(i), system, comparator));
            //root!= null && (root.left== VariantDNullNode || root.left == VariantDNonNullNode || root.right == VariantDNullNode || root.right == VariantDNonNullNode)

        }

        return true;

    }

    // Returns the celestial system with which a body is
    // associated. If body is not contained as a key, 'null'
    // is returned.

    //body!= null
    public CelestialSystem get(CelestialBody body) {

        //(root==null || root == VariantDNonNullNode) && body!=null
        return root.get(body);

    }

    // Returns 'true' if the specified 'body' is listed
    // in the index.
    public boolean contains(CelestialBody body) {

        //body!=null
        return get(body) != null;
    }

    // Returns a readable representation with (key, value) pairs sorted by the key.
    public String toString() {

        return "{" + root.toString() + "}";
    }

    // Returns the comparator used in this index.
    public CelestialBodyComparator getComparator() {
        return comparator;
    }

    @Override
    // Returns an iterator iterating over all celestial bodies of this index.
    public CelestialBodyIterator iterator() {

        return root.iterator(new VariantDNodeIterator(null, null));
    }
}

interface VariantDNode {

    // Adds the specified 'node' to the tree of which 'this' is the root
    // node. If the tree already has a node with the same key as that
    // of 'node' the tree remains unchanged.

    //pre: node!= null
    //post: if node< root => added to the left
    //post: if node> root => added to the right
    //post: if this==null => tree not changed
    VariantDNode add(VariantDNode node);

    // Returns the celestial system with which a body is associated, if 'body' is a key
    // which is contained in the this tree (the tree of which 'this' is the root node).
    // If body is not contained as a key, 'null' is returned.

    //pre: body!= null
    //post: if body in tree => celestial system with the body returned
    //post: if body not in the tree => null returned
    //post: if this == null => null returned
    CelestialSystem get(CelestialBody body);

    // Returns a readable representation of the tree of which 'this' is the root node.


    //post: string is returned
    //post: if this == null => ""returned
    String toString();

    // Returns an iterator over all keys of the tree of which 'this' is the root node.
    // 'parent' is the iterator of the parent (path from the root).

    //pre: VariantDNodeIterator parent initialised
    //post: parent is returned, with node = this || node = null;
    //post: no values are changed
    VariantDNodeIterator iterator(VariantDNodeIterator parent);

    // Returns the key of this node.

    //pre: node has key
    //post: CelestialBody is returned
    //post: if node==null => null is returned
    CelestialBody getKey();

}

// Implements a terminal node with no content (used instead of 'null').
class VariantDNullNode implements VariantDNode {

    // singleton: only one instance is needed, because the
    // state of 'this' can not be changed.
    public static final VariantDNullNode NIL = new VariantDNullNode();

    // private to avoid object creation from outside
    private VariantDNullNode() {}

    //pre: (node!= null) || (node == null)
    //post: tree not changed
    public VariantDNode add(VariantDNode node) {
        return node;
    }

    //pre: body!= null || body==null
    //post: null returned
    public CelestialSystem get(CelestialBody body) {
        return null;
    }

    //post: "" returned
    public String toString() {
        return "";
    }


    //pre: VariantDNodeIterator parent initialised
    //post: parent is returned, with no changes
    //post: no values are changed
    public VariantDNodeIterator iterator(VariantDNodeIterator parent) {
        return parent;
    }

    //post: null is returned
    public CelestialBody getKey() {
        return null;
    }

}

class VariantDNonNullNode implements VariantDNode {
    private VariantDNode left;
    private VariantDNode right;
    private CelestialBody key;
    private CelestialSystem cs;
    private CelestialBodyComparator comparator;

    public VariantDNonNullNode(CelestialBody key, CelestialSystem cs,
                               CelestialBodyComparator comparator) {
        this.key = key;
        this.cs = cs;
        this.left = VariantDNullNode.NIL;
        this.right = VariantDNullNode.NIL;
        this.comparator = comparator;

    }

    //pre: node!= null
    //post: node added to tree
    //post: if node< root => added to the left
    //post: if node> root => added to the right
    public VariantDNode add(VariantDNode node) {

        int comp = this.comparator.compare(this.key, node.getKey());
        if (comp > 0) {
            left = left.add(node);
        } else {
            if (comp < 0) {
                right = right.add(node);
            }
        }

        return this;
    }

    //pre: body!= null
    //pre: this!=null  !!!Fehler- Die Vorbedingung ist stärker
    //post: if body in tree => celestial system with the body returned
    //post: if body not in the tree => null returned
    public CelestialSystem get(CelestialBody body) {
        if (key.equals(body)) {
            return cs;
        }

        if (this.comparator.compare(this.key, body) > 0) {
            return left.get(body);
        } else {
            return right.get(body);
        }

    }

    //pre: this!= null
    //post: string is returned with at least "{this.key} belongs to {this.cs}"
    public String toString() {
        String result;
        String right = this.right.toString();
        result = this.left.toString();
        result += result.isEmpty() ? "" : ",\n";
        result += this.key + " belongs to " + this.cs;
        result += right.isEmpty() ? "" : ",\n";
        result += right;
        return result;

    }

    //pre: node has key
    //post: CelestialBody is returned
    public CelestialBody getKey() {
        return key;
    }

    //pre: VariantDNodeIterator parent initialised
    //post: new Iterator is returned, with node = this;
    //post: no values are changed
    public VariantDNodeIterator iterator(VariantDNodeIterator next) {
        return left.iterator(new VariantDNodeIterator(this, next));
    }

    //pre: VariantDNodeIterator next initialised
    //post: next value is read
    public VariantDNodeIterator nextStep(VariantDNodeIterator next) {
        return right.iterator(next);
    }

}

class VariantDNodeIterator implements CelestialBodyIterator {
    private VariantDNonNullNode node;
    private VariantDNodeIterator next;

    public VariantDNodeIterator(VariantDNonNullNode node, VariantDNodeIterator next) {
        this.node = node;
        this.next = next;
    }

    @Override
    public boolean hasNext() {
        return node != null;
    }

    @Override
    public CelestialBody next() {
        if (hasNext()) {
            CelestialBody key = node.getKey();
            next = node.nextStep(next);
            node = next.node;
            next = next.next;
            return key;
        }
        throw new NoSuchElementException("This index has no (more) entries!");
    }

}



// TODO: define class according to 'Aufgabenblatt5'.

public class CelestialSystemIndexMap implements CelestialSystemIndex {
    private CelestialBody[] keys = new CelestialBody[65]; //65. for null
    private CelestialSystem[] values = new CelestialSystem[65];
    private int size;

    public CelestialSystemIndexMap() {

    }

    @Override
    public boolean add(CelestialSystem system) {
        //proves if there is a body in the system, which already exists; in that case returns false
        if (system == null) {
            return false;
        }
        for (int i = 0; i < system.size(); i++) {
            CelestialBody body = system.get(i);
            if (contains(body)) { //if such body already exists return false
                return false;
            }
        }

        //Add each body from CelestialSystem to CelestialSystemIndex
        for (int i = 0; i < system.size(); i++) {
            CelestialBody body = system.get(i);
            int index = find(body);
            keys[index] = body; // new body is added
            values[index] = system; //the value of position index is changed
            size++;
            //if 80% from the array is full, double the length
            if (size >= 0.8 * keys.length) {
                CelestialBody[] oldKeys = keys;
                CelestialSystem[] oldValues = values;
                int length = keys.length * 2 - 1;
                keys = new CelestialBody[length];
                values = new CelestialSystem[length];
                for (int j = 0; j < oldKeys.length; j++) {
                    if (oldKeys[j] != null) {
                        int newIndex = find(oldKeys[j]);
                        keys[newIndex] = oldKeys[j];
                        values[newIndex] = oldValues[j];
                    }
                }

            }
        }

        return true;

    }

    @Override
    public CelestialSystem get(CelestialBody body) {
        return values[find(body)];

    }

    @Override
    public boolean contains(CelestialBody body) {
        if (body == null) {
            return false;
        }
        int i = Math.abs(body.hashCode() % (keys.length - 2));  //Math.abs in case hashCode is negative
        boolean found = false;

        int count = keys.length-1; //helps to search trough all elements, if necessary

        while (count!=0){
            if (keys[i] != null && keys[i].equals(body)) { //this element already exist in the same position
                return true;
            }
            i = (i + 1) % (keys.length - 2); //prove the next one; if it is at the end, start from beginning
            count--;
        }

        return found;


    }

    //Returns index of the key
    private int find(CelestialBody key) {
        if (key == null) {
            return keys.length - 1;
        }
        int i = Math.abs(key.hashCode() % (keys.length - 2));  //lineares Sondieren, calculates the position , Math.abs in case hashCode is negative

        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i + 1) % (keys.length - 2); //prove the next one; if it is at the end, start from beginning

        }

        return i;
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                text += "Key: " + keys[i].getName() + " Value: " + values[i].getName() + "; ";
            }
        }
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CelestialSystemIndexMap system = (CelestialSystemIndexMap) o;

        if(size!=system.size){
            return false;
        }

        for (int i = 0; i < system.keys.length; i++) {
            if (system.keys[i] != null) {
                String nameKey = system.keys[i].getName();
                if (nameKey != null && !contains(system.keys[i])) {
                    return false;
                }
            }

        }

        return true;

    }

    @Override
    public int hashCode() {
        int h = size;
        for (int i = 0; i < keys.length; i++) {
            if(keys[i]!=null){
                if (keys[i].getName() != null) {
                    h += keys[i].hashCode();
                }
            }

        }

        return h;
    }


}

/*
1. Die CelestialBodies würden nicht korrekt verlichen werden (nicht mit Namen). Folglich würden die Methoden find und equals falsche Ergebnisse liefern.
Auf dieser Weise könnten z.B. mehrere Objekten mit dem gleichen Namen gespeichert werden.
2. Wenn nur hashCode gelöscht wird, wird derselbe Fehler passieren. Jedoch wird equals nie funktionieren, weil equals die Namen vergleicht, aber hashCode
wird nicht nur mit hashCode des Namens berechnet.
3. Bedingungen für equals:
    Reflexivität: x.equals(x) ist immer true;
    Symmetrie: x.equals(y) = y.equals(x);
    Transitivität: x.equals(y) y.equals(z) => x.equals(z);
    x.equals(null) ist immer false;
    x.equals(y) liefert immer dasselbe Ergebnis, falls x oder y nicht geändert sind

    Bedingungen für hashCode:
    falls x.equals(y) => x.hashCode() == y.hashCode();
    x.hashCode() liefert immer dasselbe Ergebnis, falls x nicht geändert ist
4. In meiner Lösung liefert x.toString().equals(y.toString()) true. Wenn diese Bedingung nicht erfüllt ist, könnte es so sein,
    dass Systemen mit gleichen Objekte nicht gleich sind.
5.  Im CelestialSystemIndexTree sollte die Methode get statt String CelestialBody als Parameter bekommen.
    Darüber hinaus soll auch die Methode contains implementiert werden.

 */
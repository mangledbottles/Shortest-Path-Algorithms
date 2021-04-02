import java.util.HashSet;

class Streets {
    public HashSet<Street>[] adj;
    private static int intersectionsQuantity, streetsQuantity;

    Streets(int intersectionsQuantity, int streetsQuantity) {
        Streets.intersectionsQuantity = intersectionsQuantity;
        Streets.streetsQuantity = streetsQuantity;

        adj = (HashSet<Street>[]) new HashSet[intersectionsQuantity];
        for (int v = 0; v < intersectionsQuantity; v++)
            adj[v] = new HashSet<Street>();
    }

    void insertStreet(int intersectionA, int intersectionB, double streetLength) {
        try {
            isVertexValid(intersectionA);
            isVertexValid(intersectionB);

            Street s = new Street(intersectionA, intersectionB, streetLength);
            adj[intersectionA].add(s);
        } catch(Exception e) {
            System.out.println("Error inserting edge");
        }
    }

    private void isVertexValid(int vertex) {
        if (vertex < 0 || vertex >= intersectionsQuantity) {
            throw new IllegalArgumentException("Vertex " + vertex + "  has an invalid range," +
                    "must be between 0 and " + (intersectionsQuantity - 1));
        }
    }

    static int getStreetsQuantity() {
        return streetsQuantity;
    }

    static int getIntersectionsQuantity() {
        return intersectionsQuantity;
    }

}
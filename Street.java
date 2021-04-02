class Street {
    int intersectionA;
    int intersectionB;
    double streetLength;

    Street(int intersectionA, int intersectionB, double streetLength) {
        this.intersectionA = intersectionA;
        this.intersectionB = intersectionB;
        this.streetLength = streetLength;
    }
    double getStreetLength() {
        return this.streetLength;
    }
}
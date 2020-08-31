package by.epam.course.multithreading.entity;

public class Ferry {
    private static final int DEFAULT_CARRYING = 15000;
    private static final int DEFAULT_AREA = 500;
    private int ferryCarrying;
    private int ferryArea;

    public Ferry() {
        this.ferryCarrying = DEFAULT_CARRYING;
        this.ferryArea = DEFAULT_AREA;
    }

    public Ferry(int ferryCarrying, int ferryArea) {
        this.ferryCarrying = ferryCarrying;
        this.ferryArea = ferryArea;
    }

    public int getferryCarrying() {

        return ferryCarrying;
    }

    public int getferryArea() {
        return ferryArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ferry ferry = (Ferry) o;

        if (ferryCarrying != ferry.ferryCarrying) return false;
        return ferryArea == ferry.ferryArea;
    }

    @Override
    public int hashCode() {
        int result = ferryCarrying;
        result = 31 * result + ferryArea;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ferry{");
        sb.append("ferryCarrying=").append(ferryCarrying);
        sb.append(", ferryArea=").append(ferryArea);
        sb.append('}');
        return sb.toString();
    }
}

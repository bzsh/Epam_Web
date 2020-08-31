package by.epam.course.shape.entity;

import by.epam.course.shape.action.TetrahedronCalc;

public class TetrahedronCharacteristic {
    private int id;
    private double square;
    private double volume;
    private double perimeter;

    public TetrahedronCharacteristic(int id, double square, double volume, double perimeter) {
        this.id = id;
        this.square = square;
        this.volume = volume;
        this.perimeter = perimeter;
    }

    public TetrahedronCharacteristic(Tetrahedron tetrahedron) {
        TetrahedronCalc calculator = new TetrahedronCalc();
        this.id = tetrahedron.getId();
        this.square = calculator.calculateSquare(tetrahedron);
        this.volume = calculator.calculateVolume(tetrahedron);
        this.perimeter = calculator.calculatePerimeter(tetrahedron);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TetrahedronCharacteristic that = (TetrahedronCharacteristic) o;
        return id == that.id &&
                Double.compare(that.square, square) == 0 &&
                Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.perimeter, perimeter) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int PRIME = 31;
        hash *= PRIME + Double.valueOf(square);
        hash *= PRIME + Double.valueOf(volume);
        hash *= PRIME + Double.valueOf(perimeter);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TetrahedronCharacteristic{");
        sb.append("id=").append(id);
        sb.append(", square=").append(square);
        sb.append(", volume=").append(volume);
        sb.append(", perimeter=").append(perimeter);
        sb.append('}');
        return sb.toString();
    }
}

package by.epam.course.shape.entity;

import by.epam.course.shape.observer.Observable;
import by.epam.course.shape.observer.Observer;
import by.epam.course.shape.observer.TetrahedronObserver;
import by.epam.course.shape.util.IdCreator;

import java.util.List;

public class Tetrahedron implements Observable {
    private Observer observer = new TetrahedronObserver("TetraObserver");
    private String name;
    private List<Point> points;
    private int id;

    public Tetrahedron(int id, List<Point> points, String name) {
        this.id = id;
        this.points = points;
        this.name = name;

    }

    public static Tetrahedron createTetrahedron(List<Point> points) {
        int id = IdCreator.createId();
        String name = "Tetrahedron_" + id;
        return new Tetrahedron(id, points, name);
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getPointFromList(int index) {
        return points.get(index);
    }


    public void setPoints(List<Point> points) {
        this.points = points;
        notifyObserver();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetrahedron that = (Tetrahedron) o;
        return id == that.id &&
                points.get(0).equals(that.points.get(0)) &&
                points.get(1).equals(that.points.get(1)) &&
                points.get(2).equals(that.points.get(2)) &&
                points.get(3).equals(that.points.get(3));
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int PRIME = 31;
        hash *= PRIME + points.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tetrahedron{");
        sb.append("points=").append(points);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void notifyObserver() {
        observer.update(this);
    }
}

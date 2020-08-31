package by.epam.course.multithreading.entity;

import by.epam.course.multithreading.state.CarState;

public class Car {

    private int id;
    private CarType type;
    private int weight;
    private int area;
    private CarState state;

    public Car() {

    }

    public Car(int id, CarType type, int weight, int area ) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.area = area;
        state = CarState.WAITING_FOR_LOADING;
    }

    public long getId() {
        return id;
    }

    public CarType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public int getArea() {
        return area;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (weight != car.weight) return false;
        if (area != car.area) return false;
        if (type != car.type) return false;
        return state == car.state;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + area;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", weight=").append(weight);
        sb.append(", area=").append(area);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}

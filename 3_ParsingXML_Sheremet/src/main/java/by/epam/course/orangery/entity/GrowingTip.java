package by.epam.course.orangery.entity;

public class GrowingTip {

    private int temperature;
    private boolean lighting;
    private int watering;

    public GrowingTip(){

    }

    public GrowingTip(int temperature, boolean lighting, int watering) {
        this.temperature = temperature;
        this.lighting = lighting;
        this.watering = watering;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        GrowingTip that = (GrowingTip) o;

        if (temperature != that.temperature) return false;
        if (lighting != that.lighting) return false;
        return watering == that.watering;
    }

    @Override
    public int hashCode() {
        int result = temperature;
        result = 31 * result + (lighting ? 1 : 0);
        result = 31 * result + watering;
        return result;
    }

    @Override
    public String toString() {
        return "GrowingTip{" +
                "temperature=" + temperature +
                ", lighting=" + lighting +
                ", watering=" + watering +
                '}';
    }
}
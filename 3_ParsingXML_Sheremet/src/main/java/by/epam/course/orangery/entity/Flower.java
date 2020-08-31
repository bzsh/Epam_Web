package by.epam.course.orangery.entity;

import java.time.LocalDate;

public class Flower {
    private String id;
    private String name;
    private Soil soil;
    private String origin;
    private Visual visual;
    private LocalDate dateLanding;
    private GrowingTip growingTip;
    private Multiplying multiplying;

    public Flower(){
        visual = new Visual();
        growingTip = new GrowingTip();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Visual getVisual() {
        return visual;
    }

    public void setVisual(Visual visual) {
        this.visual = visual;
    }

    public LocalDate getDateLanding() {
        return dateLanding;
    }

    public void setDateLanding(LocalDate dateLanding) {
        this.dateLanding = dateLanding;
    }

    public GrowingTip getGrowingTip() {
        return growingTip;
    }

    public void setGrowingTip(GrowingTip growingTips) {
        this.growingTip = growingTips;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower flower = (Flower) o;

        if (id != null ? !id.equals(flower.id) : flower.id != null) return false;
        if (name != null ? !name.equals(flower.name) : flower.name != null) return false;
        if (soil != flower.soil) return false;
        if (origin != null ? !origin.equals(flower.origin) : flower.origin != null) return false;
        if (visual != null ? !visual.equals(flower.visual) : flower.visual != null) return false;
        if (dateLanding != null ? !dateLanding.equals(flower.dateLanding) : flower.dateLanding != null) return false;
        if (growingTip != null ? !growingTip.equals(flower.growingTip) : flower.growingTip != null) return false;
        return multiplying == flower.multiplying;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (soil != null ? soil.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (visual != null ? visual.hashCode() : 0);
        result = 31 * result + (dateLanding != null ? dateLanding.hashCode() : 0);
        result = 31 * result + (growingTip != null ? growingTip.hashCode() : 0);
        result = 31 * result + (multiplying != null ? multiplying.hashCode() : 0);
        return result;
    }
}


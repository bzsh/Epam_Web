package by.epam.course.shape.specification;

import by.epam.course.shape.action.TetrahedronCalc;
import by.epam.course.shape.entity.Tetrahedron;

public class VolumeSpecification implements Specification {
    double minVolume;
    double maxVolume;

    public VolumeSpecification(double minVolume, double maxVolume) {
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
    }

    @Override
    public boolean specify(Tetrahedron tetrahedron) {
        TetrahedronCalc calculator = new TetrahedronCalc();
        double volume = calculator.calculateVolume(tetrahedron);
        return minVolume < volume && volume < maxVolume;
    }
}

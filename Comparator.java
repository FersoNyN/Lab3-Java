import java.util.Comparator;

// Comparator for sorting cars by license plate
class PlateComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return c1.getLicensePlate().compareTo(c2.getLicensePlate());
    }
}

// Comparator for sorting cars by brand
class BrandComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return c1.getBrand().compareTo(c2.getBrand());
    }
}

// Comparator for sorting cars by year of production
class YearComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return Integer.compare(c1.getYearOfProduction(), c2.getYearOfProduction());
    }
}

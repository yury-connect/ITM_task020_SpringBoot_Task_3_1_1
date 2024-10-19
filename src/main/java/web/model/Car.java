package web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    private int id;

    private String model;

    private String color;

    private Date releaseDate; // Дата выпуска


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(releaseDate, car.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, color, releaseDate);
    }
}

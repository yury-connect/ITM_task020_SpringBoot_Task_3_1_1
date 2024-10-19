package web.util;

import net.datafaker.Faker;
import web.model.Car;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public  class CarUtils {

    public static List<Car> generateCar(int count) {
        List<Car> cars = Stream.generate(() -> generateCar())
                .limit(count) // Количество элементов в списке
                .collect(Collectors.toList());
        return cars;
    }


        public static Car generateCar() {
        Faker faker = new Faker(new Locale("ru"));
//        Transliterator transliterator = Transliterator.getInstance("Russian-Latin/BGN");   // Создание транслитератора

        String model = faker.vehicle().make();
        String color = faker.vehicle().color();

        final LocalDate startDate = LocalDate.of(1970, 1, 1);
        final LocalDate endDate = LocalDate.of(2024, 10, 20);
        java.sql.Date data = Date.valueOf(generateRandomDate(startDate, endDate));

        return Car.builder()
                .model(model)
                .color(color)
                .releaseDate(data)
                .build();
    }

    private static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);
        return LocalDate.ofEpochDay(randomEpochDay);
    }
}

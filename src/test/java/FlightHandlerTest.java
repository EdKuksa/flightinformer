import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

class FlightHandlerTest {

    private final FlightHandler flightHandler = new FlightHandler();

    @Test
    void add() {
        String data = "504N 02/05/2023 18:30 3.30 KUF LED 7500";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        System.setOut(new PrintStream(OutputStream.nullOutputStream())); // Hide method output
        String result = flightHandler.add();
        Assertions.assertEquals("\nИнформация о рейсе 504N 02/05/2023 18:30 03.30 KUF LED 7500,00 добавлена\n", result);
    }

    @Test
    void show() {
        String data = "504N 02/05/2023 18:30 3.30 KUF LED 7500";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        flightHandler.add();
        data = "504N";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        String result = flightHandler.show();
        Assertions.assertEquals("Информация о рейсе: 504N 02/05/2023 18:30 03.30 KUF LED 7500,00", result);
    }

    @Test
    void showAll() {
        String data = "504N 02/05/2023 18:30 3.30 KUF LED 7500";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        flightHandler.add();
        data = "211U 05/05/2023 10:15 7.20 KZN MMK 15000";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        flightHandler.add();
        String result = flightHandler.showAll().toString();
        Assertions.assertEquals("Информация о рейсе: 504N 02/05/2023 18:30 03.30 KUF LED 7500,00\n"
                + "Информация о рейсе: 211U 05/05/2023 10:15 07.20 KZN MMK 15000,00", result);
    }
}
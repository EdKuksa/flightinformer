import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlightHandler {
    private List<Flight> flights = new ArrayList<>();
    private AirportsParser airportsParser = new AirportsParser();

    @SneakyThrows
    public void add() {
        String errorMessage = "";
        boolean containStatus = false;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            System.out.println("\nВведите данные рейса:");
            Scanner input = new Scanner(System.in);

            System.out.print("XXXX - номер рейса: ");
            errorMessage = "номер рейса";
            String flightNumber = input.next("[a-zA-Z0-9]{4}").toUpperCase();

            System.out.print("ДД/ММ/ГГГГ - дата рейса: ");
            errorMessage = "дата рейса";
            LocalDate flightDate = LocalDate.parse(input.next("(0?[1-9]|[12]\\d|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)"), dateFormat);

            System.out.print("ЧЧ:ММ - время вылета: ");
            errorMessage = "время вылета";
            String flightTimeString = input.next("(0?\\d|1\\d|2[0-3]):(0?\\d|[1-5]\\d)");
            if (flightTimeString.length() < 5) {
                flightTimeString = "0" + flightTimeString;
            }
            LocalTime flightTime = LocalTime.parse(flightTimeString);

            System.out.print("XX.XX - длительность перелета: ");
            errorMessage = "длительность перелета";
            double flightDuration = Double.parseDouble(input.next("(0?\\d|\\d{2}).([0-5]\\d)"));

            System.out.print("XXX - аэропорт вылета: ");
            errorMessage = "аэропорт вылета";
            String departureAirport = input.next("[a-zA-Z]{3}").toUpperCase();
            for (Airport airport : airportsParser.getAirports()) {
                if (airport.code().equals(departureAirport)) {
                    containStatus = true;
                    break;
                }
            }
            if (!containStatus) {
                throw new InputMismatchException();
            }
            containStatus = false;

            System.out.print("XXX - аэропорт назначения: ");
            errorMessage = "аэропорт назначения";
            String arrivalAirport = input.next("[a-zA-Z]{3}").toUpperCase();
            for (Airport airport : airportsParser.getAirports()) {
                if (airport.code().equals(arrivalAirport)) {
                    containStatus = true;
                    break;
                }
            }
            if (!containStatus) {
                throw new InputMismatchException();
            }

            System.out.print(".XX - стоимость билета (>0): ");
            errorMessage = "стоимость билета";
            double ticketCost = input.nextDouble();
            if (ticketCost < 0) {
                throw new InputMismatchException();
            }

            Flight flight = new Flight(flightNumber, flightDate, flightTime, flightDuration, departureAirport, arrivalAirport, ticketCost);
            flights.add(flight);
            System.out.println("\nИнформация о рейсе " + flight + " добавлена\n");
        } catch (InputMismatchException e) {
            System.out.println("Неверно введён параметр: " + errorMessage);
        }
    }

    public void show() {
        boolean containStatus = false;
        try {
            System.out.print("Введите номер рейса в формате XXXX: ");
            Scanner input = new Scanner(System.in);
            String flightNumber = input.next("[a-zA-Z0-9]{4}").toUpperCase();

            for (Flight flight : flights) {
                if (flight.getFlightNumber().equals(flightNumber)) {
                    System.out.println("Информация о рейсе: " + flight);
                    containStatus = true;
                    break;
                }
            }
            if (!containStatus) {
                System.out.println("Рейс " + flightNumber + " не найден");
            }
        } catch (InputMismatchException e) {
            System.out.println("Неверно введён номер рейса");
        }
    }

    public void showAll() {
        if (flights.isEmpty()) {
            System.out.println("Информация о рейсах отсутствует");
        }

        for (Flight flight : flights) {
            System.out.println("Информация о рейсе: " + flight);
        }
    }
}
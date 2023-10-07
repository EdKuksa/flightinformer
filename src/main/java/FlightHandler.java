import locale.ErrorMessages;
import locale.InputMessages;
import locale.OtherMessages;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

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
            System.out.println(OtherMessages.ADD_INPUT.getMessage());
            Scanner input = new Scanner(System.in);

            errorMessage = ErrorMessages.FLIGHT_NUMBER_ERROR.getMessage();
            String flightNumber = readInput(InputMessages.FLIGHT_NUMBER, input);

            errorMessage = ErrorMessages.FLIGHT_DATE_ERROR.getMessage();
            LocalDate flightDate = LocalDate.parse(readInput(InputMessages.FLIGHT_DATE, input), dateFormat);

            errorMessage = ErrorMessages.FLIGHT_TIME_ERROR.getMessage();
            String flightTimeString = readInput(InputMessages.FLIGHT_TIME, input);
            LocalTime flightTime = flightTimeString.length() < 5 ? LocalTime.parse("0" + flightTimeString) : LocalTime.parse(flightTimeString);

            errorMessage = ErrorMessages.FLIGHT_DURATION_ERROR.getMessage();
            double flightDuration = Double.parseDouble(readInput(InputMessages.FLIGHT_DURATION, input));

            errorMessage = ErrorMessages.DEPARTURE_AIRPORT_ERROR.getMessage();
            String departureAirport = readInput(InputMessages.DEPARTURE_AIRPORT, input);
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

            errorMessage = ErrorMessages.ARRIVAL_AIRPORT_ERROR.getMessage();
            String arrivalAirport = readInput(InputMessages.ARRIVAL_AIRPORT, input);
            for (Airport airport : airportsParser.getAirports()) {
                if (airport.code().equals(arrivalAirport)) {
                    containStatus = true;
                    break;
                }
            }
            if (!containStatus) {
                throw new InputMismatchException();
            }

            errorMessage = ErrorMessages.TICKET_COST_ERROR.getMessage();
            double ticketCost = Double.parseDouble(readInput(InputMessages.TICKET_COST, input));
            if (ticketCost < 0) {
                throw new InputMismatchException();
            }

            Flight flight = new Flight(flightNumber, flightDate, flightTime, flightDuration, departureAirport, arrivalAirport, ticketCost);
            flights.add(flight);
            System.out.println(OtherMessages.ADD_OUTPUT_PREFIX.getMessage() + flight + OtherMessages.ADD_OUTPUT_SUFFIX.getMessage());
        } catch (InputMismatchException e) {
            System.out.println(ErrorMessages.PREFIX_MESSAGE.getMessage() + errorMessage);
        }
    }

    public void show() {
        boolean containStatus = false;
        try {
            System.out.print(OtherMessages.SHOW_INPUT.getMessage());
            Scanner input = new Scanner(System.in);
            String flightNumber = input.next("[a-zA-Z0-9]{4}").toUpperCase();

            for (Flight flight : flights) {
                if (flight.getFlightNumber().equals(flightNumber)) {
                    System.out.println(OtherMessages.SHOW_AND_SHOWALL_OUTPUT.getMessage() + flight);
                    containStatus = true;
                    break;
                }
            }
            if (!containStatus) {
                System.out.println(OtherMessages.SHOW_LOST_PREFIX.getMessage() + flightNumber + OtherMessages.SHOW_LOST_SUFFIX.getMessage());
            }
        } catch (InputMismatchException e) {
            System.out.println(OtherMessages.SHOW_ERROR.getMessage());
        }
    }

    public void showAll() {
        if (flights.isEmpty()) {
            System.out.println(OtherMessages.SHOWALL_EMPTY.getMessage());
        }

        for (Flight flight : flights) {
            System.out.println(OtherMessages.SHOW_AND_SHOWALL_OUTPUT.getMessage() + flight);
        }
    }

    private String readInput(@NotNull InputMessages message, Scanner input) {
        System.out.println(message.getMessage());
        return switch (message) {
            case FLIGHT_NUMBER -> input.next("[a-zA-Z0-9]{4}").toUpperCase();
            case FLIGHT_DATE -> input.next("(0?[1-9]|[12]\\d|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
            case FLIGHT_TIME -> input.next("(0?\\d|1\\d|2[0-3]):(0?\\d|[1-5]\\d)");
            case FLIGHT_DURATION -> input.next("(0?\\d|\\d{2}).([0-5]\\d)");
            case DEPARTURE_AIRPORT, ARRIVAL_AIRPORT -> input.next("[a-zA-Z]{3}").toUpperCase();
            case TICKET_COST -> input.next();
        };
    }
}
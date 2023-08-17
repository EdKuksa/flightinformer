import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class Flight {
    private String flightNumber;
    private LocalDate flightDate;
    private LocalTime flightTime;
    private double flightDuration;
    private String departureAirport;
    private String arrivalAirport;
    private double ticketCost;

    @Override
    public String toString() {
        return flightNumber
                + " " + flightDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + flightTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                + " " + String.format("%05.2f", flightDuration).replace(",", ".")
                + " " + departureAirport
                + " " + arrivalAirport
                + " " + String.format("%.2f", ticketCost);
    }
}
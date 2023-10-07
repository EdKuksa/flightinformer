package locale;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PREFIX_MESSAGE("Неверно введён параметр: "),
    FLIGHT_NUMBER_ERROR("номер рейса"),
    FLIGHT_DATE_ERROR("дата рейса"),
    FLIGHT_TIME_ERROR("время вылета"),
    FLIGHT_DURATION_ERROR("длительность перелета"),
    DEPARTURE_AIRPORT_ERROR("аэропорт вылета"),
    ARRIVAL_AIRPORT_ERROR("аэропорт назначения"),
    TICKET_COST_ERROR("стоимость билета");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
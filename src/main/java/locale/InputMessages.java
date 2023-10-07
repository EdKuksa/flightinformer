package locale;

import lombok.Getter;

@Getter
public enum InputMessages {
    FLIGHT_NUMBER("XXXX - номер рейса: "),
    FLIGHT_DATE("ДД/ММ/ГГГГ - дата рейса: "),
    FLIGHT_TIME("ЧЧ:ММ - время вылета: "),
    FLIGHT_DURATION("XX.XX - длительность перелета: "),
    DEPARTURE_AIRPORT("XXX - аэропорт вылета: "),
    ARRIVAL_AIRPORT("XXX - аэропорт назначения: "),
    TICKET_COST(".XX - стоимость билета (>0): ");

    private final String message;

    InputMessages(String message) {
        this.message = message;
    }
}
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class AirportsParser {
    private static File jsonData = new File("src/main/resources/airports.json");
    private ObjectMapper objectMapper = new ObjectMapper();
    private Airport[] airports;

    {
        try {
            airports = objectMapper.readValue(jsonData, Airport[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

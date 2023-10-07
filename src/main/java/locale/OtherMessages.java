package locale;

import lombok.Getter;

@Getter
public enum OtherMessages {
    // Class FlightInformer
    MAIN_HEADER("Сервис поиска авиабилетов"),
    MAIN_MENU("""
                
            Главное меню:
                
            1 - ввод рейса
            2 - вывод всех рейсов
            3 - поиск рейса по номеру
            0 - завершение работы
            """),
    MAIN_MENU_INPUT("Введите номер пункта меню: "),

    // Class FlightHandler
    ADD_INPUT("\nВведите данные рейса:"),
    ADD_OUTPUT_PREFIX("\nИнформация о рейсе "),
    ADD_OUTPUT_SUFFIX(" добавлена\n"),
    SHOW_INPUT("Введите номер рейса в формате XXXX: "),
    SHOW_AND_SHOWALL_OUTPUT("Информация о рейсе: "),
    SHOW_LOST_PREFIX("Рейс "),
    SHOW_LOST_SUFFIX(" не найден"),
    SHOW_ERROR("Неверно введён номер рейса"),
    SHOWALL_EMPTY("Информация о рейсах отсутствует");

    private final String message;

    OtherMessages(String message) {
        this.message = message;
    }
}
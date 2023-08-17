import java.util.Scanner;

public class FlightInformer {
    static FlightHandler flightHandler = new FlightHandler();
    public static void main(String[] args) {
        System.out.println("Сервис поиска авиабилетов");
        while (true) {
            System.out.println("""
                                                        
                    Главное меню:
                                    
                    1 - ввод рейса
                    2 - вывод всех рейсов
                    3 - поиск рейса по номеру
                    0 - завершение работы
                    """);
            Scanner input = new Scanner(System.in);
            System.out.print("Введите номер пункта меню: ");
            int selectedItem = input.nextInt();
            switch (selectedItem) {
                case 1 -> flightHandler.add();
                case 2 -> flightHandler.showAll();
                case 3 -> flightHandler.show();
                case 0 -> {
                    return;
                }
            }
        }
    }
}

import locale.OtherMessages;

import java.util.Scanner;

public class FlightInformer {
    static FlightHandler flightHandler = new FlightHandler();
    public static void main(String[] args) {
        System.out.println(OtherMessages.MAIN_HEADER.getMessage());
        while (true) {
            System.out.println(OtherMessages.MAIN_MENU.getMessage());
            Scanner input = new Scanner(System.in);
            System.out.print(OtherMessages.MAIN_MENU_INPUT.getMessage());
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
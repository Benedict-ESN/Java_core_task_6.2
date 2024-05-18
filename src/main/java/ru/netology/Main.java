package ru.netology;

import java.util.*;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Address, Double> costPerAddress = new HashMap<>();
    private static Set<String> countries = new HashSet<>();
    private static double totalCost = 0.0;

    static {
        costPerAddress.put(new Address("Russia", "Moscow"), 1.2);
        costPerAddress.put(new Address("Russia", "Novgorod"), 7.0);
        costPerAddress.put(new Address("Russia", "Voronez"), 5.0);
        costPerAddress.put(new Address("USA", "New-York"), 15.0);
        costPerAddress.put(new Address("USA", "Chicago"), 14.0);
        costPerAddress.put(new Address("UK", "London"), 11.0);
        costPerAddress.put(new Address("UK", "Dublin"), 18.0);
    }
    public static void main(String[] args) {
        run();
    }

    public static void setScanner(Scanner scanner) {
        Main.scanner = scanner;
    }

  /*  public static String askUser() {
        String text = ""; // однозначно обнуляем переменную
        Scanner scanner = new Scanner(System.in); // Создание сканера
        text = scanner.nextLine(); // Считывание текста
        return text; // Возврат строки
    }*/

    public static void run() {
        while (true) {
            System.out.println("Введите заказ (или 'end' для завершения) - страну, город, вес (кг) посылки через пробел: ");
            String input = scanner.nextLine();
            if (input == null || input.isEmpty() || input.equals(" ")) {
                System.out.println("Значение не может быть пустым.\n \n");
                continue;
            } else if ("end".equalsIgnoreCase(input) || "exit".equalsIgnoreCase(input) || "0".equalsIgnoreCase(input)) {
                break;
            } else {
                String[] order = input.split(" ");
                if (order.length != 3) {
                    System.out.println("Недостаточно данных. Попробуйте снова.");
                    continue;
                }
                String country = order[0];
                String city = order[1];
                double weight;
                try {
                    weight = Double.parseDouble(order[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный вес. Попробуйте снова.");
                    continue;
                }

                Address address = new Address(country, city);
                Double cost = costPerAddress.get(address);
                if (cost == null) {
                    System.out.println("Доставка в указанный адрес не предоставляется.");
                } else {
                    double deliveryCost = cost * weight;
                    countries.add(country);
                    totalCost += deliveryCost;
                    System.out.printf("Заказ доставки по адресу:\nСтрана: %s\nГород: %s\nВес посылки: %.2f кг\n", country, city, weight);
                    System.out.printf("Стоимость доставки: %.2f\n", deliveryCost);
                    System.out.println("Всего уникальных стран: " + countries.size() + " - " + countries);
                }
            }
        }
    }
}
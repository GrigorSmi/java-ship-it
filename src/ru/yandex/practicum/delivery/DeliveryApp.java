package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже


    private static void addParcel() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите тип посылки:\n1 — стандартная\n2 — хрупкая\n3 — скоропортящаяся");
        int type = scanner.nextInt();

        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки (кг):");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки:");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        switch (type) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standardParcel.packageItem();
                break;

            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                fragileParcel.packageItem();
                break;

            case 3:
                System.out.println("Введите срок годности (в днях):");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcel.packageItem();
                break;

            default:
                System.out.println("Неизвестный тип посылки");
        }
    }


    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
    }

}


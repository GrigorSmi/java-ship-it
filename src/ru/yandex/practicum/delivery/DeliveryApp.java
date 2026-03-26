package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Parcel> allParcels = new ArrayList<>();
    private static ArrayList<Trackable> trackableParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standartParcels = new ParcelBox<>(20);
    private static ParcelBox<FragileParcel> fragileParcels = new ParcelBox<>(8);
    private static ParcelBox<PerishableParcel> perishableParcels = new ParcelBox<>(12);

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
                case 4:
                    trackingStatus();
                    break;
                case 5:
                    boxContents();
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
        System.out.println("4 — Обновить статус для всех посылок c трекингом");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже


    private static void addParcel() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите тип посылки:\n1 — стандартная\n2 — хрупкая\n3 — скоропортящаяся");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки:");
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
                if (standartParcels.addParcel(standardParcel) == true) {
                    allParcels.add(standardParcel);
                }
                break;

            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                if (fragileParcels.addParcel(fragileParcel) == true) {
                    allParcels.add(fragileParcel);
                    trackableParcels.add(fragileParcel);
                }
                break;

            case 3:
                System.out.println("Введите срок годности в днях:");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                if (perishableParcels.addParcel(perishableParcel)) {
                    allParcels.add(perishableParcel);
                }
                break;

            default:
                System.out.println("Неизвестный тип посылки");
        }
    }


    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }


    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost);
    }

    private static void trackingStatus() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок с поддержкой трекинга");
            return;
        }

        for (Trackable parcel : trackableParcels) {
            System.out.println("Введите новое местоположение для " + parcel.getDescription());
            String newLocation = scanner.nextLine();
            parcel.reportStatus(newLocation);
        }
    }

    private static void boxContents() {

        System.out.println("Выберите тип посылки содержание которой посмотреть: \n1 — стандартная\n2 — хрупкая\n3 — скоропортящаяся\n4 — все");
        int typeOfBox = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Содержимое коробки:");
        switch (typeOfBox) {
            case 1:
                if (standartParcels.getAllParcels().isEmpty()) {
                    System.out.println("Ничего нет");
                } else {
                    for (Parcel parcel : standartParcels.getAllParcels()) {
                        System.out.println(parcel.getDescription());
                    }
                }
                break;

            case 2:
                if (fragileParcels.getAllParcels().isEmpty()) {
                    System.out.println("Ничего нет");
                } else {
                    for (Parcel parcel : fragileParcels.getAllParcels()) {
                        System.out.println(parcel.getDescription());
                    }
                }
                break;

            case 3:
                if (perishableParcels.getAllParcels().isEmpty()) {
                    System.out.println("Ничего нет");
                } else {
                    for (Parcel parcel : perishableParcels.getAllParcels()) {
                        System.out.println(parcel.getDescription());
                    }
                }
                break;
            case 4:
                if (allParcels.isEmpty()) {
                    System.out.println("Ничего нет");
                } else {
                    for (Parcel parcel : allParcels) {
                        System.out.println(parcel.getDescription());
                    }
                }
                break;

            default:
                System.out.println("Неизвестный тип посылки");
        }
    }

}


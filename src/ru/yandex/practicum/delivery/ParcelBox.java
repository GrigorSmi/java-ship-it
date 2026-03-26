package ru.yandex.practicum.delivery;

import java.util.ArrayList;


public class ParcelBox<T extends Parcel> {

    private ArrayList<T> parcels = new ArrayList<>();
    ;
    private int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        double parcelWeight = parcel.getWeight();

        if (currentWeight + parcelWeight > maxWeight) {
            System.out.println("Не удалось добавить посылку — превышен максимальный вес коробки");
            System.out.println("Текущий вес: " + currentWeight + " вес посылки: " + parcelWeight +
                    " максимальный вес: " + maxWeight);
            return false;
        }

        parcels.add(parcel);
        currentWeight += parcelWeight;
        System.out.println("Посылка успешно добавлена в коробку. Текущий вес: " + currentWeight + " кг.");
        return true;
    }

    public ArrayList<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }
}
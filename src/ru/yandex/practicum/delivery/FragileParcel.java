package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel {
    private static final int BASE_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public void deliver() {
        super.deliver();
    }

    @Override
    protected int getBaseCost() {
        return BASE_COST;
    }
}

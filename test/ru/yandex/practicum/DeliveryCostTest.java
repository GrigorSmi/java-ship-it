package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    //проверка цены
    @Test
    void testStandardParcelWeight10CostMustBe20() {
        StandardParcel parcel = new StandardParcel("Чемодан", 10, "Москва", 1);
        assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    void testStandardParcelWeight20CostMustBe40() {
        StandardParcel parcel = new StandardParcel("Большой чемодан", 20, "Москва", 1);
        assertEquals(40, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelWeight4CostMustBe16() {
        FragileParcel parcel = new FragileParcel("Планшет", 4, "СПб", 2);
        assertEquals(16, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelWeight8CostMustBe32() {
        FragileParcel parcel = new FragileParcel("Зеркало", 8, "СПб", 2);
        assertEquals(32, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelWeight3CostMustBe9() {
        PerishableParcel parcel = new PerishableParcel("Торт", 3, "Казань", 3, 7);
        assertEquals(9, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelWeight12CostMustBe36() {
        PerishableParcel parcel = new PerishableParcel("Фрукты", 12, "Казань", 3, 7);
        assertEquals(36, parcel.calculateDeliveryCost());
    }


    //проверка просрочки
    @Test
    void testNotExpiredParcel() {
        PerishableParcel parcel = new PerishableParcel("Колбаса", 3, "Новгород", 1, 5);
        assertFalse(parcel.isExpired(4));
    }

    @Test
    void testBoundaryExpiration() {
        PerishableParcel parcel = new PerishableParcel("Сыр", 1, "Новгород", 1, 5);
        assertTrue(parcel.isExpired(7));
    }

    @Test
    void testExpiredParcel() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 2, "Новгород", 1, 5);
        assertFalse(parcel.isExpired(6));
    }


    //проверка добавления новой посылки в коробку
    private ParcelBox<Parcel> box;

    @BeforeEach
    void newBox() {
        box = new ParcelBox<>(20); // максимальный вес 20 кг
    }

    @Test
    void testAddParcelWithinWeightLimit() {
        Parcel parcel = new StandardParcel("Чемодан", 10, "Москва", 1);
        boolean result = box.addParcel(parcel);
        assertTrue(result);
    }

    @Test
    void testAddParcelExceedingWeightLimit() {
        box.addParcel(new StandardParcel("Большой чемодан", 15, "Москва", 3));
        Parcel heavyParcel = new StandardParcel("Чемодан", 10, "Москва", 4);
        boolean result = box.addParcel(heavyParcel);
        assertFalse(result); // не должно добавиться

    }

    @Test
    void testBoundaryWeightAddition() {
        box.addParcel(new StandardParcel("Большой чемодан", 18, "Москва", 2));
        Parcel smallParcel = new StandardParcel("Торт", 2, "Москва", 3);
        boolean result = box.addParcel(smallParcel);
        assertTrue(result);
    }

}

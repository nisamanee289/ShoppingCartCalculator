package Lib;

import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) {
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2)); // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) {
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }
         // Test 4:  โปรโมชั่น BOGO (ซื้อ 1 แถม 1)
        ArrayList<CartItem> bogoCart = new ArrayList<>();
        bogoCart.add(new CartItem("BOGO", "Soda", 10.0, 3)); // จ่าย 2 = 20
        double total4 = ShoppingCartCalculator.calculateTotalPrice(bogoCart);
        if (total4 == 20.0) {
            System.out.println("PASSED: BOGO promotion is correct (20.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BOGO expected 20.0 but got " + total4);
            failedCount++;
        }

        // Test 5: โปรโมชั่น BULK (ซื้อเยอะลดราคา)
        ArrayList<CartItem> bulkCart = new ArrayList<>();
        bulkCart.add(new CartItem("BULK", "Rice", 50.0, 6)); // 300 - 10% = 270
        double total5 = ShoppingCartCalculator.calculateTotalPrice(bulkCart);
        if (total5 == 270.0) {
            System.out.println("PASSED: BULK discount is correct (270.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK expected 270.0 but got " + total5);
            failedCount++;
        }

        // Test 6: BULK แต่ซื้อไม่ถึง 6 ชิ้น (ไม่ลดราคา)
        ArrayList<CartItem> bulkCartSmall = new ArrayList<>();
        bulkCartSmall.add(new CartItem("BULK", "Rice", 50.0, 5)); // 250 no discount
        double total6 = ShoppingCartCalculator.calculateTotalPrice(bulkCartSmall);
        if (total6 == 250.0) {
            System.out.println("PASSED: BULK without discount is correct (250.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: BULK expected 250.0 but got " + total6);
            failedCount++;
        }

        // Test 7:สินค้ามีราคาหรือจำนวนติดลบ (ต้องละเว้น)
        ArrayList<CartItem> invalidCart = new ArrayList<>();
        invalidCart.add(new CartItem("NORMAL", "InvalidItem", -10.0, 5)); // skip
        invalidCart.add(new CartItem("NORMAL", "InvalidItem2", 10.0, -5)); // skip
        double total7 = ShoppingCartCalculator.calculateTotalPrice(invalidCart);
        if (total7 == 0.0) {
            System.out.println("PASSED: Invalid items are ignored");
            passedCount++;
        } else {
            System.out.println("FAILED: Expected 0.0 for invalid items but got " + total7);
            failedCount++;
        }

        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}
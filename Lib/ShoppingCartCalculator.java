package Lib;

import java.util.ArrayList;

public class ShoppingCartCalculator {

    /**
     *เมธอดนี้ใช้คำนวณราคารวมสุทธิของสินค้าทั้งหมดในตะกร้าช้อปปิ้ง
     * โดยพิจารณาจากราคาสินค้า จำนวน และเงื่อนไขส่วนลดพิเศษ
     *
     * - จะทำอย่างไรถ้า items เป็น null หรือ empty?
     *   - จะคืนค่า 0.0 ทันที เนื่องจากไม่มีสินค้าให้คำนวณ
     *
     * - จะทำอย่างไรถ้า CartItem มี price หรือ quantity ติดลบ?
     *   - จะไม่รวมสินค้านั้นในการคำนวณ เพราะถือว่าข้อมูลไม่ถูกต้อง
     *
     * - กฎส่วนลด BOGO (ซื้อ 1 แถม 1):
     *   - ใช้กับสินค้าที่มีรหัส SKU เป็น "BOGO"
     *   - ระบบจะคิดเฉพาะจำนวนที่ต้องจ่าย เช่น ซื้อ 3 จ่าย 2, ซื้อ 4 จ่าย 2
     *
     * - กฎส่วนลด BULK (ซื้อ >= 6 ชิ้น ลด 10%):
     *   - ใช้กับสินค้าที่มีรหัส SKU เป็น "BULK"
     *   - หากซื้อจำนวนตั้งแต่ 6 ชิ้นขึ้นไป จะลดราคาสินค้านั้นลง 10%
     * @param items รายการสินค้าในตะกร้า (ArrayList ของ CartItem)
     * @return ยอดรวมราคาสุทธิของตะกร้าสินค้า (เป็น double)
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if (items == null || items.isEmpty()) {
        return 0.0;
    }
    double total = 0.0;

    for (CartItem item : items) {
        if (item.price() < 0 || item.quantity() < 0) {
            continue; // ข้าม item ที่มีราคาหรือจำนวนติดลบ
        }

        double itemTotal;

        switch (item.sku()) {
            case "BOGO":
                int payQty = item.quantity() / 2 + item.quantity() % 2;
                itemTotal = payQty * item.price();
                break;
            case "BULK":
                itemTotal = item.price() * item.quantity();
                if (item.quantity() >= 6) {
                    itemTotal *= 0.9; // ลด 10%
                }
                break;
            default:
                itemTotal = item.price() * item.quantity();
        }

        total += itemTotal;
     }

        return total;
    }
}
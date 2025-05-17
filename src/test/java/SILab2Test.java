import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SILab2Test {

    @Test
    void checkCart_EveryStatement() {
        // Test case 1: allItems = null, cardNumber = any
        // Овој case е кога листата allItems е празна => без разлика што има стрингот cardNumber, ќе врати RuntimeException("allItems list can't be null!")
        RuntimeException ex1 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, "any")
        );
        assertEquals("allItems list can't be null!", ex1.getMessage());

        // Test case 2: allItems = [Item(null, 2, 400, 0.1)], cardNumber = "1234567890123456"
        // има елемент во allItems, кој нема име (null) - ќе врати RuntimeException("Invalid item!")
        List<Item> items2 = List.of(new Item(null, 2, 400, 0.1));
        RuntimeException ex2 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items2, "1234567890123456")
        );
        assertEquals("Invalid item!", ex2.getMessage());

        // Test case 3: allItems = [Item("", 2, 400, 0.1)], cardNumber = "1234567890123456"
        // има елемент во allItems, кој има име празен string (length=0), ќе врати RuntimeException("Invalid item!")
        List<Item> items3 = List.of(new Item("", 2, 400, 0.1));
        RuntimeException ex3 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items3, "1234567890123456")
        );
        assertEquals("Invalid item!", ex3.getMessage());

        // Test case 4: allItems = [Item("banana", 2, 400, 0.1)], cardNumber = null
        // валиден елемент во allItems, cardNum e null, ќе врати RuntimeException("Invalid card number!")
        List<Item> items4 = List.of(new Item("banana", 2, 400, 0.1));
        RuntimeException ex4 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items4, null)
        );
        assertEquals("Invalid card number!", ex4.getMessage());

        // Test case 5: allItems = [Item("banana", 2, 400, 0.1)], cardNumber = "12345"
        // валиден елемент во allItems, cardNum e string, што не е 16 карактери, ќе врати RuntimeException("Invalid card number!")
        RuntimeException ex5 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items4, "12345")
        );
        assertEquals("Invalid card number!", ex5.getMessage());

        // Test case 6: allItems = [Item("banana", 2, 400, 0.1)], cardNumber = "A234567890123456"
        // валиден елемент во allItems, невалиден карактер во cardNum, ќе врати RuntimeException("Invalid character in card number!")
        RuntimeException ex6 = assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(items4, "A234567890123456")
        );
        assertEquals("Invalid character in card number!", ex6.getMessage());

        // Test case 7: allItems = [Item("banana", 2, 400, 0.0), Item("apple", 3, 300, 0.1)], cardNumber = "1234567890123456"
        // валидни елементи во allItems, едниот без discount, другиот со discount, и валиден cardNumber. Ќе помине точно, со тоа што, за секој елемент во allItems,
        // sum += item.getPrice() * item.getQuantity() за првиот елемент, и sum += item.getPrice() * (1 - item.getDiscount()) * item.getQuantity() за вториот елемент.
        List<Item> items7 = List.of(
                new Item("banana", 2, 400, 0.0),
                new Item("apple", 3, 300, 0.1)
        );
        double result7 = SILab2.checkCart(items7, "1234567890123456");
        assertEquals(1550.0, result7);
    }

    @Test
    void checkCart_MultipleCondition() {
        // Multiple Condition критериумот за условот
        // if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
        String validCard = "1234567890123456";

        // Test 1: false || false || false
        List<Item> items1 = List.of(new Item("Item1", 10, 300, 0));
        double result1 = SILab2.checkCart(items1, validCard);
        assertEquals(3000, result1);

        // Test 2: true || false || false
        List<Item> items2 = List.of(new Item("Item2", 10, 301, 0));
        double result2 = SILab2.checkCart(items2, validCard);
        assertEquals(2980, result2);

        // Test 3: false || true || false
        List<Item> items3 = List.of(new Item("Item3", 10, 300, 0.1));
        double result3 = SILab2.checkCart(items3, validCard);
        assertEquals(2670, result3);

        // Test 4: false || false || true
        List<Item> items4 = List.of(new Item("Item4", 11, 300, 0));
        double result4 = SILab2.checkCart(items4, validCard);
        assertEquals(3270, result4);

        // Test 5: true || true || false
        List<Item> items5 = List.of(new Item("Item5", 10, 301, 0.1));
        double result5 = SILab2.checkCart(items5, validCard);
        assertEquals(2679, result5);

        // Test 6: true || false || true
        List<Item> items6 = List.of(new Item("Item6", 11, 301, 0));
        double result6 = SILab2.checkCart(items6, validCard);
        assertEquals(3281, result6);

        // Test 7: false || true || true
        List<Item> items7 = List.of(new Item("Item7", 11, 300, 0.1));
        double result7 = SILab2.checkCart(items7, validCard);
        assertEquals(2940, result7);

        // Test 8: true || true || true
        List<Item> items8 = List.of(new Item("Item8", 11, 301, 0.1));
        double result8 = SILab2.checkCart(items8, validCard);
        assertEquals(2949.9, result8);
    }

}
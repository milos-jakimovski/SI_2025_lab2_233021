import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {
        // Test 1: allItems == null
        RuntimeException ex1 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(null, "1234567812345678"));
        assertEquals("allItems list can't be null!", ex1.getMessage());

        // Test 2: item with null name
        List<Item> items2 = List.of(new Item(null, 1, 100, 0));
        RuntimeException ex2 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items2, "1234567812345678"));
        assertEquals("Invalid item!", ex2.getMessage());

        // Test 3: item with price > 300 and discount > 0 and quantity > 10 (activates sum -= 30 and uses discount)
        List<Item> items3 = List.of(new Item("item1", 15, 400, 0.1));
        double result3 = SILab2.checkCart(items3, "1234567812345678");
        // (400 * 0.9) * 15 = 5400.0, then subtract 30 => 5370.0
        assertEquals(5370.0, result3);

        // Test 4: item without discount, no sum -= 30
        List<Item> items4 = List.of(new Item("item2", 1, 100, 0));
        double result4 = SILab2.checkCart(items4, "1234567812345678");
        assertEquals(100.0, result4);

        // Test 5: invalid card number (not 16 characters)
        List<Item> items5 = List.of(new Item("item", 1, 100, 0));
        RuntimeException ex5 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items5, "12345678"));
        assertEquals("Invalid card number!", ex5.getMessage());

        // Test 6: card number with non-digit character
        List<Item> items6 = List.of(new Item("item", 1, 100, 0));
        RuntimeException ex6 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items6, "12345678abc45678"));
        assertEquals("Invalid character in card number!", ex6.getMessage());
    }

    @Test
    public void testMultipleCondition() {
        // T1: false, false, false
        List<Item> t1 = List.of(new Item("T1", 1, 100, 0.0));
        double r1 = SILab2.checkCart(t1, "1234567812345678");
        assertEquals(100.0, r1); // no deduction

        // T2: false, false, true
        List<Item> t2 = List.of(new Item("T2", 11, 100, 0.0));
        double r2 = SILab2.checkCart(t2, "1234567812345678");
        assertEquals(1100.0 - 30.0, r2);

        // T3: false, true, false
        List<Item> t3 = List.of(new Item("T3", 1, 100, 0.1));
        double r3 = SILab2.checkCart(t3, "1234567812345678");
        assertEquals(90.0 - 30.0, r3); // 100*0.9 = 90

        // T4: false, true, true
        List<Item> t4 = List.of(new Item("T4", 11, 100, 0.1));
        double r4 = SILab2.checkCart(t4, "1234567812345678");
        assertEquals((100 * 0.9 * 11) - 30.0, r4);

        // T5: true, false, false
        List<Item> t5 = List.of(new Item("T5", 1, 350, 0.0));
        double r5 = SILab2.checkCart(t5, "1234567812345678");
        assertEquals(350.0 - 30.0, r5);

        // T6: true, false, true
        List<Item> t6 = List.of(new Item("T6", 11, 350, 0.0));
        double r6 = SILab2.checkCart(t6, "1234567812345678");
        assertEquals((350 * 11) - 30.0, r6);

        // T7: true, true, false
        List<Item> t7 = List.of(new Item("T7", 1, 350, 0.1));
        double r7 = SILab2.checkCart(t7, "1234567812345678");
        assertEquals((350 * 0.9) - 30.0, r7);

        // T8: true, true, true
        List<Item> t8 = List.of(new Item("T8", 20, 500, 0.2));
        double r8 = SILab2.checkCart(t8, "1234567812345678");
        assertEquals((500 * 0.8 * 20) - 30.0, r8);
    }
}

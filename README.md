# Втора лабораториска вежба по Софтверско Инженерство
## Милош Јакимовски, бр. на индекс 233021
### Control Flow Graph
# ![Control Flow Graph](https://github.com/user-attachments/assets/e971f618-656c-419e-a433-1e95f1505e31)
### Цикломатска комплексност
Цикломатската комплексност на овој код е 13, истата ја добив преку формулата P+1, каде што P е бројот на предикатни јазли. Во случајoв P=12, па цикломатската комплексност изнесува 13.
### Тест случаи според критериумот Every statement
#### Test 1: allItems == null
Цел: Да се покрие RuntimeException за null листа.
checkCart(null, "1234567812345678");
Очекуван резултат: RuntimeException("allItems list can't be null!")

#### Test 2: Еден предмет со null име
List<Item> items = List.of(new Item(null, 1, 100, 0));
checkCart(items, "1234567812345678");
Очекуван резултат: RuntimeException("Invalid item!")

#### Test 3: Предмет кој активира sum -= 30 и има попуст
List<Item> items = List.of(new Item("item1", 15, 400, 0.1));
checkCart(items, "1234567812345678");
Се активира:
sum -= 30 (заради количина > 10 и цена > 300)
discount > 0 → се пресметува цена со попуст

#### Test 4: Предмет без попуст, неактивен sum -= 30
List<Item> items = List.of(new Item("item2", 1, 100, 0));
checkCart(items, "1234567812345678");
Се активира пресметка без попуст
sum -= 30 не се извршува

#### Test 5: cardNumber.length() != 16
List<Item> items = List.of(new Item("item", 1, 100, 0));
checkCart(items, "12345678");
Очекуван резултат: RuntimeException("Invalid card number!")

#### Test 6: cardNumber со нецифрен карактер
List<Item> items = List.of(new Item("item", 1, 100, 0));
checkCart(items, "12345678abc45678");
Очекуван резултат: RuntimeException("Invalid character in card number!")

### Тест случаи според Multiple Condition критериумот за условот: if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

| Тест | A (price > 300) | B (discount > 0) | C (quantity > 10) | Услов активиран? | Објаснување                                                |
| ---- | --------------- | ---------------- | ----------------- | ---------------- | ---------------------------------------------------------- |
| T1   | false           | false            | false             |  Не             | Ниеден услов не е исполнет, `sum -= 30` **не** се извршува |
| T2   | false           | false            | true              |  Да             | Само количината е > 10                                     |
| T3   | false           | true             | false             |  Да             | Само има попуст                                            |
| T4   | false           | true             | true              |  Да             | Попуст и количина се исполнети                             |
| T5   | true            | false            | false             |  Да             | Само цената е > 300                                        |
| T6   | true            | false            | true              |  Да             | Цена и количина се исполнети                               |
| T7   | true            | true             | false             |  Да             | Цена и попуст се исполнети                                 |
| T8   | true            | true             | true              |  Да             | Сите три услови се исполнети                               |

За да се постигне целосна покриеност според **Multiple Condition критериумот**, потребни се **минимум 8 тест случаи** по еден за секоја од 2³ = 8 можни комбинации на условите.


# Втора лабораториска вежба по Софтверско инженерство 
## Мартин Јанев , бр. на индекс 236040

Control Flow Graph

![image](https://github.com/user-attachments/assets/e0082732-6d48-4b25-a478-97b9d5443135)

# Цикломатска комплексност
Цикломатската комплексност на овој код е 7, истата ја добив преку формулата P+1, каде што P е бројот на предикатни јазли. Во случајoв P=6, па цикломатската комплексност изнесува 7.

# Тест случаи според критериумот Every statement
`allItems = null, cardNumber = any `- Овој case е кога листата allItems е празна => без разлика што има стрингот cardNumber, ќе врати RuntimeException("allItems list can't be null!")

`allItems = [Item(null, 2, 400, 0.1)] cardNumber = "1234567890123456"` - има елемент во allItems, кој нема име (null) - ќе врати RuntimeException("Invalid item!")

`allItems = [Item("", 2, 400, 0.1)] cardNumber = "1234567890123456"` - има елемент во allItems, кој има име празен string (length=0), ќе врати RuntimeException("Invalid item!")

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = null` - валиден елемент во allItems, cardNum e null, ќе врати RuntimeException("Invalid card number!")

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "12345"` - валиден елемент во allItems, cardNum e string, што не е 16 карактери, ќе врати RuntimeException("Invalid card number!")

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "A234567890123456"` - валиден елемент во allItems, невалиден карактер во cardNum, ќе врати RuntimeException("Invalid character in card number!")

`allItems = [Item("banana", 2, 400, 0.0)] cardNumber = "1234567890123456"` - валиден елемент во allItems без discount и валиден cardNumber, ќе помине точно, со тоа што, за секој елемент во allItems,sum += item.getPrice()*item.getQuantity();

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "1234567890123456"` - валиден елемент во allItems со discount и валиден cardNumber, ќе помине точно, со тоа што, за секој елемент во allItems, sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity();

Минимален број тест случаи, за да се постигне овој критериум е 5, и тоа 4 за секој RuntimeException (еден од нив ќе има случај со невалиден cardNumber и ќе има/нема discount на Item), што се јавува во кодот и уште еден, со кој се изминува задачата без појава на Exception, а ќе биде обратен случај, од оној што е погоре опишан, каде ќе има Exception.


# Тест случаи според критериумот Multiple Condition 
## if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
За условот: if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

За да се задоволи Multiple Condition критериумот, може да се покријат сите комбинации од логичките вредности на трите подуслови. Но доволни се точно 4 тест случаи, затоа што имаме ИЛИ оператор (||):

![image](https://github.com/user-attachments/assets/37fb0517-5bf5-4dab-89f7-ed5931fb3b61)


# Објаснување на напишаните unit tests

Секој тест случај покрива една или повеќе гранки/услови од функцијата. 

Функцијата `checkCart_EveryStatement` ги тестира сите гранки од CFG. Се користат истите test cases, со кои се покриваат сите exceptions, кои може да се стигнат, како и 2 test - cases за discount > 0 (`allItems = [Item("banana", 2, 400, 0.0)] cardNumber = "1234567890123456"` и `allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "1234567890123456"`).

Функцијата `checkCart_MultipleCondition` ги покрива 8те комбинации за условот: `item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10`. За сите случаи, cardNumber не се испитува, па затоа е нерелевантен.


### Test 1: `false || false || false`

```java
// Test 1: false || false || false
List<`item> `items1 = List.of(new `item("`item1", 10, 300, 0));
double result1 = SILab2.checkCart(`items1, validCard);
assertEquals(3000, result1);
```

Цената не надминува 300, нема попуст, и нема над 10 парчиња од item-от. Условот не се исполнува, па не се намалува сумата за 30. Бидејќи нема попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 3000.

---

### Test 2: `true || false || false`

```java
// Test 2: true || false || false
List<`item> `items2 = List.of(new `item("`item2", 10, 301, 0));
double result2 = SILab2.checkCart(`items2, validCard);
assertEquals(2980, result2);
```

Цената надминува 300, нема попуст, и нема над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Поради тоа што нема попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 2970.

---

### Test 3: `false || true || false`

```java
// Test 3: false || true || false
List<`item> `items3 = List.of(new `item("`item3", 10, 300, 0.1));
double result3 = SILab2.checkCart(`items3, validCard);
assertEquals(2670, result3);
```

Цената не надминува 300, има попуст, и нема над 10 парчиња од item-от. Условот се исполнува, па се намалува сумата за 30. Поради попустот, се додава намалената цена: `item.getPrice() * ((1 -/ tem.getDiscount()) * item.getQuantity())`. <br><br> Финална сума: 2670.

---

### Test 4: `false || false || true`

```java
// Test 4: false || false || true
List<`item> `items4 = List.of(new `item("`item4", 11, 300, 0));
double result4 = SILab2.checkCart(`items4, validCard);
assertEquals(3270, result4);
```

Цената не надминува 300, нема попуст, но има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Бидејќи нема попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 3270.

---

### Test 5: `true || true || false`

```java
// Test 5: true || true || false
List<`item> `items5 = List.of(new `item("`item5", 10, 301, 0.1));
double result5 = SILab2.checkCart(`items5, validCard);
assertEquals(2679, result5);
```

Цената надминува 300, има попуст, и нема над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Се додава намалената цена поради попустот: `item.getPrice() * ((1 - item.getDiscount()) * item.getQuantity())`. <br><br> Финална сума: 2679.

---

### Test 6: `true || false || true`

```java
// Test 6: true || false || true
List<`item> `items6 = List.of(new `item("`item6", 11, 301, 0));
double result6 = SILab2.checkCart(`items6, validCard);
assertEquals(3281, result6);
```

Цената надминува 300, нема попуст, и има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Поради отсуство на попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 3281.

---

### Test 7: `false || true || true`

```java
// Test 7: false || true || true
List<`item> `items7 = List.of(new `item("`item7", 11, 300, 0.1));
double result7 = SILab2.checkCart(`items7, validCard);
assertEquals(2940, result7);
```

Цената не надминува 300, има попуст, и има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Се додава намалената цена поради попустот: `item.getPrice() * ((1 - item.getDiscount()) * item.getQuantity())`. <br><br> Финална сума: 2940.

---

### Test 8: `true || true || true`

```java
// Test 8: true || true || true
List<`item> `items8 = List.of(new `item("`item8", 11, 301, 0.1));
double result8 = SILab2.checkCart(`items8, validCard);
assertEquals(2949.9, result8);
```

Цената надминува 300, има попуст, и има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Се додава намалената цена поради попустот: `item.getPrice() * ((1 - item.getDiscount()) * item.getQuantity())`. <br><br> Финална сума: 2949.9.

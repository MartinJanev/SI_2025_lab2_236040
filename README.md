# Втора лабораториска вежба по Софтверско инженерство

## Мартин Јанев , бр. на индекс 236040

Control Flow Graph

![Control Flow Graph](cfg.png)

# Цикломатска комплексност

Цикломатската комплексност на овој код е 9, истата ја добив преку формулата P+1, каде што P е бројот на предикатни
јазли. Во случајoв P=8, па цикломатската комплексност изнесува 9.

# Тест случаи според критериумот Every statement

`allItems = null, cardNumber = any `- Овој case е кога листата allItems е празна => без разлика што има стрингот
cardNumber, ќе врати RuntimeException("allItems list can't be null!")

`allItems = [Item(null, 2, 400, 0.1)] cardNumber = "1234567890123456"` - има елемент во allItems, кој нема име (null) -
ќе врати RuntimeException("Invalid item!")

`allItems = [Item("", 2, 400, 0.1)] cardNumber = "1234567890123456"` - има елемент во allItems, кој има име празен
string (length=0), ќе врати RuntimeException("Invalid item!")

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = null` - валиден елемент во allItems, cardNum e null, ќе врати
RuntimeException("Invalid card number!")

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "12345"` - валиден елемент во allItems, cardNum e string, што не
е 16 карактери, ќе врати RuntimeException("Invalid card number!")

`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "A234567890123456"` - валиден елемент во allItems, невалиден
карактер во cardNum, ќе врати RuntimeException("Invalid character in card number!")

`allItems = [Item("banana", 2, 400, 0.0), Item("apple", 3, 300, 0.1)] cardNumber = "1234567890123456"` - валидни
елементи во allItems, едниот без discount, другиот со discount, и валиден cardNumber. Ќе помине точно, со тоа што, за
секој елемент во allItems, sum += item.getPrice() * item.getQuantity() за првиот елемент, и sum += item.getPrice() * (
1 - item.getDiscount()) * item.getQuantity() за вториот елемент.

## Минималниот број на тест случаи потребни за да се покријат сите линии од кодот е 5:

### 🔴 Четири тест случаи за исклучоци:

Овие тестови се наменети да ги активираат различните RuntimeException случаи во кодот. Секој тест случај одговара на
специфична невалидна ситуација:

* allItems е null
    * Треба да фрли исклучок.
* Некој елемент (Item) во allItems има null име
    * Фрла исклучок.
* Некој елемент во allItems има празно име ("")
    * Исто така фрла исклучок.
* cardNumber е null или невалиден (на пр. не е 16 карактери или содржи невалидни знаци)
    * Фрла исклучок.

### ✅ Еден тест случај за валидни влезни податоци:

Овој тест случај треба да осигура дека кодот се извршува без исклучоци и дека логиката за пресметување на сумата
функционира правилно.

Треба да вклучи: валидна allItems листа со најмалку два елементи:

* Една без попуст
* Една со попуст,
* Валиден cardNumber (точно 16 цифри, сите бројки).

# Тест случаи според критериумот Multiple Condition

## if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

За условот: if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

За да се задоволи Multiple Condition критериумот, може да се покријат сите комбинации од логичките вредности на трите
подуслови. Но доволни се точно 8 тест случаи, затоа што имаме ИЛИ оператор (||):

| Тест | Услови (A \|\| B \|\| C) | Влез (Цена, Попуст, Количина)     | Опис                             | Очекуван резултат         |
|------|--------------------------|-----------------------------------|----------------------------------|---------------------------|
| 1    | F \|\| F \|\| F          | Цена=300, Попуст=0, Количина=10   | Ниту еден подуслов не е исполнет | Не се одзема 30 од сумата |
| 2    | T \|\| F \|\| F          | Цена=301, Попуст=0, Количина=10   | Само цената е висока             | Се одзема 30 од сумата    |
| 3    | F \|\| T \|\| F          | Цена=300, Попуст=0.1, Количина=10 | Само има попуст                  | Се одзема 30 од сумата    |
| 4    | F \|\| F \|\| T          | Цена=300, Попуст=0, Количина=11   | Само количината >10              | Се одзема 30 од сумата    |
| 5    | T \|\| T \|\| F          | Цена=301, Попуст=0.1, Количина=10 | Висока цена и попуст             | Се одзема 30 од сумата    |
| 6    | T \|\| F \|\| T          | Цена=301, Попуст=0, Количина=11   | Висока цена и количина >10       | Се одзема 30 од сумата    |
| 7    | F \|\| T \|\| T          | Цена=300, Попуст=0.1, Количина=11 | Попуст и количина >10            | Се одзема 30 од сумата    |
| 8    | T \|\| T \|\| T          | Цена=301, Попуст=0.1, Количина=11 | Сите три подуслови се исполнети  | Се одзема 30 од сумата    |

# Објаснување на напишаните unit tests

Секој тест случај покрива една или повеќе гранки/услови од функцијата.

Функцијата `checkCart_EveryStatement` ги тестира сите гранки од CFG. Се користат истите test cases, со кои се покриваат
сите exceptions, кои може да се стигнат, како и 2 test - cases за discount > 0 (
`allItems = [Item("banana", 2, 400, 0.0)] cardNumber = "1234567890123456"` и
`allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "1234567890123456"`).

Функцијата `checkCart_MultipleCondition` ги покрива 8те комбинации за условот:
`item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10`. За сите случаи, cardNumber не се испитува,
па затоа е нерелевантен.

### Test 1: `false || false || false`

```java
// Test 1: false || false || false
List<`item> `items1 =List.

of(new `item("`item1", 10,300,0));
double result1 = SILab2.checkCart(`items1, validCard);

assertEquals(3000,result1);
```

Цената не надминува 300, нема попуст, и нема над 10 парчиња од item-от. Условот не се исполнува, па не се намалува
сумата за 30. Бидејќи нема попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална
сума: 3000.

---

### Test 2: `true || false || false`

```java
// Test 2: true || false || false
List<`item> `items2 =List.

of(new `item("`item2", 10,301,0));
double result2 = SILab2.checkCart(`items2, validCard);

assertEquals(2980,result2);
```

Цената надминува 300, нема попуст, и нема над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Поради тоа
што нема попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 2970.

---

### Test 3: `false || true || false`

```java
// Test 3: false || true || false
List<`item> `items3 =List.

of(new `item("`item3", 10,300,0.1));
double result3 = SILab2.checkCart(`items3, validCard);

assertEquals(2670,result3);
```

Цената не надминува 300, има попуст, и нема над 10 парчиња од item-от. Условот се исполнува, па се намалува сумата за

30. Поради попустот, се додава намалената цена:
    `item.getPrice() * ((1 -/ tem.getDiscount()) * item.getQuantity())`. <br><br> Финална сума: 2670.

---

### Test 4: `false || false || true`

```java
// Test 4: false || false || true
List<`item> `items4 =List.

of(new `item("`item4", 11,300,0));
double result4 = SILab2.checkCart(`items4, validCard);

assertEquals(3270,result4);
```

Цената не надминува 300, нема попуст, но има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Бидејќи
нема попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 3270.

---

### Test 5: `true || true || false`

```java
// Test 5: true || true || false
List<`item> `items5 =List.

of(new `item("`item5", 10,301,0.1));
double result5 = SILab2.checkCart(`items5, validCard);

assertEquals(2679,result5);
```

Цената надминува 300, има попуст, и нема над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Се додава
намалената цена поради попустот: `item.getPrice() * ((1 - item.getDiscount()) * item.getQuantity())`. <br><br> Финална
сума: 2679.

---

### Test 6: `true || false || true`

```java
// Test 6: true || false || true
List<`item> `items6 =List.

of(new `item("`item6", 11,301,0));
double result6 = SILab2.checkCart(`items6, validCard);

assertEquals(3281,result6);
```

Цената надминува 300, нема попуст, и има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Поради
отсуство на попуст, се додава полната цена: `item.getPrice() * item.getQuantity()`. <br><br> Финална сума: 3281.

---

### Test 7: `false || true || true`

```java
// Test 7: false || true || true
List<`item> `items7 =List.

of(new `item("`item7", 11,300,0.1));
double result7 = SILab2.checkCart(`items7, validCard);

assertEquals(2940,result7);
```

Цената не надминува 300, има попуст, и има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Се додава
намалената цена поради попустот: `item.getPrice() * ((1 - item.getDiscount()) * item.getQuantity())`. <br><br> Финална
сума: 2940.

---

### Test 8: `true || true || true`

```java
// Test 8: true || true || true
List<`item> `items8 =List.

of(new `item("`item8", 11,301,0.1));
double result8 = SILab2.checkCart(`items8, validCard);

assertEquals(2949.9,result8);
```

Цената надминува 300, има попуст, и има над 10 парчиња. Условот се исполнува, па се намалува сумата за 30. Се додава
намалената цена поради попустот: `item.getPrice() * ((1 - item.getDiscount()) * item.getQuantity())`. <br><br> Финална
сума: 2949.9.

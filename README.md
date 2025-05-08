# Втора лабораториска вежба по Софтверско инженерство 
## Мартин Јанев , бр. на индекс 236040

Control Flow Graph

![image](https://github.com/user-attachments/assets/e0082732-6d48-4b25-a478-97b9d5443135)

Цикломатска комплексност
Цикломатската комплексност на овој код е 7, истата ја добив преку формулата P+1, каде што P е бројот на предикатни јазли. Во случајoв P=6, па цикломатската комплексност изнесува 7.

# Тест случаи според критериумот Every statement
allItems = null, cardNumber = any - Овој case е кога листата allItems е празна => без разлика што има стрингот cardNumber, ќе врати RuntimeException("allItems list can't be null!")

allItems = [Item(null, 2, 400, 0.1)] cardNumber = "1234567890123456" - има елемент во allItems, кој нема име (null) - ќе врати RuntimeException("Invalid item!")

allItems = [Item("", 2, 400, 0.1)] cardNumber = "1234567890123456" - има елемент во allItems, кој има име празен string (length=0), ќе врати RuntimeException("Invalid item!")

allItems = [Item("banana", 2, 400, 0.1)] cardNumber = null - валиден елемент во allItems, cardNum e null, ќе врати RuntimeException("Invalid card number!")

allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "12345" - валиден елемент во allItems, cardNum e string, што не е 16 карактери, ќе врати RuntimeException("Invalid card number!")

allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "A234567890123456" - валиден елемент во allItems, невалиден карактер во cardNum, ќе врати RuntimeException("Invalid character in card number!")

allItems = [Item("banana", 2, 400, 0.0)] cardNumber = "1234567890123456" - валиден елемент во allItems без discount и валиден cardNumber, ќе помине точно, со тоа што, за секој елемент во allItems,sum += item.getPrice()*item.getQuantity();

allItems = [Item("banana", 2, 400, 0.1)] cardNumber = "1234567890123456" - валиден елемент во allItems со discount и валиден cardNumber, ќе помине точно, со тоа што, за секој елемент во allItems, sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity();

Минимален број тест случаи, за да се постигне овој критериум е 5, и тоа 4 за секој RuntimeException, што се јавува во кодот и уште еден, со кој се изминува задачата без појава на Exception


# Тест случаи според критериумот Multiple Condition 
## if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
За условот: if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

За да се задоволи Multiple Condition критериумот, мора да се покријат сите комбинации од логичките вредности на трите подуслови. Потребни се точно 8 тест случаи.

![image](https://github.com/user-attachments/assets/4826eecc-fc61-4281-9c72-905428f5aaa3)


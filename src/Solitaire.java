//На столе лежат колоды игральных карт. В самой тоненькой колоде – p карт, во второй – p+1,
// в третьей – p+2, …, в последней – k карт.
// Старуха Шапокляк раскладывает пасьянс. Беря в руки любую из колод, она, если число карт в ней четное,
// на место возвращает колоду, наполовину уменьшив число карт в ней (лишние убирает в ящик),
// а если количество карт в колоде нечетное, то утраивает их количество и добавляет еще одну карту,
// а уже тогда кладет колоду на стол (карт у нее в ящике для этой операции достаточно).
// Если в какой-то колоде остается две карты, она больше ее не трогает.
// Пасьянс сходится, если во всех колодах остается по две карты.
//
//Требуется написать программу, которая определит сходится ли пасьянс,
// и если сходится – сколько раз должна старуха Шапокляк брать со стола карты.
public class Solitaire {
    static void main() {
        //Пользователь вводит мал и больш.число карт в колоде
        int small = Integer.parseInt(IO.readln("Выедите количество карт в самой тонкой колоде: "));
        int big = Integer.parseInt(IO.readln("Выедите количество карт в последней колоде: "));
        //amount= ?
        //Вычислить колтчество действий
        int count = countSteps(small, big);
        //Вывести count
        IO.println(count);
}
private static int countSteps(int small, int big){
       int counter = 0;
       int amountDesks = big-small+1;
       System.out.println("amountDesks = " + amountDesks);
       for (int desk = small; desk<= big; desk++){
           int x = desk;
           //Проделать хитрые манипуляции по условиям пасьянса, изменяя при этом счетчик
           counter +=countManipulationsWithDesk(x);
       }
       return counter;
}
 static int countManipulationsWithDesk(int size)  {
     System.out.println("size = " + size);
     int counter = 0;
     while (size != 2) {
          if (size % 2 == 0) {
              size /= 2;
          } else {
              size = size * 3 + 1;
          }
          counter++;
          System.out.println(counter + ") size " + size);
      }
      System.out.println("counter = " + counter);
       return counter;
   }
}
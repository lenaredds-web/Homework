import java.util.Scanner;

//В рождественский вечер на окошке стояло три цветочка, слева направо: герань, крокус и фиалка.
// Каждое утро Маша вытирала окошко и меняла местами стоящий справа цветок с центральным цветком.
// А Таня каждый вечер поливала цветочки и меняла местами левый и центральный цветок.
// Требуется определить порядок цветов ночью по прошествии K дней.
public class Flowers {
    static void main(String[] args) {
        //ввод исходных данных
        System.out.println("Введите количество дней: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            int k = scanner.nextInt();
            int reminder = k % 3;
            if (reminder == 0) {
                System.out.println("GCV");
            } else if (reminder == 1) {
                System.out.println("VGC");
            } else {
                System.out.println("CVG");
            }
        }
            scanner.close();


        }
    }
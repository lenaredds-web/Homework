import java.util.Scanner;
// Кузнечик Тёма очень любит прыгать по полянке, на которой он живет.
// Но однажды беда обрушилась на его голову. На его полянке поселилась злобная лягушка, которая была бы не против полакомиться кузнечиком Тёмой.
// Однако Тёма решил не унывать и все-таки продолжать прыгать и резвиться на полянке.
// Однако теперь ему надо делать это весьма осторожно. Для этого он хочет составить план своего путешествия.

//Тёма хочет посетить точки A1=(x1, y1), A2=(x2, y2), ..., An=(xn, yn)
// (можно считать, что изначально он находится в точке A1, затем прыгает в точку A2, потом в A3 и т.д.).
// Так же ему известно, что лягушка живет в точке B=(x, y) и имеет длину языка L.
// Таким образом, если после приземления Тёма оказывается на расстоянии не более L от точки B, то лягушка его съест,
// то есть лягушка может съесть Тёму только, когда он находится на земле. Во время полета съесть Тёму лягушка не может.

//Ваша задача помочь Тёме определить, будет ли он съеден лягушкой или нет.

//Входные данные
//В первой строке входного файла INPUT.TXT записано целое число N (1 ≤ N ≤ 105), координаты точки B и число L (0 ≤ L ≤ 5000).
// В следующих N строках записаны координаты точек Ai. Все координаты – это целые числа по модулю не превосходящие 104 .
// В выходной файл OUTPUT.TXT выведите «Yes», если кузнечик успешно завершит свое путешествие в точке An и не будет съеден в ней.
// Иначе выведите номер точки, после приземления в которую Тёму съест лягушка. Если Тёма может быть съеден лягушкой в нескольких точках, выведите точку,
// заданную раньше во входном файле (лягушка, разумеется, использует свой первый шанс, чтобы съесть кузнечика).
public class HopperTyema {
    //
        public static void main(String[] args) {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Введите количество точек в маршруте (N), координаты лягушки (x, y) и длину языка лягушки (L) (всего 4): ");

                   int n = scanner.nextInt();


                    long frogX = scanner.nextLong();


                    long frogY = scanner.nextLong();


                    long frogL = scanner.nextLong();

                    Point frogPosition = new Point(frogX, frogY);


                    Frog frog = new Frog(frogPosition, frogL);

                    System.out.println("Введите координаты " + n + " точек (по 2 числа на строку):");


                    Point[] route = new Point[n];


                    for (int i = 0; i < n; i++) {

                        long ax = scanner.nextLong();

                        long ay = scanner.nextLong();

                        route[i] = new Point(ax, ay);
                    }


                    Hopper tyoma = new Hopper(route);

                    // Проверяем съедят кузнечика или нет. Если выжил - вернет -1.
                    int deathIndex = tyoma.findDeathPoint(frog);

                    // если deathIndex не равен -1, значит кузнечика съели
                    if (deathIndex != -1) {
                        // порядковый номер точки, на которой съели кузнечика.
                        System.out.println("Увы! 🐸 Лягушка съела Тёму на точке номер " + (deathIndex + 1) + ".");
                    } else {
                        // Если deathIndex равен -1, значит цикл дошел до конца и кузнечика не съели
                        System.out.println("Ура! 🦗 Тёма успешно перепрыгнул все " + n + " точек и выжил!");
                    }
                    scanner.close();
                }
            }


            class Point {
                long x;
                long y;

                public Point(long x, long y) {
                    this.x = x;
                    this.y = y;
                }


                public long distanceSquaredTo(Point other) {

                    long dx = this.x - other.x;

                    long dy = this.y - other.y;


                    return dx * dx + dy * dy;
                }
            }


            class Frog {
                Point position;
                // Длина языка лягушки
                long tongueLength;


                public Frog(Point position, long tongueLength) {
                    this.position = position;
                    this.tongueLength = tongueLength;
                }


                public boolean canEat(Point p) {
                    // Считаем квадрат максимального расстояния, на которое может выстрелить язык
                    long maxDistanceSquared = tongueLength * tongueLength;

                    // Считаем фактический квадрат расстояния от лягушки до точки p
                    long actualDistanceSquared = position.distanceSquaredTo(p);

                    // Еслирасстояние меньше или равно длине языка, возвращаем true (может съесть)
                    return actualDistanceSquared <= maxDistanceSquared;
                }
            }

            class Hopper {

                Point[] route;


                public Hopper(Point[] route) {
                    this.route = route;
                }

                //возвращает порядковый номер точки, на которой его съели.
                // Если не съели, возвращает -1.
                public int findDeathPoint(Frog frog) {

                    for (int i = 0; i < route.length; i++) {


                        if (frog.canEat(route[i])) {

                            return i;
                        }
                    }

                    return -1;
                }
            }
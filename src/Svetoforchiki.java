import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите N (количество перекрестков):");
        int n = sc.nextInt();

        System.out.print("Введите M (количество тоннелей): ");
                int m = sc.nextInt();

        System.out.println("Введите " + m + " пар чисел (тоннели) через пробел или Enter:");

        // новый объект Подземелье
        Dungeon dungeon = new Dungeon(n);

        // Читаем M тоннелей из консоли
        for (int k = 0; k < m; k++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            dungeon.addTunnel(i, j);
        }

        //  результат
        System.out.print("Ответ: ");
        dungeon.printResult();

        sc.close();
    }
}

// Класс Подземелье
class Dungeon {
    Intersection[] crossroads;

    public Dungeon(int n) {
        crossroads = new Intersection[n + 1];
        for (int i = 1; i <= n; i++) {
            crossroads[i] = new Intersection(i);
        }
    }

    public void addTunnel(int i, int j) {
        crossroads[i].addLight();
        crossroads[j].addLight();
    }

    // Выводим результат в консоль через System.out
    public void printResult() {
        for (int i = 1; i < crossroads.length; i++) {
            System.out.print(crossroads[i].getLights() + " ");
        }
        System.out.println(); // Переход на новую строку
    }
}

// Класс Перекресток
class Intersection {
    int id;
    int lights;

    public Intersection(int id) {
        this.id = id;
        this.lights = 0;
    }

    public void addLight() {
        lights++;
    }

    public int getLights() {
        return lights;
    }
}
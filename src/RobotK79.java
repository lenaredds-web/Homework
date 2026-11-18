import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
public class RobotK79 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

             System.out.print("Введите программу робота: ");
            String commands = scanner.nextLine().trim().toUpperCase();

            Robot robot = new Robot();


            int steps = robot.executeCommands(commands);


            if (steps == -1) {
                System.out.println("Робот не вернется на пройденное место. (-1)");
            } else {
                System.out.println("Робот вернется на старое место после " + steps + " шагов.");
            }

            scanner.close();
        }
    }


    enum Direction {
        NORTH, EAST, SOUTH, WEST;


        public Direction turnLeft() {

            return values()[(this.ordinal() + 3) % 4];
        }


        public Direction turnRight() {
            return values()[(this.ordinal() + 1) % 4];
        }
    }


    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // сравниваем
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }


        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    class Robot {
        private Point position;
        private Direction direction;
        private Set<Point> visited;

        public Robot() {
            this.position = new Point(0, 0); // Стартовая позиция
            this.direction = Direction.NORTH;
            this.visited = new HashSet<>();
            this.visited.add(position);
        }


        public int executeCommands(String commands) {
            int steps = 0; // Счетчик сделанных шагов


            for (char cmd : commands.toCharArray()) {

                if (cmd == 'L') {
                    direction = direction.turnLeft();
                }
                else if (cmd == 'R') {
                    direction = direction.turnRight();
                }
                else if (cmd == 'S') {
                    steps++;
                    moveForward();

                    if (visited.contains(position)) {
                        return steps;
                    }


                    visited.add(position);
                }
            }
            return -1;
        }


        private void moveForward() {
            // Создаем новые координаты на основе текущих
            int newX = position.x;
            int newY = position.y;


            switch (direction) {
                case NORTH: newY++; break; // вверх
                case SOUTH: newY--; break; // вниз
                case EAST:  newX++; break; // вправо
                case WEST:  newX--; break; // влево
            }


            position = new Point(newX, newY);
        }
    }

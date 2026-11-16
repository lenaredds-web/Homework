import java.util.Arrays;
import java.util.Scanner;
//Компания из N человек собирается пойти в байдарочный поход, i-ый человек характеризуется своей массой Mi кг.
// На лодочной базе имеется в наличии неограниченное количество одинаковых байдарок.
// Каждая байдарка может вмещать одного или двух людей. Байдарки имеют грузоподъемность D кг.
// Какое наименьшее количество байдарок придется арендовать компании, чтобы всем отправиться в поход?
public class Baidarky {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите количество людей (N): ");
            int n = scanner.nextInt();
            System.out.print("Введите грузоподъемность байдарки (D): ");
            int d = scanner.nextInt();


            Person[] people = new Person[n];

            System.out.println("Введите вес каждого из " + n + " человек:");
            for (int i = 0; i < n; i++) {
                int weight = scanner.nextInt();
                people[i] = new Person(weight);
            }

            // Создаем объект базы проката и передаем ей людей и грузоподъемность
            Baza company = new Baza(people, d);

            // Узнаем минимальное количество байдарок
            int boatsNeeded = company.calculateMinBoats();

            System.out.println("Минимальное количество байдарок: " + boatsNeeded);

            scanner.close();
        }
    }

   // сортировать людей по весу
    class Person implements Comparable<Person> {
        int weight;

        public Person(int weight) {
            this.weight = weight;
        }

        // сравниваем вес
        @Override
        public int compareTo(Person other) {
            return this.weight - other.weight;
        }
    }


    class Boat {
        int maxCapacity;      // Грузоподъемность (D)
        int currentWeight;    // Сколько сейчас весят пассажиры
        int peopleCount;      // Сколько человек сидит (максимум 2)

        public Boat(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            this.currentWeight = 0;
            this.peopleCount = 0;
        }

        // Проверяем, можно ли посадить в лодку еще одного человека
        public boolean canAdd(Person person) {

            return peopleCount < 2 && (currentWeight + person.weight <= maxCapacity);
        }


        public void add(Person person) {
            if (canAdd(person)) {
                currentWeight += person.weight;
                peopleCount++;
            }
        }
    }


    class Baza {
        Person[] people;
        int boatCapacity;

        public Baza(Person[] people, int boatCapacity) {
            this.people = people;
            this.boatCapacity = boatCapacity;
        }

        public int calculateMinBoats() {
            // Сортируем людей по весу (от легкого к тяжелому).

            Arrays.sort(people);

            int left = 0;                  // Указатель на самого легкого
            int right = people.length - 1; // Указатель на самого тяжелого
            int boatCount = 0;             // Счетчик байдарок

            // 2. Двигаем указатели навстречу друг другу
            while (left <= right) {
                // Берем нового человека и создаем для него байдарку
                Boat boat = new Boat(boatCapacity);
                boatCount++; // Байдарка арендована

                // Сажаем самого тяжелого (он точно поедет)
                boat.add(people[right]);
                right--; // Сдвигаем указатель, так как этот человек уже в лодке

                // Теперь пытаемся подсадить к нему самого легкого
                // (если это не тот же самый человек, то есть left не перепрыгнул right)
                if (left <= right) {
                    boat.add(people[left]); // Метод add сам проверит, влезет ли он

                    // Если человека посадили (проверяем по количеству в лодке),
                    // то сдвигаем левый указатель.
                    // В нашем методе add() он добавится, только если хватит места.
                    if (boat.peopleCount == 2) {
                        left++;
                    }
                }
            }
            return boatCount;
        }
    }


import java.util.*;

public class Main {
    /*
    1.Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
    2. Пусть дан список сотрудников: Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова,
    Иван Юрин, Петр Лыков, Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина, Мария Рыкова,
    Марина Лугова, Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов.
    Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
    Отсортировать по убыванию популярности. Для сортировки использовать TreeMap.
    3.*Реализовать алгоритм пирамидальной сортировки (HeapSort)
    4.**На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
     */
    public static void main(String[] args) {
        ex1();
        ex2();
        ex3();
        ex4();


    }

    /*
    1.Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
     */
    static void ex1() {
        Map<Integer, String> telBook = new HashMap<>();

        telBook.put(123456, "Иванов");
        telBook.put(321456, "Васильев");
        telBook.put(234561, "Петрова");
        telBook.put(234432, "Иванов");
        telBook.put(654321, "Петрова");
        telBook.put(345678, "Иванов");
        telBook.put(345678, "Иванов2");

        printTel(telBook, "Иванов");
        printTel(telBook, "Петрова");
        printTel(telBook, "Петров");


    }


    static void printTel(Map<Integer, String> telbook, String str) {
        if (telbook.containsValue(str)) {
            System.out.println("Телефоны пользователя " + str);
            for (Map.Entry entry : telbook.entrySet()) {
                if (entry.getValue().equals(str)) {
                    System.out.println(entry.getKey());
                }
            }
        } else {
            System.out.println("Пользователя " + str + " нет");
        }
    }

    /*
        2. Пусть дан список сотрудников: Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова,
        Иван Юрин, Петр Лыков, Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина, Мария Рыкова,
        Марина Лугова, Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов.
        Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
        Отсортировать по убыванию популярности. Для сортировки использовать TreeMap.
     */
    static void ex2() {
        TreeMap<String, List<String>> names = new TreeMap<>();
        String text = "Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова, " +
                "Иван Юрин, Петр Лыков, Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина, Мария Рыкова, " +
                "Марина Лугова, Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов";
        String[] fio_arr = text.split(", ");

        for (String val : fio_arr) {
            String[] s = val.split(" ");
            if (names.containsKey(s[0])) {
                List<String> list = names.get(s[0]);
                list.add(s[1]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s[1]);
                names.put(s[0], list);
            }
        }
        System.out.println("Начальная структура");
        System.out.println(names);
        TreeMap<String, List<String>> names_sorted = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (names.get(o2).size() == names.get(o1).size()) {
                    if (o1.equals(o2)) {
                        return 0;
                    } else {
                        return o1.compareTo(o2);
                    }
                }

                return names.get(o2).size() - names.get(o1).size();

            }
        });
        names_sorted.putAll(names);
        System.out.println("Отсортированная структура");
        System.out.println(names_sorted);


        for (Map.Entry entry : names_sorted.entrySet()) {
            System.out.print(entry.getKey());
            System.out.println(" - повторов " + names_sorted.get(entry.getKey()).size()
                    + " (" + names_sorted.get(entry.getKey()) + ")");

        }


    }

    ;

    /*
        3.*Реализовать алгоритм пирамидальной сортировки (HeapSort)
     */
    static void ex3() {
        int[] array = getRandomArray(15, 100);

        int heapSize = array.length;
        System.out.println("Изначальный массив:");

        System.out.println(Arrays.toString(array));

        for (int i = array.length / 2; i >= 0; i--) {
            heapify(array, i, heapSize);
        }

        System.out.println("Куча:");
        printHeap(array);

        while (heapSize > 1) {
            swap(array, 0, heapSize - 1);
            heapSize--;
            heapify(array, 0, heapSize);
        }

        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(array));
    }

    //Создание случайного массива длиной n, максимальное число Max
    static int[] getRandomArray(int n, int max) {
        int[] arr = new int[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(max + 1);
        }
        return arr;
    }

    private static void heapify(int[] a, int i, int heapSize) {
        int left_child_id = 2 * i + 1;
        int right_child_id = 2 * i + 2;
        int max_id = i;
        if (left_child_id < heapSize && a[i] < a[left_child_id]) {
            max_id = left_child_id;
        }
        if (right_child_id < heapSize && a[max_id] < a[right_child_id]) {
            max_id = right_child_id;
        }
        if (i != max_id) {
            swap(a, i, max_id);
            heapify(a, max_id, heapSize);
        }
    }


    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void printHeap(int[] a) {
        int level = 0;
        int max_level = (int) Math.ceil(Math.log(a.length) / Math.log(2));
        int level_count = 1;
        int max_level_count = 1;

        for (int i = 0; i < a.length; i++) {
            if (i == max_level_count) {
                level++;
                level_count *= 2;
                max_level_count += level_count;
                System.out.println();
                //System.out.print(level);

            }

            String separ = "";

            for (int j = 1; j < (max_level - level); j++) {
                separ += "  ";
                for (int k = 1; k < j; k++) {
                    separ += "  ";
                }
            }


            if (a[i] < 10) {
                System.out.print(separ + " " + a[i] + "  " + separ);
            } else {
                System.out.print(separ + a[i] + "  " + separ);
            }

        }
        System.out.println();
    }
    private static void ex4(){
        int[] ferz_row = {0,0,0,0,0,0,0,0};

        while (true){
            if (check (ferz_row)){
                break;
            }
            ferz_row[0]+=1;
            check_max(ferz_row);
        }
        printPole(ferz_row);
    }
    private static boolean check (int[] a){
        for (int i = 0; i <a.length; i++) {
            for (int j = 0; j <a.length ; j++) {
                if (i!=j) {
                    if ((a[i] == a[j] ) | (Math.abs(i - j) == Math.abs(a[i] - a[j]))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private static void check_max(int[] a){
        for (int i = 0; i < a.length-1; i++) {
            if (a[i]==a.length){
                a[i]=0;
                a[i+1]+=1;
            }

        }
    }

    private static void printPole (int[] a){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String figa=" ";
                if (a[i]==j){
                    figa="Ф";
                }
                System.out.print("|"+figa);

            }
            System.out.println("|");
        }
    }
}


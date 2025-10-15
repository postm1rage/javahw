package lab3.task3;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // a
        List<Human> humanList = Arrays.asList(
            new Human("Ivan", "Petrov", 25),
            new Human("Maria", "Ivanova", 30),
            new Human("Alexey", "Sidorov", 22),
            new Human("Olga", "Ivanova", 28),
            new Human("Peter", "Petrov", 35)
        );
        
        System.out.println("Original list:");
        humanList.forEach(System.out::println);
        System.out.println();
        
        System.out.println("a) HashSet:");
        Set<Human> hashSet = new HashSet<>(humanList);
        hashSet.forEach(System.out::println);
        System.out.println();

        // HashSet не гарантирует порядок элементов, использует хэш-таблицу для быстрого доступа
        // Порядок зависит от хэш-кодов и может меняться между запусками
        
        // b
        System.out.println("b) LinkedHashSet:");
        Set<Human> linkedHashSet = new LinkedHashSet<>(humanList);
        linkedHashSet.forEach(System.out::println);
        System.out.println();
        
        // LinkedHashSet сохраняет порядок вставки элементов, использует связный список
        // в дополнение к хэш-таблице для поддержания порядка

        // c
        System.out.println("c) TreeSet (with Comparable):");
        Set<Human> treeSet = new TreeSet<>(humanList);
        treeSet.forEach(System.out::println);
        System.out.println();

        // TreeSet с Comparable автоматически сортирует элементы согласно natural ordering
        // (в нашем случае по фамилии, затем по имени), использует красно-черное дерево
        
        // d
        System.out.println("d) TreeSet with last name comparator:");
        Set<Human> treeSetByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeSetByLastName.addAll(humanList);
        treeSetByLastName.forEach(System.out::println);
        System.out.println();

        // TreeSet с HumanComparatorByLastName сортирует только по фамилии, при этом
        // люди с одинаковыми фамилиями считаются равными (компаратор возвращает 0)
        // и не добавляются в множество - поэтому видим только по одному представителю каждой фамилии
        
        // e
        System.out.println("e) TreeSet with anonymous age comparator:");
        Set<Human> treeSetByAge = new TreeSet<>(new Comparator<Human>() {
            @Override
            public int compare(Human h1, Human h2) {
                return Integer.compare(h1.getAge(), h2.getAge());
            }
        });

        treeSetByAge.addAll(humanList);
        treeSetByAge.forEach(System.out::println);

        // TreeSet с анонимным компаратором по возрасту сортирует по возрасту,
        // люди с одинаковым возрастом также считаются равными и не добавляются




        // - HashSet: максимальная производительность, нет гарантий порядка
        // - LinkedHashSet: сохраняет порядок вставки, немного медленнее HashSet
        // - TreeSet: автоматическая сортировка, но O(log n) для операций
        // - В TreeSet дубликаты определяются через compareTo/compare (возврат 0), а не через equals.

    }
}
//easy
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " | " + age + " | " + salary;
    }
}

public class EmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, 50000),
            new Employee("Bob", 25, 45000),
            new Employee("Charlie", 28, 55000)
        );

        // Sort by name using lambda
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));

        System.out.println("Sorted by Name:");
        employees.forEach(System.out::println);

        // Sort by salary using lambda
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));

        System.out.println("\nSorted by Salary:");
        employees.forEach(System.out::println);
    }
}

// medium
import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Aman", 82.5),
            new Student("Riya", 74.0),
            new Student("Karan", 91.2),
            new Student("Sneha", 65.3),
            new Student("Tina", 78.9)
        );

        List<String> filteredAndSorted = students.stream()
            .filter(student -> student.marks > 75)
            .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks)) // descending order
            .map(student -> student.name)
            .collect(Collectors.toList());

        System.out.println("Students scoring above 75%:");
        filteredAndSorted.forEach(System.out::println);
    }
}

//hard

import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | " + price;
    }
}

public class ProductStreamOperations {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("Jeans", "Clothing", 40),
            new Product("T-shirt", "Clothing", 20),
            new Product("Blender", "Home Appliances", 100),
            new Product("Microwave", "Home Appliances", 200)
        );

        // Group products by category
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(product -> product.category));

        System.out.println("Products grouped by category:");
        groupedByCategory.forEach((category, prodList) -> {
            System.out.println(category + ": " + prodList);
        });

        // Find the most expensive product in each category
        Map<String, Optional<Product>> mostExpensiveProductByCategory = products.stream()
            .collect(Collectors.groupingBy(
                product -> product.category,
                Collectors.maxBy(Comparator.comparingDouble(product -> product.price))
            ));

        System.out.println("\nMost expensive product in each category:");
        mostExpensiveProductByCategory.forEach((category, product) -> {
            System.out.println(category + ": " + product.orElse(null));
        });

        // Calculate the average price of all products
        double averagePrice = products.stream()
            .mapToDouble(product -> product.price)
            .average()
            .orElse(0.0);

        System.out.println("\nAverage price of all products: " + averagePrice);
    }
}

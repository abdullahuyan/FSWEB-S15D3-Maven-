package org.example;


import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();

        // Duplicate id’ler ekleniyor
        employees.add(new Employee(1, "Ann", "Smith"));
        employees.add(new Employee(2, "Bob", "Johnson"));
        employees.add(new Employee(3, "Carol", "Williams"));
        employees.add(new Employee(1, "Ann", "Smith"));  // duplicate
        employees.add(new Employee(2, "Bob", "Johnson")); // duplicate
        employees.add(new Employee(4, "David", "Brown"));

        System.out.println("Employees: " + employees);

        System.out.println("Duplicates: " + findDuplicates(employees));
        System.out.println("Uniques Map: " + findUniques(employees));
        System.out.println("Without duplicates: " + removeDuplicates(employees));
    }

    // ✅ tekrar eden employee’leri döner
    public static List<Employee> findDuplicates(List<Employee> list) {
        Set<Employee> seen = new HashSet<>();
        List<Employee> duplicates = new ArrayList<>();

        for (Employee e : list) {

            if (!seen.add(e)) {
                duplicates.add(e);
            }
        }
        return duplicates;
    }

    // ✅ tekrar edenlerden sadece bir tanesini ve tekrar etmeyenleri Map'e ekler
    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> uniques = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();

        for (Employee e : list) {
            if (e == null) continue;
            counts.put(e.getId(), counts.getOrDefault(e.getId(), 0) + 1);
        }

        for (Employee e : list) {
            if (e == null) continue;
            if (counts.get(e.getId()) >= 1 && !uniques.containsKey(e.getId())) {
                uniques.put(e.getId(), e); // duplicate ise bir tanesini koy
            }
        }

        return uniques;
    }

    // ✅ listede birden fazla kez geçenleri tamamen çıkarır
    public static List<Employee> removeDuplicates(List<Employee> list) {
        Map<Integer, Integer> counts = new HashMap<>();
        List<Employee> result = new LinkedList<>();

        for (Employee e : list) {
            if (e == null) continue;
            counts.put(e.getId(), counts.getOrDefault(e.getId(), 0) + 1);
        }

        for (Employee e : list) {
            if (e == null) continue;
            if (counts.get(e.getId()) == 1) { // sadece tek geçenleri al
                result.add(e);
            }
        }
        return result;
    }
}
package com.vidix;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {


    static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(
                new Employee("Tuvok", "Dawoodi", 5000.0, List.of("Project 1", "Project 2"))
        );

        employees.add(
                new Employee("Vidix", "Gupta", 6000.0, List.of("Project 1", "Project 3"))
        );

        employees.add(
                new Employee("Belana", "Torres", 5500.0, List.of("Project 3", "Project 4"))
        );
    }


    public static void main(String[] args) {
        // write your code here


        // for each
        employees.stream().forEach(employee -> {
            //   System.out.println(employee);
        });

        // map and collect (eg: increase salary by 10%)
        List<Employee> increasedSal = employees.stream()
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                )).collect(toList());

        // filter  and map (increase salary where sal <= 5500)
        final List<Employee> increasedFilSal = employees.stream()
                .filter(employee -> employee.getSalary() <= 5500)
                .map(employee -> new Employee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.20,
                        employee.getProjects()))
                .collect(toList());


        // filter and map (increase salary where sal <= 5500) fin first
        final Employee increasedFilSalFirst = employees.stream()
                .filter(employee -> employee.getSalary() <= 5500)
                .map(employee -> new Employee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.20,
                        employee.getProjects()))
                .findFirst().orElse(null);


        // sorting
        final List<Employee> sortedBySal = employees.stream()
                .sorted((e1, e2) -> e1.getSalary()
                        .compareTo(e2.getSalary()))
                .collect(toList());



        final List<Employee> sortedByLast = employees.stream()
                .sorted((e1, e2) -> e1.getLastName()
                        .compareToIgnoreCase(e2.getLastName()))
                .collect(toList());



        // max (find max salary)
        final Employee maxSalary = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);

        // reduce get sum of salaries
        final Double salarySum = employees.stream()
                .map(employee -> employee.getSalary())
                .reduce(0.00, Double::sum);



        System.out.println(salarySum);
    }
}

package com.vidix;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        employees.add(
                new Employee("Nelix", "Xnloge", 5700.0, List.of("Project 9", "Project 5"))
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


        System.out.println(employees);

        //--
        //short circuit (select only one last element)
        final List<Employee> lastElement = employees.stream()
                .skip(employees.size() - 1)
                .limit(1)
                .collect(toList());


        //Increase salary by 100 and sort employees by salary
        final List<Employee> collect = employees.stream()
                .map(employee -> new Employee(employee.getFirstName(), employee.getLastName(), employee.getSalary() + 100, employee.getProjects()))
                .sorted(((o1, o2) -> o1.getSalary().compareTo(o2.getSalary())))
                .collect(toList());


        //Filter salary lower than 6000 increase it by 100 and get sum
        final Double sumOfIncreasedSalary = employees.stream()
                .filter(employee -> employee.getSalary() < 6000)
                .map(employee -> employee.getSalary())
                .reduce(0.00, Double::sum);

        //Increase only Vidix salary by 500
        final List<Employee> vidixIncreased = employees.stream()
                .filter(employee -> employee.getFirstName().contains("V"))
                .map(employee -> new Employee(employee.getFirstName(), employee.getLastName(), employee.getSalary() + 500, employee.getProjects()))
                .collect(toList());

        //Increase salary greater than 5500 and get sum of it

        final Double sumOfSalary = employees.stream()
                .filter(employee -> employee.getSalary() > 5000)
                .map(employee -> employee.getSalary() + 200)
                .reduce(0.00, Double::sum);

        System.out.println(sumOfSalary);

    }
}

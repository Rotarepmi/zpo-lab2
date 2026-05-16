package com.validation;

import com.validation.exception.ValidationException;
import com.validation.validator.Validator;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        
        // Test 1: Poprawne dane
        System.out.println("--- Test 1: Poprawne dane ---");
        student.setImie("Jan");
        student.setNazwisko("Kowalski");
        student.setEmail("jan.kowalski@pbs.edu.pl");
        student.setNrIndeksu("123456");
        testValidation(student);

        // Test 2: Błędne imię (za krótkie) i brak nazwiska (null)
        System.out.println("\n--- Test 2: Błędne imię (za krótkie) i null w nazwisku ---");
        student.setImie("Jo");
        student.setNazwisko(null);
        testValidation(student);

        // Test 3: Błędny email i błędny numer indeksu
        System.out.println("\n--- Test 3: Błędny email i błędny numer indeksu ---");
        student.setImie("Adam");
        student.setNazwisko("Nowak");
        student.setEmail("zly-email");
        student.setNrIndeksu("123");
        testValidation(student);

        // Test 4: Pusty email (NotEmpty)
        System.out.println("\n--- Test 4: Pusty email (NotEmpty) ---");
        student.setEmail("");
        testValidation(student);
    }

    private static void testValidation(Student student) {
        try {
            Validator.validate(student);
            System.out.println("Walidacja przeszła pomyślnie!");
        } catch (ValidationException e) {
            System.out.println("Błędy walidacji:\n" + e.getMessage());
        }
    }
}
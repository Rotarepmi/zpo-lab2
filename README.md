# ZAAWANSOWANE PROGRAMOWANIE OBIEKTOWE - LAB2

### Temat: IMPLEMENTACJA WALIDATORA DANYCH Z WYKORZYSTANIEM ADNOTACJI, REFLEKSJI I WZORCA STRATEGIA

---

### 1. Cel zadania
Celem laboratorium była implementacja uniwersalnego mechanizmu walidacji danych wejściowych opartego na mechanizmie refleksji. System pozwala na automatyczne sprawdzanie poprawności pól obiektów (np. klasy `Student`) oznaczonych dedykowanymi adnotacjami, eliminując konieczność pisania powtarzalnego kodu walidacyjnego.

### 2. Opis implementacji

#### 2.1. Architektura rozwiązania
Projekt został zaprojektowany z wykorzystaniem wzorca **Strategia** oraz **Fabryki**, co zapewnia wysoką elastyczność i łatwość rozszerzania o nowe reguły:
- **Adnotacje walidacyjne**: Zdefiniowano zestaw adnotacji (`@NotNull`, `@NotEmpty`, `@Size`, `@NrIndeksu`, `@Email`) służących do oznaczania reguł bezpośrednio nad polami klas.
- **Strategie walidacji**: Każda adnotacja posiada odpowiadającą jej klasę implementującą interfejs `ValidationStrategy`, która zawiera konkretną logikę sprawdzania warunków.
- **Validator**: Centralna klasa wykorzystująca refleksję (`getDeclaredFields`, `getAnnotations`) do przeszukiwania pól obiektu i uruchamiania przypisanych im strategii.
- **System wyjątków**: W przypadku wykrycia błędów rzucany jest dedykowany wyjątek `ValidationException`, zbierający wszystkie komunikaty o błędach w jednej strukturze.

#### 2.2. Przetwarzanie i Refleksja
Proces walidacji w metodzie `validate` przebiega następująco:
- Pobranie klasy obiektu i iteracja po wszystkich jego polach (również prywatnych dzięki `setAccessible(true)`).
- Pobranie wszystkich adnotacji przypisanych do danego pola.
- Pobranie odpowiedniej strategii z fabryki `ValidationStrategyFactory` na podstawie typu adnotacji.
- Wykonanie walidacji i opcjonalne dodanie komunikatu o błędzie do zbiorczej listy.
- Rzucenie wyjątku, jeśli lista błędów nie jest pusta.

#### 2.3. Wzorce projektowe
- **Strategia**: Pozwala na odseparowanie logiki walidacji od głównego kodu walidatora i samej klasy danych.
- **Fabryka**: Zarządza instancjami strategii i mapuje je na odpowiednie typy adnotacji.

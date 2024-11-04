import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        int cols = 20;
        int rows = 10;

        Box Box = new Box(rows, cols);
        Scanner scanner = new Scanner(System.in);
        String cellsFilename = "cells.txt";

        try {
            Box.loadFromFile(cellsFilename);
        } catch (IOException e) {
            System.out.println("Не вдалося завантажити файл, створено нову камеру схову.");
        }

        boolean running = true;
        while (running) {
            showMenu();

            int[] rowCol;

            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    Box.displayCells();
                    break;
                case 1:
                    rowCol = getRowCol(scanner);
                    Box.displayThingInBox(rowCol[0], rowCol[1]);
                    break;
                case 2:
                    rowCol = getRowCol(scanner);
                    System.out.print("Введіть назву речі: ");
                    String item = scanner.nextLine();
                    Box.storeItemInCell(rowCol[0], rowCol[1], item);
                    break;
                case 3:
                    rowCol = getRowCol(scanner);
                    Box.removeItemInCell(rowCol[0], rowCol[1]);
                    break;
                case 4:
                    try {
                        Box.saveToFile(cellsFilename);
                    } catch (IOException e) {
                        System.out.println("Помилка при збереженні у файл.");
                    }
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Невірна команда. Спробуйте ще раз.");
            }
        }

        try {
            Box.saveToFile(cellsFilename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у файл.");
        }
    }

    /**
     * Відображає меню опцій для користувача.
     */
    static void showMenu() {
        System.out.println("\nОберіть дію:");
        System.out.println("0 - Показати стан комірок");
        System.out.println("1 - Показати річ у комірці");
        System.out.println("2 - Зберегти річ у комірку");
        System.out.println("3 - Видалити річ із комірки");
        System.out.println("4 - Зберегти стан у файл");
        System.out.println("5 - Вийти");
    }

    /**
     * Запитує у користувача введення рядка та стовпця комірки.
     * Перевіряє введення, щоб переконатися, що воно в межах допустимого діапазону.
     *
     * @param scanner об'єкт Scanner для зчитування введення користувача
     * @return масив, що містить індекси рядка та стовпця
     */
    static int[] getRowCol(Scanner scanner) {
        System.out.print("Введіть рядок (1-10): ");
        int row = scanner.nextInt();
        System.out.print("Введіть стовпець (1-20): ");
        int col = scanner.nextInt();
        scanner.nextLine();

        if (row < 1 || row > 10 || col < 1 || col > 20) {
            System.out.println("Невірні координати. Спробуйте ще раз.");
            return getRowCol(scanner);
        }

        return new int[] {row, col};
    }
}

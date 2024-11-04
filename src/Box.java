import java.io.*;

/**
 * Клас Box представляє камеру схову з сіткою комірок.
 * Кожна комірка може зберігати один предмет, представлений як рядок.
 */
public class Box {
    private String[][] Cells;
    private final int rows;
    private final int cols;

    /**
     * Конструктор Box з вказаною кількістю рядків та стовпців.
     * Ініціалізує всі комірки як порожні.
     *
     * @param rows кількість рядків у камері схову
     * @param cols кількість стовпців у камері схову
     */
    public Box(int rows, int cols) {
        Cells = new String[rows][cols];
        this.rows = rows;
        this.cols = cols;

        initializeCells();
    }

    /**
     * Ініціалізує всі комірки у камері схову як порожні ("-").
     */
    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cells[i][j] = "-";
            }
        }
    }

    /**
     * Відображає поточний стан комірок у камері схову.
     */
    public void displayCells() {
        System.out.println("Стан камери схову:");
        System.out.println("Рядки\\Стовпці");

        System.out.print("   ");
        for (int col = 1; col <= cols; col++) {
            System.out.printf("%4d", col);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < cols; j++) {

                if (Cells[i][j].equals("-")) {
                    System.out.printf("%4s", "-");
                } else {
                    System.out.printf("%4s", "З");
                }
            }
            System.out.println();
        }
    }

    /**
     * Відображає предмет у вказаній комірці.
     *
     * @param row рядок комірки (індекс з 1)
     * @param col стовпець комірки (індекс з 1)
     */
    public void displayThingInBox(int row, int col) {
        if (Cells[row - 1][col - 1].equals("-")) {
            System.out.println("Комірка порожня.");
        } else {
            System.out.println("Річ у комірці " + row + "-" + col + ": " + Cells[row - 1][col - 1]);
        }
    }

    /**
     * Зберігає предмет у вказаній комірці, якщо вона порожня.
     *
     * @param row рядок комірки (індекс з 1)
     * @param col стовпець комірки (індекс з 1)
     * @param item предмет для зберігання у комірці
     */
    public void storeItemInCell(int row, int col, String item) {
        if (Cells[row - 1][col - 1].equals("-")) {
            Cells[row - 1][col - 1] = item;
            System.out.println("Річ збережено в комірці " + row + "-" + col);
        } else {
            System.out.println("Комірка вже зайнята.");
        }
    }

    /**
     * Видаляє предмет з вказаної комірки, якщо вона не порожня.
     *
     * @param row рядок комірки (індекс з 1)
     * @param col стовпець комірки (індекс з 1)
     */
    public void removeItemInCell(int row, int col) {
        if (!Cells[row - 1][col - 1].equals("-")) {
            Cells[row - 1][col - 1] = "-";
            System.out.println("Річ видалено з комірки " + row + "-" + col);
        } else {
            System.out.println("Комірка вже порожня.");
        }
    }

    /**
     * Зберігає поточний стан камери схову у файл.
     *
     * @param filename ім'я файлу для збереження стану
     * @throws IOException якщо виникає помилка вводу/виводу
     */
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                writer.write((i + 1) + "-" + (j + 1) + ": " + Cells[i][j]);
                writer.newLine();
            }
        }
        writer.close();
        System.out.println("Стан збережено у файл.");
    }

    /**
     * Завантажує стан камери схову з файлу.
     *
     * @param filename ім'я файлу для завантаження стану
     * @throws IOException якщо виникає помилка вводу/виводу
     */
    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(": ");
            String[] indices = parts[0].split("-");
            int row = Integer.parseInt(indices[0]) - 1;
            int col = Integer.parseInt(indices[1]) - 1;
            Cells[row][col] = parts[1];
        }
        reader.close();
        System.out.println("Стан завантажено з файлу.");
    }
}
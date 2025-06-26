package org.example;

import java.io.*;
import java.util.Scanner;

// Собственное исключение для ошибок ввода-вывода
class FileIOException extends IOException {
    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class FileData {

    private static final String FILE_NAME = "data.txt";

    // Запись данных в файл
    public static void writeFile(String data) throws FileIOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            throw new FileIOException("Ошибка при записи в файл", e);
        }
    }

    // Считывание данных из файла
    public static String readFile() throws FileIOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileIOException("Файл не найден", e);
        } catch (IOException e) {
            throw new FileIOException("Ошибка при чтении из файла", e);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запись данных
        System.out.println("Введите данные для записи в файл:");
        String dataToWrite = scanner.nextLine();
        try {
            writeFile(dataToWrite);
            System.out.println("Данные успешно записаны.");
        } catch (FileIOException e) {
            System.err.println("Ошибка при записи: " + e.getMessage());
            e.printStackTrace(); // Вывод стека вызовов для отладки
        }

        // Чтение данных
        try {
            String fileContent = readFile();

            System.out.println("Содержимое файла:\n" + fileContent);
        } catch (FileIOException e) {
            System.err.println("Ошибка при чтении: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }
}
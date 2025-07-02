package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileData {

    private static final String FILE_NAME = "data.txt";

    public static void writeFile(String data) throws MyFileIOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            throw new MyFileIOException("Ошибка при записи в файл", e);
        }
    }

    public static String readFile() throws MyFileIOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new MyFileIOException("Файл не найден", e);
        } catch (IOException e) {
            throw new MyFileIOException("Ошибка при чтении из файла", e);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные для записи в файл:");
        String dataToWrite = scanner.nextLine();
        try {
            writeFile(dataToWrite);
            System.out.println("Данные успешно записаны.");
        } catch (MyFileIOException e) {
            System.err.println("Ошибка при записи: " + e.getMessage());
            e.printStackTrace(); // Вывод стека вызовов для отладки
        }

        try {
            String fileContent = readFile();

            System.out.println("Содержимое файла:\n" + fileContent);
        } catch (MyFileIOException e) {
            System.err.println("Ошибка при чтении: " + e.getMessage());
            e.printStackTrace();
        }
        scanner.close();
    }
}
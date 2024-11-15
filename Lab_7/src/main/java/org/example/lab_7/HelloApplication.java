package org.example.lab_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class HelloApplication{
    private static final String URL = "jdbc:mysql://localhost:3306/study";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin1987";

    public static void main(String[] args) {
        String[] sqlFiles = {
                "src/main/resources/org/example/lab_7/dropTableMusic.sql",
                "src/main/resources/org/example/lab_7/task8_DropBook.sql",
                "src/main/resources/org/example/lab_7/task8_DropClient.sql",
                "src/main/resources/org/example/lab_7/music-create.sql",
                "src/main/resources/org/example/lab_7/music-insert.sql",
                "src/main/resources/org/example/lab_7/task1_GetMusicCompositions.sql",
                "src/main/resources/org/example/lab_7/task2_GetCompositionsWithoutMT.sql",
                "src/main/resources/org/example/lab_7/task3_InsertMyComposition.sql",
                "src/main/resources/org/example/lab_7/task4_CreateBook.sql",
                "src/main/resources/org/example/lab_7/task4_CreateClient.sql",
                "src/main/resources/org/example/lab_7/task4_SetBook.sql",
                "src/main/resources/org/example/lab_7/task4_SetClient.sql",
                "src/main/resources/org/example/lab_7/task5_GetSortedBooks.sql",
                "src/main/resources/org/example/lab_7/task6_GetYoungBooks.sql",
                "src/main/resources/org/example/lab_7/task7_AddInfoAboutMe.sql",
                "src/main/resources/org/example/lab_7/task7_AddMyBooks.sql"
        };

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            for (String filePath : sqlFiles) {
                executeSqlFile(connection, filePath);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    private static void executeSqlFile(Connection connection, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sql.append(line).append("\n");
            }
            try (Statement statement = connection.createStatement()) {
                // Определяем, является ли SQL запросом типа SELECT
                if (sql.toString().trim().toLowerCase().startsWith("select")) {
                    try (ResultSet resultSet = statement.executeQuery(sql.toString())) {
                        int columnCount = resultSet.getMetaData().getColumnCount();
                        System.out.println();

                        while (resultSet.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                System.out.print(resultSet.getString(i) + "\t");
                            }
                            System.out.println();
                        }
                    }
                } else {
                    statement.execute(sql.toString());
                    System.out.println("Файл " + filePath + " успешно выполнен.");
                }
            } catch (SQLException e) {
                System.err.println("Ошибка выполнения SQL из файла " + filePath + ": " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + filePath + ": " + e.getMessage());
        }
    }

}
package org.example.lab_7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class HelloApplication {
    private static final String URL = "jdbc:mysql://localhost:3306/study";
    private static final String USER = "root";
    private static final String PASSWORD = "Admin1987";

    public static void main(String[] args) {
        String[] sqlFiles = {
                "src/main/resources/org/example/lab_7/music-insert.sql",
                "src/main/resources/org/example/lab_7/task1_GetMusicCompositions.sql",
                "src/main/resources/org/example/lab_7/task2_GetCompositionsWithoutMT.sql",
                "src/main/resources/org/example/lab_7/task3_InsertMyComposition.sql",
                "src/main/resources/org/example/lab_7/task5_GetSortedBooks.sql",
                "src/main/resources/org/example/lab_7/task6_GetYoungBooks.sql",
                "src/main/resources/org/example/lab_7/task7_AddInfoAboutMe.sql",
                "src/main/resources/org/example/lab_7/task7_AddMyBooks.sql"
        };

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            executeSqlFile(connection, "src/main/resources/org/example/lab_7/dropTableMusic.sql");
            executeSqlFile(connection, "src/main/resources/org/example/lab_7/task8_DropBook.sql");
            executeSqlFile(connection, "src/main/resources/org/example/lab_7/task8_DropClient.sql");
            executeSqlFile(connection, "src/main/resources/org/example/lab_7/music-create.sql");
            executeSqlFile(connection, "src/main/resources/org/example/lab_7/task4_CreateClient.sql");
            executeSqlFile(connection, "src/main/resources/org/example/lab_7/task4_CreateBook.sql");

            importJsonToDatabase(connection, "src/main/resources/org/example/lab_7/books.json");

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

    private static void importJsonToDatabase(Connection connection, String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(jsonFilePath));

            for (JsonNode clientNode : root) {
                String insertClientSQL = "INSERT INTO client (name, surname, phone, subscribed) VALUES (?, ?, ?, ?)";
                try (PreparedStatement clientStmt = connection.prepareStatement(insertClientSQL, Statement.RETURN_GENERATED_KEYS)) {
                    clientStmt.setString(1, clientNode.get("name").asText());
                    clientStmt.setString(2, clientNode.get("surname").asText());
                    clientStmt.setString(3, clientNode.get("phone").asText());
                    clientStmt.setBoolean(4, clientNode.get("subscribed").asBoolean());
                    clientStmt.executeUpdate();

                    ResultSet generatedKeys = clientStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int clientId = generatedKeys.getInt(1);

                        String insertBookSQL = "INSERT INTO book (name, author, publishingYear, isbn, publisher) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement bookStmt = connection.prepareStatement(insertBookSQL)) {
                            for (JsonNode bookNode : clientNode.get("favoriteBooks")) {
                                bookStmt.setString(1, bookNode.get("name").asText());
                                bookStmt.setString(2, bookNode.get("author").asText());
                                bookStmt.setInt(3, bookNode.get("publishingYear").asInt());
                                bookStmt.setString(4, bookNode.get("isbn").asText());
                                bookStmt.setString(5, bookNode.get("publisher").asText());
                                bookStmt.addBatch();
                            }
                            bookStmt.executeBatch();
                        }
                    }
                }
            }
            System.out.println("Данные из JSON успешно добавлены в базу данных.");
        } catch (IOException | SQLException e) {
            System.err.println("Ошибка при обработке JSON файла: " + e.getMessage());
        }
    }
}

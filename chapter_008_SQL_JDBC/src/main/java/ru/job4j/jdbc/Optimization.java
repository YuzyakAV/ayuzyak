package ru.job4j.jdbc;
 
 import java.sql.*;
 
 public class Optimization {
     private int maxCount;
     private String url;
     private String userName;
     private String password;
 
     public String getUrl() {
         return url;
     }
 
     public void setUrl(String serverUrl) {
         this.url = "jdbc:postgresql://" + serverUrl;
     }
 
     public String getUserName() {
         return userName;
     }
 
     public void setUserName(String userName) {
         this.userName = userName;
     }
 
     public String getPassword() {
         return password;
     }
 
     public void setPassword(String password) {
         this.password = password;
     }
 
     public int getMaxCount() {
         return maxCount;
     }
 
     public void setMaxCount(int maxCount) {
         this.maxCount = maxCount;
     }
 
     public Connection getConnection() throws ClassNotFoundException, SQLException {
         return DriverManager.getConnection(url, userName, password);
     }
 
     public void insertRows(Connection connection) {
         try {
             Statement st = connection.createStatement();
             st.executeUpdate("DROP TABLE IF EXISTS TEST");
             st.executeUpdate("CREATE TABLE TEST (FIELD INT)");
             st.execute("CREATE OR REPLACE FUNCTION insertCount(n INTEGER) RETURNS VOID AS $$\n" +
                     "DECLARE c INTEGER := 1;\n" +
                     "BEGIN\n" +
                     "  LOOP\n" +
                     "    IF c > n THEN\n" +
                     "      EXIT;\n" +
                     "    END IF;\n" +
                     "    INSERT INTO test (field) VALUES (c);\n" +
                     "    c := c + 1;\n" +
                     "  END LOOP;\n" +
                     "END;\n" +
                     "$$ LANGUAGE plpgsql;");
             PreparedStatement statement = connection.prepareStatement("SELECT insertcount(?)");
             statement.setInt(1, maxCount);
             statement.execute();
         } catch (SQLException e) {
             for (Throwable t : e) {
                 e.printStackTrace();
             }
         }
     }
 
     public static void main(String[] args) throws ClassNotFoundException {
         String myURL = "localhost:5432/sqlite";
         String myUser = "postgres";
         String myPassword = "shelby";
         int count = 1_000_000;
         Optimization opti = new Optimization();
         opti.setUrl(myURL);
         opti.setUserName(myUser);
         opti.setPassword(myPassword);
         opti.setMaxCount(count);
         try (Connection con = opti.getConnection()){
             opti.insertRows(con);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             for (Throwable t : e) {
                 t.printStackTrace();
             }
        }
     }
 }
package DataBaseTesting_ETL;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class DatabasePostgresqlCrudTest {
    private Connection connection;

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return new Object[][]{
                {"1", "John Doe", "JOHN.DOE@gmail.com", "540-256-6275", "QA Auto"},
                {"2", "Lindsay Olzawski", "Lindsay.Olzawski@gmail.com", "703-473-5463", "QA Auto"},
                {"3", "Ekuye Embiale", "ekuyesweet5@gmail.com", "202-280-3556", "QA AUTO"},
                {"4", "Driss Chima", "Driss.Chima@gmail.com", "(571)-357-8399", "QA AUTO"},
                {"5", "Claudia Ramirez", "ClaudiaRamirez232110@gmail.com", "703-899-3802", "QA AUTO"},
                {"6", "Afzal Baig", "AfzalBaig04@gmail.com", "7572220089", "Q"},
                {"7", "Harsh trivedi", "harsh@collaboraitinc.com", "7572220089", "QA Automation"},
        };
    }

    @Test(dataProvider = "testData",priority = 2)
    public void readRecordTest(String id, String name, String email, String phone, String type) {
        try {
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM classinfo";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Assert
            boolean isRecordFound = false;
            while (resultSet.next()) {
                String actualId = resultSet.getString("id");
                String actualEmail = resultSet.getString("email");
                String actualPhone = resultSet.getString("phone");
                String actualType = resultSet.getString("type");

                if (actualId.equals(id) && actualEmail.equals(email) && actualPhone.equals(phone) && actualType.equals(type)) {
                    isRecordFound = true;
                    break;
                }
            }
            Assert.assertTrue(isRecordFound, "Read Operation Failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "testData",priority = 0)
    public void createRecordTest(String id, String name, String email, String phone, String type) {
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO classinfo (id, name, email, phone, type) " +
                    "VALUES ('" + id + "', '" + name + "', '" + email + "', '" + phone + "', '" + type + "') " +
                    "ON CONFLICT (id) DO NOTHING";
            statement.executeUpdate(insertQuery);

            // Assert
            String selectQuery = "SELECT * FROM classinfo WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            Assert.assertEquals(rowCount, 1, "Record Creation Failed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "testData",priority = 3)
    public void deleteRecordTest(String id, String name, String email, String phone, String type) {
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM classinfo WHERE name = '" + name + "'";
            statement.executeUpdate(deleteQuery);

            // Assert the record was deleted successfully
            String selectQuery = "SELECT * FROM classinfo WHERE name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            Assert.assertEquals(rowCount, 0, "Record deletion failed for name: " + name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 1)
    public void updateRecordTest() {
        try {
            // Perform the update operation using SQL UPDATE statement
            Statement statement = connection.createStatement();
            String updateQuery = "UPDATE classinfo SET column2 = 'newvalue' WHERE column1 = 'value1'";
            statement.executeUpdate(updateQuery);

            // Assert the record was updated successfully
            String selectQuery = "SELECT * FROM classinfo WHERE column1 = 'value1' AND column2 = 'newvalue'";
            ResultSet resultSet = statement.executeQuery(selectQuery);
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            assert rowCount == 1 : "Record update failed";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @BeforeClass
    public void setUp() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "1290";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
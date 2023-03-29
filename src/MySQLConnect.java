import java.sql.*;

public class MySQLConnect {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase()  {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Set up the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost/qldiem?user=root&password=@Ge@8f3R");

            System.out.println("Connect successfully");


        } catch (Exception e) {
            System.out.println("Connect failed");
            e.printStackTrace();
        } finally {
            close(resultSet);
        }
    }

    public ResultSet queryStudent(String studentId)  {
        readDataBase();
        // Statements allow to issue SQL queries to the database
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from sinhVien where mssv='" + studentId + "'");
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Result set get the result of the SQL query

    }

    public void writeResultSet(ResultSet resultSet) {
        // ResultSet is initially before the first data set
            try {
                String id = resultSet.getString("mssv");
                String name = resultSet.getString("hoten");
                String dob = resultSet.getString("ngaySinh");
                String address = resultSet.getString("diachi");

                name = String.format("%-20s", name);
                System.out.println(id + "\t" + name + "\t" + dob + "\t\t" + address);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    public String getResultSetString(ResultSet resultSet) {
        try {
            String id = resultSet.getString("mssv");
            String name = resultSet.getString("hoten");
            String dob = resultSet.getString("ngaySinh");
            String address = resultSet.getString("diachi");

            name = String.format("%-20s", name);
            return (id + "\t" + name + "\t" + dob + "\t\t" + address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void close(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



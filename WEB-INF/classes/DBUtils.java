import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils{
    public static Connection establishConnection() throws Exception{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/userapp","postgres","*******");
    }
}

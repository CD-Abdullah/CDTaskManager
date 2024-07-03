import java.sql.SQLException;

@FunctionalInterface
public interface Signin {
    int signin(String username, String password) throws SQLException;
}

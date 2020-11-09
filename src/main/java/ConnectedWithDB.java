import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "connection")
@ApplicationScoped
public class ConnectedWithDB {
    @Resource(name = "java:/comp/env/jdbc/postgres")
    private DataSource dataSource;
    private Connection connection;

@PostConstruct
    public void init() throws NamingException, SQLException {
        connectionDB();
    }

    private void connectionDB() throws NamingException, SQLException {


        try {
            connection = dataSource.getConnection();
            System.out.println("Connection completed.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connection.createStatement().execute(
                "create table if not exists results (" +
                        "x float , y float, r float, result boolean)");


    }

    public void add(Point point) throws SQLException, NamingException {
        if (connection == null)
            connectionDB();

        PreparedStatement s = connection.prepareStatement(
                "insert into results (x, y, r, result) values (?, ?, ?, ?)"
        );
        s.setDouble(1, point.getX());
        s.setDouble(2, point.getY());
        s.setDouble(3, point.getR());
        s.setBoolean(4, point.getResult());
        s.execute();
    }

    public List<Point> getPointList() throws SQLException, NamingException {
        if (connection == null)
            connectionDB();

        ResultSet rs = connection.createStatement().executeQuery("select * from results");
        List<Point> result = new ArrayList<>();
        while (rs.next()) {
            Point point = new Point();
            point.setX(rs.getFloat("x"));
            point.setY(rs.getFloat("y"));
            point.setR(rs.getFloat("r"));
            point.setResult(rs.getBoolean("result"));
            result.add(point);
        }
        return result;
    }

}

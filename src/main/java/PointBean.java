import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PointBean {

    private ConnectedWithDB connectedWithDB = new ConnectedWithDB();
    private Point point;

    public PointBean() {
        this.point = new Point();

    }

    public void addPoint() throws NamingException, SQLException {
        point.check();
        connectedWithDB.add(point);
        point = new Point();
    }

    public Point getPoint() {
        return point;
    }

    public List<Point> getPointList() throws SQLException, NamingException {
        return connectedWithDB.getPointList();
    }
}

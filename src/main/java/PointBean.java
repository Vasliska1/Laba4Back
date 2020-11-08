import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "pointBean")
@ApplicationScoped
public class PointBean {

    @ManagedProperty(value = "#{connection}")
    private ConnectedWithDB connectedWithDB;
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

    public void setConnectedWithDB(ConnectedWithDB connectedWithDB) {
        this.connectedWithDB = connectedWithDB;
    }
}

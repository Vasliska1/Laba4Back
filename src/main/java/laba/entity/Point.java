package laba.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "result")
public class Point extends BaseEntity {

    @NotNull
    @Column(name = "x")
    private float x;

    @NotNull
    @Column(name = "y")
    private float y;

    @NotNull
    @Column(name = "r")
    private float r;

    @Column(name = "result")
    private Boolean result;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getResult() {
        return result;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public Boolean check() {
        if ((x * x + y * y <= r / 2 * r / 2 && x >= 0 && y <= 0) ||
                (y <= -2*x + r  && x >= 0 && y >= 0) ||
                (y <= 0  && x <= 0 && x >= -r && y >= -r/2)) {
            return true;
        } else {
            return false;
        }
    }


}

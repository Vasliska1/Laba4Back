public class Point {

    private double x;
    private double y;
    private double r;
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean res) {
        this.result = res;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }


    public void check() {
        if ((x * x + y * y <= r / 2 * r / 2 && x <= 0 && y >= 0) ||
                (y >= x - r / 2 && x >= 0 && y <= 0) ||
                (y <= r / 2 && x >= 0 && x <= r && y >= 0)) {
            result = true;
        } else {
            result = false;
        }
    }


}

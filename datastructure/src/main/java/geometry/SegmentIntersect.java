package geometry;

/**
 * 判断两条线段是否相交
 * 先判断一条线段的两点是否在另一条线段的两侧（使用四次叉积判断夹角是正还是负），
 * 否则，如果有一个端点在另一条线段所在直线上，判断这个点是否在另一条线段两个端点之间
 */
public class SegmentIntersect {

    private Point p1, p2, p3, p4;

    public SegmentIntersect(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public boolean judgeIntersect() {
        double d1 = direction(p3, p4, p1);
        double d2 = direction(p3, p4, p2);
        double d3 = direction(p1, p2, p3);
        double d4 = direction(p1, p2, p4);
        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0))
                && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0)))
            return true;
        else if (d1 == 0 && onSegment(p3, p4, p1))
            return true;
        else if (d2 == 0 && onSegment(p3, p4, p2))
            return true;
        else if (d3 == 0 && onSegment(p1, p2, p3))
            return true;
        else if (d4 == 0 && onSegment(p1, p2, p4))
            return true;
        else
            return false;
    }

    // 大於零表示順時針，即右轉，小於零表示逆時針，即左轉，等於零，表示共綫。
    public double direction(Point pi, Point pj, Point pk){
        return det(PiPj(pi, pj), PiPj(pi, pk));
    }

    public Point PiPj(Point pi, Point pj) { // 構造向量
        Point p = new Point();
        p.setX(pj.getX() - pi.getX());
        p.setY(pj.getY() - pi.getY());
        return p;
    }

    public double det(Point pi, Point pj) { // 叉积,为正时，pi位于pj的顺时针方向；为负，逆时针
        return pi.getX() * pj.getY() - pj.getX() * pi.getY();
    }

    public boolean onSegment(Point pi, Point pj, Point pk) {
        double max_x = (pi.getX() - pj.getX()) > 0 ? pi.getX() : pj.getX();
        double min_x = (pi.getX() - pj.getX()) < 0 ? pi.getX() : pj.getX();
        double max_y = (pi.getY() - pj.getY()) > 0 ? pi.getY() : pj.getY();
        double min_y = (pi.getY() - pj.getY()) < 0 ? pi.getY() : pj.getY();
        if ((min_x <= pk.getX()) && (pk.getX() <= max_x)
                && (min_y <= pk.getY()) && (pk.getY() <= max_y))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 4);
        Point p2 = new Point(3, 0);
        Point p3 = new Point(0, 1);
        Point p4 = new Point(4, 3);
        if(new SegmentIntersect(p1, p2, p3, p4).judgeIntersect()){
            System.out.println("相交綫段");
        }else{
            System.out.println("非相交綫段");
        };

    }
}

class Point{
    private double x;
    private double y;

    public Point(){
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}





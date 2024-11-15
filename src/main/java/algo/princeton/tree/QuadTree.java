package algo.princeton.tree;

import java.util.ArrayList;
import java.util.List;

record Point(float x, float y) {
}

record Rectangle(float x, float y, float w, float h) {

}

public class QuadTree {

    private final Rectangle boundary;
    private final List<Point> points;
    private QuadTree northEast;
    private QuadTree northWest;
    private QuadTree southEast;
    private QuadTree southWest;
    private boolean subdivided;
    private final int capacity;

    public QuadTree(Rectangle boundary, int capacity) {
        this.boundary = boundary;
        this.capacity = capacity;
        this.points = new ArrayList<>();
        this.northEast = null;
        this.northWest = null;
        this.southEast = null;
        this.southWest = null;
    }

    private void subdivide() {
        var x = this.boundary.x();
        var y = this.boundary.y();
        var h = this.boundary.h();
        var w = this.boundary.w();
        Rectangle ne = new Rectangle(x + w / 2, y + h / 2, w / 2, h / 2);
        this.northEast = new QuadTree(ne, capacity);

        Rectangle nw = new Rectangle(x - w / 2, y + h / 2, w / 2, h / 2);
        this.northWest = new QuadTree(nw, capacity);

        Rectangle se = new Rectangle(x + w / 2, y - h / 2, w / 2, h / 2);
        this.southEast = new QuadTree(se, capacity);

        Rectangle sw = new Rectangle(x - w / 2, y - h / 2, w / 2, h / 2);
        this.southWest = new QuadTree(sw, capacity);

        this.subdivided = true;
    }

    private boolean contains(Rectangle range, Point point) {
        return point.x() >= range.x() - range.w() &&
                point.x() <= range.x() + range.w() &&
                point.y() >= range.y() - range.h() &&
                point.y() <= range.y() + range.h();
    }

    private boolean intersects(Rectangle range) {
        return !(range.x() - range.w() > this.boundary.x() + this.boundary.w() ||
                range.x() + range.w() < this.boundary.x() - this.boundary.w() ||
                range.y() - range.h() > this.boundary.y() + this.boundary.h() ||
                range.y() + range.h() < this.boundary.y() - this.boundary.h()
        );
    }

    public List<Point> query(Rectangle range) {

        List<Point> foundPoints = new ArrayList<>();

        if (!intersects(range)) {
            return foundPoints;
        }

        for(Point point : points) {
            if(contains(range, point)) {
                foundPoints.add(point);
            }
        }

        if (this.subdivided) {
            foundPoints.addAll(this.northEast.query(range));
            foundPoints.addAll(this.northWest.query(range));
            foundPoints.addAll(this.southEast.query(range));
            foundPoints.addAll(this.southWest.query(range));
        }
        return foundPoints;
    }

    public boolean insert(Point point) {
        if (!contains(this.boundary, point)) {
            return false;
        }
        if (this.points.size() < capacity) {
            this.points.add(point);
            return true;
        } else {
            if (!subdivided) {
                subdivide();
            }
            return this.northEast.insert(point) ||
                    this.northWest.insert(point) ||
                    this.southEast.insert(point) ||
                    this.southWest.insert(point);
        }
    }

    public static void main(String[] args) {
        Rectangle rect = new Rectangle(200, 200, 200, 200);
        QuadTree quadTree = new QuadTree(rect, 4);
        quadTree.insert(new Point(10, 10));
        quadTree.insert(new Point(20, 20));
        quadTree.insert(new Point(30, 30));
        quadTree.insert(new Point(40, 40));
        quadTree.insert(new Point(50, 50));
        quadTree.insert(new Point(0, 0));
        System.out.println(quadTree.query(rect));
    }
}


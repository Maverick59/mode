import java.io.Serializable;

public class Segment implements Serializable {

	Point p1, p2;

	public Segment(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

}

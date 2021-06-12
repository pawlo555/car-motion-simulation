import javax.swing.JFrame;
import java.util.ArrayList;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;

	public Program() {
		setTitle("Sound simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(1024, 768);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Crossing example = exampleJunction.createCrossing();
		System.out.println("-------------------------");
		System.out.println("-------------------------");
		System.out.println("-------------------------");
		for(int j = 0; j < example.waysThroughIntersection.size(); j+=1){
			ArrayList<Cell> list = example.waysThroughIntersection.get(j);
			System.out.println("Way through: " + j + " \nWay size: " + list.size());
			for(Cell i:list){
				System.out.println("[" + i.x + "," + i.y + "]");
			}
			System.out.println("-------------------------");
		}




	}
}

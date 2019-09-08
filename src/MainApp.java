import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sort");


        frame.setTitle("Sorting Visualiser");
        frame.add(new SortVisualizer());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

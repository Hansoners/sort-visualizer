import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

public class SortVisualizer extends JPanel {

    private static final int ITEM_NUM = 20;
    private static final int PREF_W = 500;
    private static final int PREF_H = 500;
    private static final int HOR = PREF_W / ITEM_NUM;
    private static final int VERT = 15;
    private static final int HORIZON = 500;

    private int curIndex = ITEM_NUM - 1;

    private Timer timer = null;

    private Integer[] num_list;

    public SortVisualizer() {
        num_list = initList();

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (curIndex == 0) {
                    ((Timer) e.getSource()).stop();
                } else {
                    sortOne();
                }
                repaint();
            }
        });

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num_list = initList();
                curIndex = ITEM_NUM - 1;
                repaint();
            }
        });

        add(startBtn);
        add(resetBtn);
    }

    private void sortOne() {
        int currentMax = num_list[0];
        int currentMaxIndex = 0;

        for (int j = 1; j <= curIndex; j++) {
            if (currentMax < num_list[j]) {
                currentMax = num_list[j];
                currentMaxIndex = j;
            }
        }

        if (currentMaxIndex != curIndex) {
            num_list[currentMaxIndex] = num_list[curIndex];
            num_list[curIndex] = currentMax;
        }
        curIndex--;
    }

    private Integer[] initList() {
        Integer[] nums = new Integer[ITEM_NUM];
        for (int i = 1; i <= nums.length; i++) {
            nums[i - 1] = i;
        }
        Collections.shuffle(Arrays.asList(nums));
        return nums;
    }

    private void draw(Graphics g, int val, int index) {
        int height = val * VERT;
        int y = HORIZON - height;
        int x = index * HOR;
        g.fillRect(x, y, HOR, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < num_list.length; i++) {
            draw(g, num_list[i], i);
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

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

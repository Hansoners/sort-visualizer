import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class SortVisualizer extends JPanel {

    private static final int ITEM_NUM = 25;
    private static final int PREF_W = 500;
    private static final int PREF_H = 500;
    private static final int HOR = PREF_W / ITEM_NUM;
    private static final int VERT = 15;
    private static final int HORIZON = 500;

    private int curIndex = 0;

    private Timer timer = null;

    private Integer[] num_list;
    private int num_list_size;

    public SortVisualizer() {
        num_list = initList();
        num_list_size = num_list.length;

        String sorts[] = {"Bubble Sort", "Selection Sort", "Insertion Sort"};
        JComboBox c1 = new JComboBox(sorts);

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = c1.getSelectedItem().toString();
                switch (value) {
                    case "Bubble Sort":
                        if (bubbleSortDone()) ((Timer) e.getSource()).stop();
                        else bubbleSortOnce();
                        break;
                    case "Insertion Sort":
                        if (insertionSortDone()) ((Timer) e.getSource()).stop();
                        else insertionSortOnce();
                        break;
                    case "Selection Sort":
                        if (selectionSortDone()) ((Timer) e.getSource()).stop();
                        else selectionSortOnce();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + value);
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
                curIndex = 0;
                repaint();
            }
        });

        add(c1);
        add(startBtn);
        add(resetBtn);
    }

    private boolean selectionSortDone() {
        return (curIndex == num_list_size);
    }

    private void selectionSortOnce() {
        int min_idx = curIndex;
        for (int j = curIndex + 1; j < num_list_size; j++)
            if (num_list[j] < num_list[min_idx])
                min_idx = j;

        int temp = num_list[min_idx];
        num_list[min_idx] = num_list[curIndex];
        num_list[curIndex] = temp;
        curIndex++;
    }


    private boolean insertionSortDone() {
        return (curIndex == num_list_size);
    }

    private void insertionSortOnce() {
        System.out.println("Insertion Sort");
        int key = num_list[curIndex];
        int j = curIndex - 1;
        while (j >= 0 && num_list[j] > key) {
            num_list[j + 1] = num_list[j];
            j = j - 1;
        }
        num_list[j + 1] = key;
        curIndex++;
    }

    private boolean bubbleSortDone() {
        return (curIndex == num_list_size - 1);
    }

    private void bubbleSortOnce() {
        for (int j = 0; j < num_list_size - curIndex - 1; j++)
            if (num_list[j] > num_list[j + 1]) {
                int temp = num_list[j];
                num_list[j] = num_list[j + 1];
                num_list[j + 1] = temp;
            }
        curIndex++;
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
        g.setColor(Color.BLACK);
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
}

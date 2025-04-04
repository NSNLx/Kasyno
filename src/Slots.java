import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Slots extends Frame {
    private Menu menu;

    Slots(Menu menu) {
        this.menu = menu;
        setSize(400, 280);
        setLayout(new BorderLayout());

        Random randomNumbers = new Random();
        JLabel label = new JLabel("3 random numbers", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JTextField stake = new JTextField();
        stake.setText("5");
        JButton submit = new JButton("Submit");

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        inputPanel.add(stake);
        inputPanel.add(submit);

        JPanel slotsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        slotsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        JLabel a = new JLabel("", SwingConstants.CENTER);
        JLabel b = new JLabel("", SwingConstants.CENTER);
        JLabel c = new JLabel("", SwingConstants.CENTER);
        a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        slotsPanel.add(a);
        slotsPanel.add(b);
        slotsPanel.add(c);

        add(label, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(slotsPanel, BorderLayout.SOUTH);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int stakeValue = Integer.parseInt(stake.getText());
                    if (menu.getCurrency() < stakeValue || stakeValue <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid stake", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Timer timer = new Timer(100, null);
                    final int[] counter = {0};
                    timer.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (counter[0] < 20) {
                                int aa = randomNumbers.nextInt(7) + 1;
                                int bb = randomNumbers.nextInt(7) + 1;
                                int cc = randomNumbers.nextInt(7) + 1;
                                a.setText(Integer.toString(aa));
                                b.setText(Integer.toString(bb));
                                c.setText(Integer.toString(cc));
                                counter[0]++;
                            } else {
                                ((Timer) evt.getSource()).stop();

                                // Sprawdzenie warunków wygranej
                                if (a.getText().equals(b.getText()) && b.getText().equals(c.getText())) {
                                    int reward = stakeValue * 4;
                                    menu.addToCurrency(reward);
                                    menu.saveTokens();
                                    JOptionPane.showMessageDialog(null, "3 numbers - you won " + reward + " tokens", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                                } else if (a.getText().equals(b.getText()) || a.getText().equals(c.getText()) || b.getText().equals(c.getText())) {
                                    int reward = stakeValue * 2;
                                    menu.addToCurrency(reward);
                                    menu.saveTokens();
                                    JOptionPane.showMessageDialog(null, "2 numbers - you won " + reward + " tokens", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    menu.addToCurrency(-stakeValue);
                                    menu.saveTokens();
                                    JOptionPane.showMessageDialog(null, "No matching numbers. You lost " + stakeValue + " tokens", "Sorry", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    });
                    timer.setInitialDelay(0);
                    timer.start();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid stake", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}

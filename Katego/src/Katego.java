import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
public class Katego extends JFrame {
    int die1;
    int die2;
    int total;
    private JPanel panel;
    private JTextField[] human = new JTextField[5];
    private JTextField[] robot = new JTextField[5];
    private final int WINDOW_LENGTH = 300;
    private final int WINDOW_WIDTH = 190;
    private JButton[] placeNum = new JButton[5];
    private JButton roll;
    private JLabel[] row = new JLabel[5];
    private JLabel rollWin;
    private int[] hasContent = new int[5];
    private int exclamation = 1;
    private int rollCount = 0;
    private int[] hasContentRobot = new int[5];
    private int[] humanNums = new int[5];
    private int[] robotNums = new int[5];
    int theOneThatBlocksYouFromUsingRoboRolls = 1;
    Random rand = new Random();

    public void RollDie() {
        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        total = die1+die2;
    }
    public String exclamationMark(int exclamation) {
        String Mark = "";
        for (int i = 0; i < exclamation; i++) {
             Mark += "!";
        }
        return Mark;
    }
    public void scoring(int[] human, int[] robot) {
        int humanTotal = 0;
        int robotTotal = 0;
        for (int i = 0; i < human.length; i++) {
            if(human[i] > robot[i]) {
                humanTotal += i+1;
            } else if(human[i] < robot[i]) {
                robotTotal += i+1;
            }
        }
        String winner;
        if(humanTotal > robotTotal) {
            winner = "Win";
        } else {
            winner = "Lose";
        }
        rollWin.setText("You " + winner + ": " + humanTotal + "-" + robotTotal);
    }
    public Katego() {
        setTitle("Katego");

        setSize(WINDOW_WIDTH, WINDOW_LENGTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();

        add(panel);

        setVisible(true);
    }
    private void buildPanel() {
        panel = new JPanel();
        for (int i = 0; i < human.length; i++) {
            row[i] = new JLabel(i+1 + "");
            human[i] = new JTextField(3);
            robot[i] = new JTextField(3);
            placeNum[i] = new JButton("Enter");
            hasContent[i] = 0;
            hasContentRobot[i] = 0;
            humanNums[i] = 0;
            robotNums[i] = 0;
            panel.add(row[i]);
            panel.add(human[i]);
            panel.add(robot[i]);
            panel.add(placeNum[i]);
        }
        roll = new JButton("Roll");
        rollWin = new JLabel("It's your turn" + exclamationMark(exclamation));
        panel.add(rollWin);
        panel.add(roll);
        placeNum[0].addActionListener(new Enter1ButtonListener());
        placeNum[1].addActionListener(new Enter2ButtonListener());
        placeNum[2].addActionListener(new Enter3ButtonListener());
        placeNum[3].addActionListener(new Enter4ButtonListener());
        placeNum[4].addActionListener(new Enter5ButtonListener());
        roll.addActionListener(new RollButtonListener());
    }
    private class Enter1ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(hasContent[0] == 0 && theOneThatBlocksYouFromUsingRoboRolls == 0) {
                human[0].setText(String.valueOf(total));
                humanNums[0] = total;
                hasContent[0] = 1;
                RollDie();
                int roboPlace = rand.nextInt(5);
                while(hasContentRobot[roboPlace] != 0) {
                    roboPlace = rand.nextInt(5);
                }
                hasContentRobot[roboPlace] = 1;
                robot[roboPlace].setText(String.valueOf(total));
                robotNums[roboPlace] = total;
                rollCount = 0;
                exclamation = 0;
                theOneThatBlocksYouFromUsingRoboRolls = 1;
            } else {
                exclamation += 1;
                rollWin.setText(die1 + " + " + die2 + " = " + total + exclamationMark(exclamation));
            }
            if(hasContent[0] != 0 && hasContent[1] != 0 && hasContent[2] != 0 && hasContent[3] != 0 && hasContent[4] != 0) {
                scoring(humanNums, robotNums);
            }
        }
    }
    private class Enter2ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(hasContent[1] == 0 && theOneThatBlocksYouFromUsingRoboRolls == 0) {
                human[1].setText(String.valueOf(total));
                humanNums[1] = total;
                hasContent[1] = 1;
                RollDie();
                int roboPlace = rand.nextInt(5);
                while(hasContentRobot[roboPlace] != 0) {
                    roboPlace = rand.nextInt(5);
                }
                hasContentRobot[roboPlace] = 1;
                robot[roboPlace].setText(String.valueOf(total));
                robotNums[roboPlace] = total;
                rollCount = 0;
                exclamation = 0;
                theOneThatBlocksYouFromUsingRoboRolls = 1;
            } else {
                exclamation += 1;
                rollWin.setText(die1 + " + " + die2 + " = " + total + exclamationMark(exclamation));
            }
            if(hasContent[0] != 0 && hasContent[1] != 0 && hasContent[2] != 0 && hasContent[3] != 0 && hasContent[4] != 0) {
                scoring(humanNums, robotNums);
            }
        }
    }
    private class Enter3ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (hasContent[2] == 0 && theOneThatBlocksYouFromUsingRoboRolls == 0) {
                human[2].setText(String.valueOf(total));
                humanNums[2] = total;
                hasContent[2] = 1;
                RollDie();
                int roboPlace = rand.nextInt(5);
                while(hasContentRobot[roboPlace] != 0) {
                    roboPlace = rand.nextInt(5);
                }
                hasContentRobot[roboPlace] = 1;
                robot[roboPlace].setText(String.valueOf(total));
                robotNums[roboPlace] = total;
                rollCount = 0;
                exclamation = 0;
                theOneThatBlocksYouFromUsingRoboRolls = 1;
            } else {
                exclamation += 1;
                rollWin.setText(die1 + " + " + die2 + " = " + total + exclamationMark(exclamation));
            }
            if(hasContent[0] != 0 && hasContent[1] != 0 && hasContent[2] != 0 && hasContent[3] != 0 && hasContent[4] != 0) {
                scoring(humanNums, robotNums);
            }
        }
    }
    private class Enter4ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (hasContent[3] == 0 && theOneThatBlocksYouFromUsingRoboRolls == 0) {
                human[3].setText(String.valueOf(total));
                humanNums[3] = total;
                hasContent[3] = 1;
                RollDie();
                int roboPlace = rand.nextInt(5);
                while(hasContentRobot[roboPlace] != 0) {
                    roboPlace = rand.nextInt(5);
                }
                hasContentRobot[roboPlace] = 1;
                robot[roboPlace].setText(String.valueOf(total));
                robotNums[roboPlace] = total;
                rollCount = 0;
                exclamation = 0;
                theOneThatBlocksYouFromUsingRoboRolls = 1;
            } else {
                exclamation += 1;
                rollWin.setText(die1 + " + " + die2 + " = " + total + exclamationMark(exclamation));
            }
            if(hasContent[0] != 0 && hasContent[1] != 0 && hasContent[2] != 0 && hasContent[3] != 0 && hasContent[4] != 0) {
                scoring(humanNums, robotNums);
            }
        }
    }
    private class Enter5ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (hasContent[4] == 0 && theOneThatBlocksYouFromUsingRoboRolls == 0) {
                human[4].setText(String.valueOf(total));
                humanNums[4] = total;
                hasContent[4] = 1;
                RollDie();
                int roboPlace = rand.nextInt(5);
                while(hasContentRobot[roboPlace] != 0) {
                    roboPlace = rand.nextInt(5);
                }
                hasContentRobot[roboPlace] = 1;
                robot[roboPlace].setText(String.valueOf(total));
                robotNums[roboPlace] = total;
                rollCount = 0;
                exclamation = 0;
                theOneThatBlocksYouFromUsingRoboRolls = 1;
            } else {
                exclamation += 1;
                rollWin.setText(die1 + " + " + die2 + " = " + total + exclamationMark(exclamation));
            }
            if(hasContent[0] != 0 && hasContent[1] != 0 && hasContent[2] != 0 && hasContent[3] != 0 && hasContent[4] != 0) {
                scoring(humanNums, robotNums);
            }
        }
    }
    private class RollButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(rollCount == 0) {
                RollDie();
                rollCount = 1;
                exclamation = 0;
                theOneThatBlocksYouFromUsingRoboRolls = 0;
            } else {
                exclamation += 1;
            }
            rollWin.setText(die1 + " + " + die2 + " = " + total + exclamationMark(exclamation));
        }
    }
}
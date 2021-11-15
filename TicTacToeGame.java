
/**
   *Tiana Noll-Walker
   Homework7:TicTacToe
   11/14/21
   Minimal
   */

import java.awt.Color;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class TicTacToeGame extends JPanel {

    WsuWindow win;
    WsuRectangle r;
    WsuEllipse ov;
    int JButtonIdx = 0;
    JButton[][] buttons = new JButton[3][3];
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    final String PLAYER_X = "X";
    final String PLAYER_O = "O";
    String PlayerID = PLAYER_X;

    public TicTacToeGame() {

        win = new WsuWindow(10, 10, 235, 280);
        win.setTitle("Tic-Tac-Toe");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.CreateButtons();
        this.CreateLabel();
        this.PlayerID = PLAYER_X;

        win.setVisible(true);
        win.repaint();
        setLayout(new GridLayout(3, 3));
    }

    public void CreateButtons() {
        int size = 50;
        int start = 30;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int xPosition = (start + size * x);
                int yPosition = (start + size * y);
                buttons[x][y] = new JButton();
                buttons[x][y].setSize(size, size);
                buttons[x][y].setLocation(xPosition, yPosition);
                win.add(buttons[x][y]);
                win.setVisible(true);
                buttons[x][y].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton buttonPressed = (JButton) e.getSource();
                        buttonPressed.setText(String.valueOf(PlayerID));

                        if (checkWin()) {
                            System.out.println(PlayerID + " you won!");
                            return;
                        }

                        // players x and o switching current player
                        if (PlayerID == PLAYER_X) {
                            PlayerID = PLAYER_O;
                            System.out.println("Its your turn, O.");

                        } else {
                            PlayerID = PLAYER_X;
                            System.out.println("Its your turn, X");

                        }

                    }
                });

            }
        }
        win.repaint();
    }

    public void CreateLabel() {
        for (int i = 0; i < 9; i++) {
            label.setSize(160, 20);
            label.setLocation(30, 200);
            label.setHorizontalAlignment(JLabel.CENTER);

            // panel.setLayout(new GridLayout(5,4,2,3));
            panel.add(buttons[3][3]);
            win.add(label);
            win.setVisible(true);
            win.repaint();
        }

    }

    private boolean checkWin() {
        return this.checkRows() || this.checkColumns() || this.checkDiags();

    }

    private boolean checkDiags() {
        /**
         * [0][0], [1][1], [2][2], = diagonal 1 [0][2], [2][0], [1][1] = diagonal 2
         * 
         * When should this return true if the current player controls one of the
         * diagonals then return true
         */

        if (this.PlayerID.equals(buttons[0][0].getText()) && this.PlayerID.equals(buttons[1][1].getText())
                && this.PlayerID.equals(buttons[2][2].getText())) {
            return true;
        } else if (this.PlayerID.equals(buttons[0][2].getText()) && this.PlayerID.equals(buttons[2][0].getText())
                && this.PlayerID.equals(buttons[1][1].getText())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkColumns() {
        for (int x = 0; x < 3; x++) {
            boolean isWin = true;
            for (int y = 0; y < 3; y++) {
                JButton currButton = buttons[y][x];
                String currText = currButton.getText();
                if (!currText.equals(PlayerID)) {
                    isWin = false;
                }
            }
            if (isWin) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRows() {
        /**
         * check to see if the rows are full from one player
         */
        for (int j = 0; j < 3; j++) {
            JButton[] row = buttons[j];
            boolean isWin = true;
            for (int r = 0; r < 3; r++) {
                JButton currButton = row[r];
                String currText = currButton.getText();
                if (!currText.equals(PlayerID)) {
                    isWin = false;
                }
            }
            if (isWin) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
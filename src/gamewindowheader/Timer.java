package gamewindowheader;

import java.awt.*;
import java.util.Date;

public class Timer {

    private final int TIME_X = 470;
    private final int TIME_Y = 3;

    Font timerFont = new Font("Tacoma", Font.BOLD, 50);
    Date date = new Date();

    public Timer() {}

    /**
     * Method created to draw the timer
     *
     * @param g Object of Graphics
     */
    public void drawTimer(Graphics g) {

        g.setColor(Color.ORANGE);
        g.fillRect(TIME_X, TIME_Y, 124, 60);

        g.setColor(Color.BLACK);
        g.setFont(timerFont);

        timerSecondsVisualiser(g);
    }

    /**
     * Method that visualises the seconds in the "windowheader.Timer Box"
     *
     * @param g Object of Graphics
     */
    private void timerSecondsVisualiser(Graphics g) {

        int secondsSpentInGame = (int) ((new Date().getTime() - date.getTime()) / 1000);

        if (secondsSpentInGame < 10) {

            g.drawString("00" + secondsSpentInGame, TIME_X + 20, TIME_Y + 50);

        } else if (secondsSpentInGame < 100) {

            g.drawString("0" + secondsSpentInGame, TIME_X + 20, TIME_Y + 50);

        } else {

            g.drawString(Integer.toString(secondsSpentInGame), TIME_X, TIME_Y + 50);
        }
    }
}
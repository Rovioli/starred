package me.riseremi.ui.windows;

import me.riseremi.main.Main;
import me.riseremi.ui.RButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static me.riseremi.main.Main.setUIFont;

/**
 *
 * @author riseremi <riseremi at icloud.com>
 */
public class LobbyScreen extends JPanel implements ActionListener {

    private static final JButton goButton = new RButton("Go!", false);
    private static JButton backButton = new RButton("Back", false);
    private DefaultListModel playersListModel = new DefaultListModel();
    private JList playersList = new JList(playersListModel);

    public LobbyScreen(boolean server) {
        int frameWidth = Main.getFrames()[0].getWidth();
        final JLabel jLabel = new JLabel("Connected players:");

        setUIFont(new javax.swing.plaf.FontUIResource(Main.MAIN_FONT));
        setLayout(null);

        goButton.setBounds(64, 64, frameWidth / 4, 32);
        backButton.setBounds(64, 28, frameWidth / 4, 32);
        jLabel.setBounds(64, 64 + 38, frameWidth / 4, 32);
        playersList.setBounds(64, 128, frameWidth / 4, 320);

        goButton.setEnabled(false);
        if (server) {
            add(goButton);
        }

        add(jLabel);
        add(playersList);
        add(backButton);
    }

    public static JButton getGoButton() {
        return LobbyScreen.goButton;
    }

    public static JButton getBackButton() {
        return backButton;
    }

    public void setCanGo() {
        goButton.setEnabled(true);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public DefaultListModel getPlayersListModel() {
        return this.playersListModel;
    }
}

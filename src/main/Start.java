package main;
import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("Ustawienia Gry w Życie");
        menuFrame.setSize(1200, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.add(new SettingsMenu());
        menuFrame.setVisible(true);
    }
}

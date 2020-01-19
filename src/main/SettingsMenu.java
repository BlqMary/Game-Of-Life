package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel{
    private final JLabel title;
    private final JLabel header;
    private final JLabel footer;
    private final JLabel sidePanel;
    private JTextField surviveCondition;
    private final JLabel surviveL;
    private JTextField reproduceCondition;
    private final JLabel reproduceL;
    private JTextField gridWidth;
    private final JLabel widthL;
    private JTextField gridHeight;
    private final JLabel heightL;
    private final JButton continueButton;
    private final JButton gosperButton;
    private final JButton dieHardButton;
    private final JButton trayClothButton;
    private final JButton labyrinthButton;
    private final JButton cityButton;
    private final JButton dakotaButton;
    private int gridWidthValue;
    private int gridHeightValue;
    private int []surviveConditionValues;
    private int []reproduceConditionValues;

    public SettingsMenu(){

        this.setLayout(new GridLayout(0,2));

        surviveCondition = new JTextField();
        surviveCondition.setColumns(7);

        reproduceCondition = new JTextField();
        reproduceCondition.setColumns(7);

        gridHeight = new JTextField();
        gridHeight.setColumns(7);

        gridWidth = new JTextField();
        gridWidth.setColumns(7);

        this.title = new JLabel("Gra w Życie");
        this.title.setFont(new Font(title.getFont().getName(), Font.BOLD,20));

        this.header = new JLabel("Wpisz parametry z jakimi chcesz zagrać. ");
        this.header.setFont(new Font(header.getFont().getName(), Font.BOLD,16));

        this.footer = new JLabel("Jeśli zostawisz puste pola lub błędnie je wypełnisz gra wystartuje z domyślnymi wartościami.");
        this.footer.setFont(new Font(header.getFont().getName(), Font.ITALIC,14));

        widthL = new JLabel("Szerekość mapy (np: 15):    ");
        widthL.setLabelFor(gridWidth);

        heightL = new JLabel("Wysokość mapy (np: 15):    ");
        heightL.setLabelFor(gridHeight);

        reproduceL = new JLabel("Warunek rozmnożenia (np: 3):    ");
        reproduceL.setLabelFor(reproduceCondition);

        surviveL = new JLabel("Warunek przetrwania (np: 23):    ");
        surviveL.setLabelFor(surviveCondition);

        continueButton = new JButton("Start");

        sidePanel = new JLabel("Predefiniowane tryby gry");
        sidePanel.setFont(new Font(header.getFont().getName(), Font.BOLD,16));

        gosperButton = new JButton("Gosper Glider Gun");
        dieHardButton = new JButton("Die Hard");
        trayClothButton = new JButton("Serwetki");
        labyrinthButton = new JButton("Labirynt");
        cityButton = new JButton("Miasta");
        dakotaButton = new JButton("Dokota Duża");

        initSettingsView();

        continueButton.addActionListener(evt -> {
            parseParameters();
            startSimulation();
        });

        trayClothButton.addActionListener(evt ->{
            gridWidthValue = 70;
            gridHeightValue = 70;
            reproduceConditionValues = new int[] {2,3,4};
            surviveConditionValues = new int[] {};
            startSimulation();
        });

        cityButton.addActionListener(evt ->{
            gridWidthValue = 40;
            gridHeightValue = 40;
            reproduceConditionValues = new int[] {4,5,6,7,8};
            surviveConditionValues = new int[] {2,3,4,5};
            startSimulation();
        });

        labyrinthButton.addActionListener(evt ->{
            gridWidthValue = 70;
            gridHeightValue = 70;
            reproduceConditionValues = new int[] {3};
            surviveConditionValues = new int[] {1,2,3,4,5};
            startSimulation();
        });

        gosperButton.addActionListener(evt ->{
            gridWidthValue = 100;
            gridHeightValue = 100;
            reproduceConditionValues = new int[] {3};
            surviveConditionValues = new int[] {2,3};
            startSimulationPattern("gosper");
        });

        dieHardButton.addActionListener(evt ->{
            gridWidthValue = 50;
            gridHeightValue = 50;
            reproduceConditionValues = new int[] {3};
            surviveConditionValues = new int[] {2,3};
            startSimulationPattern("diehard");
        });

        dakotaButton.addActionListener(evt ->{
            gridWidthValue = 50;
            gridHeightValue = 10;
            reproduceConditionValues = new int[] {3};
            surviveConditionValues = new int[] {2,3};
            startSimulationPattern("dakota");
        });
    }

    private void startSimulation(){
        Simulation simulation = new Simulation(reproduceConditionValues,surviveConditionValues, gridWidthValue, gridHeightValue);
        new Visualization(this,simulation,250);
        this.setVisible(false);
    }

    private void startSimulationPattern(String pattern){
        Simulation simulation = new Simulation(reproduceConditionValues,surviveConditionValues, gridWidthValue, gridHeightValue,pattern);
        new Visualization(this,simulation,250);
        this.setVisible(false);
    }

    private void initSettingsView(){
        addPanel(new JPanel(), new Component[]{title});
        addPanel(new JPanel(), new Component[]{sidePanel});
        addPanel(new JPanel(), new Component[]{header});
        addPanel(new JPanel(), new Component[]{gosperButton});
        addPanel(new JPanel(), new Component[]{widthL,gridWidth});
        addPanel(new JPanel(), new Component[]{trayClothButton});
        addPanel(new JPanel(), new Component[]{heightL,gridHeight});
        addPanel(new JPanel(), new Component[]{labyrinthButton});
        addPanel(new JPanel(), new Component[]{surviveL,surviveCondition});
        addPanel(new JPanel(), new Component[]{cityButton});
        addPanel(new JPanel(), new Component[]{reproduceL,reproduceCondition});
        addPanel(new JPanel(), new Component[]{dakotaButton});
        addPanel(new JPanel(), new Component[]{continueButton});
        addPanel(new JPanel(), new Component[]{dieHardButton});
        addPanel(new JPanel(), new Component[]{footer});
    }

    private void addPanel(JPanel panel,Component [] components){
        for(Component component:components)
            panel.add(component);
        this.add(panel);
    }

    private void parseParameters(){
        if(gridWidth.getText().length() == 0 || !gridWidth.getText().matches("\\d+") ) gridWidthValue = 10;
        else gridWidthValue = Integer.parseInt(gridWidth.getText());
        if(gridHeight.getText().length() == 0 || !gridWidth.getText().matches("\\d+")) gridHeightValue = 10;
        else gridHeightValue = Integer.parseInt(gridHeight.getText());
        if(surviveCondition.getText().length() == 0) surviveConditionValues = new int[] {2,3};
        else {
            String surviveConditionText = surviveCondition.getText();
            this.surviveConditionValues = new int[surviveConditionText.length()];
            for(int i = 0; i < surviveConditionText.length(); i++){
                if(Character.isDigit(surviveConditionText.charAt(i)))
                    surviveConditionValues[i] = Character.getNumericValue((surviveConditionText.charAt(i)));
            }
        }
        if(reproduceCondition.getText().length() == 0) reproduceConditionValues = new int[]{3};
        else{
            String reproduceConditionText = reproduceCondition.getText();
            this.reproduceConditionValues = new int[reproduceConditionText.length()];
            for(int i = 0; i < reproduceConditionText.length(); i++){
                if(Character.isDigit(reproduceConditionText.charAt(i)))
                    reproduceConditionValues[i] = Character.getNumericValue(reproduceConditionText.charAt(i));
            }
        }
    }

}

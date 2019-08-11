package ProgramFiles.GuiFiles;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

class DatePicker extends JPanel {

    private SpinnerModel dayEditor = new SpinnerNumberModel(1, 1, 31, 1);
    private SpinnerModel monthEditor = new SpinnerNumberModel(1, 1, 12, 1);
    private SpinnerModel yearEditor = new SpinnerNumberModel(
            LocalDate.now().getYear(),
            LocalDate.now().getYear(),
            LocalDate.MAX.getYear(),
            1);
    private JSpinner daySpinner = new JSpinner(dayEditor);
    private JSpinner monthSpinner = new JSpinner(monthEditor);
    private JSpinner yearSpinner = new JSpinner(yearEditor);

    DatePicker(){
        super();


        setLayout(new GridLayout(1, 3));

        ((JSpinner.DefaultEditor) daySpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) monthSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) yearSpinner.getEditor()).getTextField().setEditable(false);

        add(daySpinner);
        add(monthSpinner);
        add(yearSpinner);
    }

    LocalDate getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return simpleDateFormat.parse(daySpinner.getValue().toString() + "-"
                    + monthSpinner.getValue().toString() + "-"
                    + yearSpinner.getValue().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}

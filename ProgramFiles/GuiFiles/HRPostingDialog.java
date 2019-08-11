package ProgramFiles.GuiFiles;

import ProgramFiles.Posting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class HRPostingDialog extends JDialog implements ActionListener {

    private JTextField postingID = new JTextField();
    private JTextField postingTitle = new JTextField();
    private JTextArea postingDescription = new JTextArea();

    private SpinnerModel numberEditor = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
    private JSpinner numHireSpinner = new JSpinner(numberEditor);

    private DatePicker datePicker = new DatePicker();

    private JButton cancel = new JButton("Cancel");
    private JButton addPosting = new JButton("Add");

    private HRHiringPanel hrHiringPanel;

    HRPostingDialog(HRHiringPanel hrHiringPanel){
        super();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.hrHiringPanel = hrHiringPanel;
        hrHiringPanel.getRootPane().setVisible(false);

        JPanel inputArea =  new JPanel();
        JPanel buttons = new JPanel(new GridLayout(1, 2));

        buttons.add(cancel);
        buttons.add(addPosting);
        cancel.addActionListener(this);
        addPosting.addActionListener(this);

        postingTitle.setColumns(20);

        ((JSpinner.DefaultEditor) numHireSpinner.getEditor()).getTextField().setEditable(false);

        GridLayout gridLayout = new GridLayout(11, 1);
        gridLayout.setVgap(20);
        inputArea.setLayout(gridLayout);
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height * 7 / 10;
        int width = screenSize.width / 4;

        setSize(new Dimension(width, height));
        setResizable(false);

        postingDescription.setLineWrap(true);
        JScrollPane description = new JScrollPane(postingDescription);

        inputArea.add(new JLabel("Enter job ID"));
        inputArea.add(postingID);
        inputArea.add(new JLabel("Enter the Posting title*"));
        inputArea.add(postingTitle);
        inputArea.add(new JLabel("Enter the post description*"));
        inputArea.add(description);
        inputArea.add(new JLabel("Enter posting end date"));
        inputArea.add(datePicker);
        inputArea.add(new JLabel("Enter the number of openings:"));
        inputArea.add(numHireSpinner);
        inputArea.add(buttons);
        add(inputArea);
    }

    public void actionPerformed(ActionEvent e){
        Posting created;
        JButton clicked = (JButton) e.getSource();
        if (clicked == cancel){
            this.dispose();
            hrHiringPanel.getRootPane().setVisible(true);
        } else if (clicked == addPosting){
            this.dispose();
            created = createPosting();
            this.hrHiringPanel.addPosting(created);
            hrHiringPanel.getRootPane().setVisible(true);
        }
    }

    private Posting createPosting(){
        return new Posting(
                postingID.getText(),
                postingTitle.getText(),
                postingDescription.getText(),
                LocalDate.now(),
                datePicker.getDate(),
                LocalDate.now(),
                (int)numHireSpinner.getValue());
    }
}

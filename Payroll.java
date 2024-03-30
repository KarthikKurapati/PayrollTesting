import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Payroll{
    private JFrame frame;
    private JPanel top,bottom,centerR,centerL,center;
    // private JComponent firstname[], lastname[],id[],paycode[],hours[],shift[],tax[],insurance[],overtime[],homePay[];
    ArrayList<JLabel> questions= new ArrayList<JLabel>();
    ArrayList<JTextField> answers = new ArrayList<JTextField>();
    ArrayList<JLabel> display= new ArrayList<JLabel>();
    private JComboBox shiftSelector , paycodeSelector;
    public Payroll(){
        Clicklistener click = new Clicklistener();
        frame = new JFrame();
        frame.setBackground(new Color(180, 191, 98));
        frame.setSize(830,600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        top = new JPanel();
        top.setBackground(new Color(50,168,82));
        top.setBounds(0,0,830,100);
        frame.add(top);

        bottom = new JPanel();
        bottom.setBackground(new Color(50,168,82));
        bottom.setBounds(0,500,830,100);
        frame.add(bottom,BorderLayout.SOUTH);

        centerR = new JPanel();
        centerR.setBackground(new Color(50,168,82));
        centerR.setBounds(415,100,415,400);
        displayLabels();
        frame.add(centerR);

        centerL = new JPanel();
        centerL.setBackground(new Color(50,168,82));
        centerL.setBounds(0,100,315,400);
        centerL.setLayout(new BoxLayout(centerL, BoxLayout.PAGE_AXIS));
        createLablesFields();
        frame.add(centerL);




        frame.setVisible(true);
    }
    
    private void displayLabels() {
        display.add(new JLabel());
    }

    private void createLablesFields() {
        questions.add(new JLabel("What is the employee's first name?"));
        questions.add(new JLabel("What is the employee's last name?"));
        questions.add(new JLabel("What is the employee's id?"));
        questions.add(new JLabel("What is the employee's paycode?"));
        questions.add(new JLabel("How many hours has the employee worked?"));
        questions.add(new JLabel("What shift does the employee work?"));

        answers.add(new JTextField());
        answers.add(new JTextField());
        answers.add(new JTextField());
        answers.add(new JTextField());
        answers.add(new JTextField());

        Integer[] paycodes = {1,2,3,4,5};
        paycodeSelector = new JComboBox(paycodes);

        String[] shifts = {"Day","Night"};
        shiftSelector = new JComboBox(shifts);

        for(int i = 0; i < questions.size(); i++){
            centerL.add(Box.createRigidArea(new Dimension(20,10)));
            centerL.add(questions.get(i));
            if(questions.get(i).getText().equals("What is the employee's paycode?")){
                centerL.add(paycodeSelector);
            }else if(questions.get(i).getText().equals("What shift does the employee work?")){
                centerL.add(shiftSelector);
            }else{
                questions.get(i).setHorizontalAlignment(JLabel.LEFT);
                centerL.add(answers.get(i));
            }
            centerL.add(Box.createRigidArea(new Dimension(5,10)));
        }


    }
    private class Clicklistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
}
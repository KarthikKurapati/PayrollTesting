import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Payroll{
    private JFrame frame;
    private JPanel top,bottom,centerR,centerL,center;
    // private JComponent firstname[], lastname[],id[],paycode[],hours[],shift[],tax[],insurance[],overtime[],homePay[];
    ArrayList<String> order = new ArrayList<String>();
    ArrayList<JLabel> questions= new ArrayList<JLabel>();
    ArrayList<JTextField> answers = new ArrayList<JTextField>();
    ArrayList<JLabel> display = new ArrayList<JLabel>();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    JLabel error = new JLabel();
    private JComboBox shiftSelector , paycodeSelector;
    double hours , overtime, insurance , takeHomePay,tax, total;
    int paycode; 
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
        top.setBounds(315,0,515,100);
        top.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        frame.add(top);

        bottom = new JPanel();
        bottom.setBackground(new Color(50,168,82));
        bottom.setBounds(0,500,830,100);
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT,13,10));
        frame.add(bottom,BorderLayout.SOUTH);

        centerR = new JPanel();
        centerR.setBackground(new Color(50,168,82));
        centerR.setBounds(330,100,500,400);
        centerR.setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
        frame.add(centerR);

        centerL = new JPanel();
        centerL.setBackground(new Color(50,168,82));
        centerL.setBounds(0,100,330,400);
        centerL.setLayout(new BoxLayout(centerL, BoxLayout.PAGE_AXIS));
        createLablesFields();
        frame.add(centerL);
        placeDisplayLabels();
        setButtons(click);



        frame.setVisible(true);
    }
    
    private void setButtons(ActionListener c) {
       //create and add new buttons to the list
       buttons.add(new JButton("Calculate"));
       buttons.add(new JButton("Clear"));
       buttons.add(new JButton("Exit"));
       for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).addActionListener(c);
            bottom.add(buttons.get(i));
       }
    }

    private void placeDisplayLabels() {
        display.add(new JLabel("First"));
        display.add(new JLabel("Last"));
        display.add(new JLabel("ID"));
        display.add(new JLabel("Paycode"));
        display.add(new JLabel("Hours"));
        display.add(new JLabel("Shift"));
        display.add(new JLabel("Tax"));
        display.add(new JLabel("Insurance"));
        display.add(new JLabel("Overtime"));
        display.add(new JLabel("Home"));
        order.add("First Name: ");
        order.add("Last Name: ");
        order.add("ID: ");
        order.add("Paycode: ");
        order.add("Hours: ");
        order.add("Shift: ");
        order.add("Tax: ");
        order.add("Insurance: ");
        order.add("Overtime: ");
        order.add("Total Take Home Pay: ");       
        for(int i = 0; i < display.size();i++){
            if(i == 0 || i == 1){
                top.add(display.get(i));
            }else{
                centerR.add(display.get(i));
            }
            
        }
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
        answers.add(null);
        answers.add(new JTextField());
        answers.add(null);
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
            // Exit button functionality - ends the program
            if(e.getSource() == buttons.get(2)){
                System.exit(0);
            }
            // Clear button resets all values in textfields and dropdowns
            if(e.getSource() == buttons.get(1)){
                for(int i = 0; i < answers.size(); i++ ){
                    answers.get(i).setText("");
                    answers.get(4).setText("");
                }
                shiftSelector.setSelectedItem("Day");
                paycodeSelector.setSelectedItem(1);
            }
            // Calculate Button functionality
            // So inputvalidation is only needed for hours
            //if input is correct, then Display to the screen
            if(e.getSource() == buttons.get(0)){
                if(inputValidation()){
                    calculateMoney();
                    for(int i = 0; i < answers.size(); i++ ){
                        if(answers.get(i) == null){
                            if(i == 3){
                                display.get(i).setText(order.get(i) + paycodeSelector.getSelectedItem());
                            }else{
                                display.get(i).setText(order.get(i) + shiftSelector.getSelectedItem() + "");
                            }
                        }else if(i == 0 || i == 1){
                            display.get(i).setText(answers.get(i).getText());
                        }
                        else{
                            display.get(i).setText(order.get(i) + answers.get(i).getText());
                        }
                    }
                }
                display.get(6).setText(order.get(6) + tax);
                display.get(7).setText(order.get(7) + insurance);
                display.get(8).setText(order.get(8) + overtime);
                display.get(9).setText(order.get(9) + takeHomePay);
            }
            
        }

        private void calculateMoney() {
            tax = 0;
            insurance = 0;
            takeHomePay = 0;
            total = 0;
            overtime = 0;
            if(hours > 40){
                total = 40 * (7.5 + (paycode * 2.5 ));
                overtime = ((7.5 + (paycode * 2.5 )) * 1.5 ) * (hours - 40);
                total = takeHomePay + overtime;
            }else{
                total = 40 * (7.5 + (paycode * 2.5 ));
            }
            tax = total * .10;
            insurance = total * 0.05;
            takeHomePay = total - tax - insurance;
            
        }

        private boolean inputValidation() {
            bottom.add(error);
            //Input Validation - Dropdown boxes do not need input validation
            try{
                //if parse fails, then we know there is an illegal input
                hours = Double.parseDouble(answers.get(4).getText());
                paycode = (int)Double.parseDouble(paycodeSelector.getSelectedItem() + "");
            }catch(NumberFormatException e){
                bottom.add(error);
                error.setText("Illegal Input - Hours");
                return false;
            }
            if(hours <= 0){
                error.setText("Illegal Input - Hours");
                return false;
            }else 
                error.setText("");
                return true;

        }
        
    }
}
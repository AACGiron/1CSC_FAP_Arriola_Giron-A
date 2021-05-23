/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FAP;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.time.*;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
    
public class ListOfRecords implements ActionListener{
    
    AddRecord addRecord;
    RemoveRecord removeRecord;
    
    private JFrame frame;
    private JPanel panText, panButton, panSort, panRadioButton, panClickables;
    ButtonGroup buttgrpRadButt;
    
    private JTextArea taInfo;
    private JScrollPane scrl;
    private JButton buttAddRecord, buttRemRecord, buttExport;
    
    private JRadioButton radbuttAscend, radbuttDescend;
    JComboBox comboxSort;
    
    final String[] sortOptions = {"Name", "Birthday", "Age"};
    
    public static ArrayList<Person> names;
    String infoText;
            
    
   public ListOfRecords() {
   
        frame = new JFrame("List of Records");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panText = new JPanel();
        panButton = new JPanel(); 
        panSort = new JPanel();
        panRadioButton = new JPanel();
        panClickables = new JPanel();
        
        
        taInfo = new JTextArea(20, 60);
	scrl = new JScrollPane(taInfo);
        
        scrl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        
        buttAddRecord = new JButton("Add a record");
        buttRemRecord = new JButton("Remove a record");
        buttExport = new JButton("Export to CSV file");
        
        buttgrpRadButt = new ButtonGroup();
        radbuttAscend = new JRadioButton("Ascending");
        radbuttDescend = new JRadioButton("Descending");
        
        buttgrpRadButt.add(radbuttAscend);
        buttgrpRadButt.add(radbuttDescend);
        
        comboxSort = new JComboBox(sortOptions);
        comboxSort.setSelectedIndex(0);
        
        names = new ArrayList<Person>();
        
        infoText = "Names \t Birthday \t Age\n";
        
   } 
   
   public void launchFrame() {
        
        panText.setLayout(new GridLayout(1, 3));
        panSort.setLayout(new GridLayout(1, 2));
        panRadioButton.setLayout(new GridLayout(2, 1));
        panClickables.setLayout(new GridLayout(2, 1));
        
        panButton.add(buttAddRecord);
        panButton.add(buttRemRecord);
        panButton.add(buttExport);
        
        panRadioButton.add(radbuttAscend);
        panRadioButton.add(radbuttDescend);
        
        panSort.add(comboxSort);
        panSort.add(panRadioButton);
        
        panClickables.add(panSort);
        panClickables.add(panButton);
        
        panText.add(scrl, BorderLayout.CENTER);
        
        this.taInfo.setText(infoText);
        
        
        
       //adds the text area and button panel to the window.
        frame.add(panText, "Center");
        
        frame.add(panClickables, "South");
       
       //sizes the window correctly and makes it visible.
        frame.pack();
        frame.setVisible(true);
        
       //connects to actionPerformed to make the buttons interactable.
        buttAddRecord.addActionListener(this);
        buttRemRecord.addActionListener(this);
        buttExport.addActionListener(this);

    }
   
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        Object sauce = actionEvent.getSource();
        
        //for this class
        
        if (sauce == buttAddRecord) {
            addRecord = new AddRecord();
            addRecord.launchFrame();
            addRecord.buttSaveBack.addActionListener(this);
            addRecord.buttSaveAnother.addActionListener(this);
            addRecord.buttBack.addActionListener(this);
        }
        if (sauce == buttRemRecord) {
            removeRecord = new RemoveRecord();
            removeRecord.launchFrame();
            removeRecord.buttRemoveBack.addActionListener(this);
            removeRecord.buttRemoveAnother.addActionListener(this);
            removeRecord.buttBack.addActionListener(this);
        }
        if (sauce == buttExport) {
            export();
        }
        
        
        
        
        
        //for addRecord
        try{
         if (sauce == addRecord.buttSaveBack) {
            if (addRecord.comboxMonth.getSelectedIndex() < 10) {
                addRecord.sMonth = "0" + Integer.toString(addRecord.comboxMonth.getSelectedIndex() + 1);
            }
            else { 
                addRecord.sMonth = Integer.toString(addRecord.comboxMonth.getSelectedIndex() + 1);
            }
            
            addRecord.birthday = LocalDate.parse((addRecord.comboxYear.getSelectedItem()  + 
                    "-" + addRecord.sMonth + "-" + addRecord.comboxDay.getSelectedItem()));
            names.add(new Person(addRecord.tfName.getText(),addRecord.birthday));
             refresh();
             addRecord.frame.dispose();
            ////close
        }
        
        if (sauce == addRecord.buttSaveAnother) {
            if (addRecord.comboxMonth.getSelectedIndex() < 10) {
                
                addRecord.sMonth = "0" + Integer.toString(addRecord.comboxMonth.getSelectedIndex() + 1);
            }
            else { 
                addRecord.sMonth = Integer.toString(addRecord.comboxMonth.getSelectedIndex() + 1);
            }
            addRecord.birthday = LocalDate.parse((addRecord.comboxYear.getSelectedItem()  + "-" + 
                    addRecord.sMonth + "-" + addRecord.comboxDay.getSelectedItem()));
            names.add(new Person(addRecord.tfName.getText(),addRecord.birthday));
            refresh();
        }
        if (sauce == addRecord.buttBack) {
            ////close
            addRecord.frame.dispose();
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please input a proper date!");
        }
        
        
        //for removeRecord
        try{
        if (sauce == removeRecord.buttRemoveBack) {
            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).getName().equals(removeRecord.tfName.getText())) {
                    names.remove(i);
                    refresh();
                    break;
                    
                }
                ////if i == names size throw exception
            }
                        
            
            removeRecord.frame.dispose();
            ////close
            
        }
        
        if (sauce == removeRecord.buttRemoveAnother) {
            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).getName().equals(removeRecord.tfName.getText())) {
                    names.remove(i);
                    refresh();
                    break;
                }
            }
        }
        
        if (sauce == removeRecord.buttBack) {
            removeRecord.frame.dispose();
            
            ////close
        
        }
        }
        catch (NullPointerException e){
            System.out.print("");
        }
        
    }
    
    
    public void refresh() {
        
        ////for loop, inputs all Person name\n, birthday\n and ageCalculator() to respective JTextAreas, but first clears the text
        infoText = "Names \t Birthday \t Age\n";
        for (int i = 0; i < names.size(); i++) {
            infoText += names.get(i).getName() + " \t " + names.get(i).getBirthday() + " \t " + names.get(i).getAge() + "\n";
            
        }
        this.taInfo.setText(infoText);
        
        
    }
    
    private void export() {
    ////export to CSV
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String file = dtf.format(now);

        try{
            String content = this.taInfo.getText();
            PrintWriter pw = new PrintWriter (new FileWriter(file + ".csv"));
            pw.println(content);
            pw.close();      
        } 

        catch (IOException e){
        System.out.println("An error was encountered");
        }
    
    }
    
    
    
    
    
    
    
    
    
    

}

class AddRecord {

    ListOfRecords listOfRecords;
    
    protected static JFrame frame;
    protected static JPanel panName, panBirthday, panButton;
    protected static JLabel labName, labBirthday;
    protected static JTextField tfName;
    protected static JComboBox comboxMonth, comboxDay, comboxYear;
    protected static JButton buttSaveBack, buttSaveAnother, buttBack;
    
    protected static LocalDate birthday;
    
    final String[] mm = {"January", "February", "March", "April", "May", "June", "July", 
                   "August", "September", "October", "November", "December"};
    final String[] dd = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] yyyy;
    int yearNow;
    String sMonth;
            
    
    AddRecord() {
        
        ////For loop, sets yyyy into array of years, 1900 to current year;
        yearNow = Period.between(LocalDate.parse("1900-01-01"), LocalDate.now()).getYears() + 1;
        yyyy = new String[yearNow];
        for (int i = 0; i < yyyy.length; i++) {
            yyyy[i] = Integer.toString(1900 + i);
            
        }
        
    
        frame = new JFrame("Add Record");
        
        panName = new JPanel();
        panBirthday = new JPanel(); 
        panButton = new JPanel();
     
        labName = new JLabel("Name: ");
        labBirthday = new JLabel("Birthday");
     
        tfName = new JTextField(40);
     
        comboxMonth = new JComboBox(mm);
        comboxDay = new JComboBox(dd);
        comboxYear = new JComboBox(yyyy);
     
        buttSaveBack = new JButton("Save and Go Back");
        buttSaveAnother = new JButton("Save and Add Another");
        buttBack = new JButton("Back");
       
       
    }
    
    void launchFrame() {
        
        frame.setLayout(new GridLayout(3,1));
        
        panName.setLayout(new GridLayout(1, 2));
        panBirthday.setLayout(new GridLayout(1, 4));
        panButton.setLayout(new GridLayout(1,3));
        
        panName.add(labName);
        panName.add(tfName);
        
        panBirthday.add(labBirthday);
        panBirthday.add(comboxMonth);
        panBirthday.add(comboxDay);
        panBirthday.add(comboxYear);
        
        panButton.add(buttSaveBack);
        panButton.add(buttSaveAnother);
        panButton.add(buttBack);
        
        frame.add(panName);
        frame.add(panBirthday);
        frame.add(panButton);
        
        frame.pack();
        frame.setVisible(true);
        
        
    }

    
}








class RemoveRecord {

    protected JFrame frame;
    protected JPanel panName, panButton;
    protected JLabel labName;
    protected JTextField tfName;
    protected JButton buttRemoveBack, buttRemoveAnother, buttBack;
    
    protected String name;
            
    RemoveRecord() {
        
    
     frame = new JFrame("Remove Record");
        
     panName = new JPanel();
     panButton = new JPanel();
     
     labName = new JLabel("Name: ");
     
     tfName = new JTextField(40);
     
     buttRemoveBack = new JButton("Remove and Go Back");
     buttRemoveAnother = new JButton("Remove and Add another");
     buttBack = new JButton("Back");
       
    }
    
    void launchFrame() {
        
        frame.setLayout(new GridLayout(2,1));
        
        panName.setLayout(new GridLayout(1, 2));
        panButton.setLayout(new GridLayout(1,3));
        
        panName.add(labName);
        panName.add(tfName);
        
        panButton.add(buttRemoveBack);
        panButton.add(buttRemoveAnother);
        panButton.add(buttBack);
        
        frame.add(panName);
        frame.add(panButton);
        
        frame.pack();
        frame.setVisible(true);
       
        
    }
    
    
}

class Person {
    private String name;
    private LocalDate birthday;
    private int age;
    private LocalDate dateNow;
    Person(String name, LocalDate birthday) {
        setName(name);
        setBirthday(birthday);
        this.birthday.format(DateTimeFormatter.ofPattern("dd-MM-yyyy").withResolverStyle(ResolverStyle.STRICT));
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return(name);
    }
    
    
    private void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        
        //put here instead of constructor so that it runs every time the birthday is changed.
        computeAge(birthday);
    }
    
    public LocalDate getBirthday() {
    
        return(birthday);
    }
    
    private void computeAge(LocalDate birthday) {
        
        
        this.age = Period.between(birthday, LocalDate.now()).getYears();
    }
    
    public int getAge() {
        return age;
    }
    
    
}


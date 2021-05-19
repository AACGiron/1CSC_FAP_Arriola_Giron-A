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
import java.util.*;
    
public class ListOfRecords implements ActionListener{
    
    AddRecord addRecord;
    RemoveRecord removeRecord;
    
    private JFrame frame;
    private JPanel panText, panButton, panSort, panClickables;
    ButtonGroup buttgrpRadButt;
    
    private JTextArea taNames, taBirthdays, taAge;
    private JButton buttAddRecord, buttRemRecord, buttExport;
    
    private JRadioButton radbuttAscend, radbuttDescend;
    JComboBox comboxSort;
    
    final String[] sortOptions = {"Name", "Birthday", "Age"};
    
    static Person[] names;
            
    
   public ListOfRecords() {
   
        frame = new JFrame("List of Records");
        
        panText = new JPanel();
        panButton = new JPanel(); 
        panSort = new JPanel();
        panClickables = new JPanel();
        
        
        taNames = new JTextArea(20, 20);
        taBirthdays = new JTextArea(20, 20);
        taAge = new JTextArea(20, 20);
        
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
        
        names = new Person[0];
        
        
   } 
   
   public void launchFrame() {
        
        panText.setLayout(new GridLayout(1, 3));
        panSort.setLayout(new GridLayout(1, 3));
        panClickables.setLayout(new GridLayout(2, 1));
        
        panButton.add(buttAddRecord);
        panButton.add(buttRemRecord);
        panButton.add(buttExport);
        
        panSort.add(comboxSort, "Left");
        panSort.add(radbuttAscend);
        panSort.add(radbuttDescend);
        
        panClickables.add(panSort);
        panClickables.add(panButton);
        
        panText.add(taNames);
        panText.add(taBirthdays);
        panText.add(taAge);
        
        
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
        
        if (sauce == buttAddRecord) {
            addRecord = new AddRecord();
            addRecord.launchFrame();
            
            refresh();
            
        }
        
        if (sauce == buttRemRecord) {
            removeRecord = new RemoveRecord();
            removeRecord.launchFrame();
           
            ////for loop, creates a new array length-1 of names[] and copies all items not in index 0
            
            refresh();
            
        }
        
        if (sauce == buttExport) {
            
            export();
            
        }
        
    
    }
    
    protected void addRecord(String name, Date birthday) {
        
        ////for loop, creates a new array length+1 of names[] and copies all items + the new person
        
    
    }
    
    protected void removeRecord(String name) {
        
        ////for loop, creates a new array length-1 of names[] and copies all items - the person removed
        
    
    }
    
    private void refresh() {
        
        ////for loop, inputs all Person name\n, birthday\n and ageCalculator() to respective JTextAreas, but first clears the text
                
    }
    
    private void export() {
    ////export to CSV
    
    
    }
    
    private void ageCalculator(int birth_date, int birth_month, int birth_year) {
    
        
    
    }

}

class AddRecord implements ActionListener{

    ListOfRecords listOfRecords;
    
    private JFrame frame;
    private JPanel panName, panBirthday, panButton;
    private JLabel labName, labBirthday;
    private JTextField tfName;
    private JComboBox comboxMonth, comboxDay, comboxYear;
    private JButton buttSaveBack, buttSaveAnother, buttBack;
    
    private String name;
    
    private Date birthday;
    
    final String[] mm = {"January", "February", "March", "April", "May", "June", "July", 
                   "August", "September", "October", "November", "December"};
    final int[] dd = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    int[] yyyy;
            
    
    AddRecord() {
        
     ////For loop, sets yyyy into array of years, 1900 to current year;
    
     frame = new JFrame("Add Record");
        
     panName = new JPanel();
     panBirthday = new JPanel(); 
     panButton = new JPanel();
     
     labName = new JLabel("Name: ");
     labBirthday = new JLabel("Birthday");
     
     tfName = new JTextField(40);
     
     comboxMonth = new JComboBox();
     comboxDay = new JComboBox();
     comboxYear = new JComboBox();
     
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
        
        buttSaveBack.addActionListener(this);
        buttSaveAnother.addActionListener(this);
        buttBack.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object sauce = actionEvent.getSource();
        
        if (sauce == buttSaveBack) {
        
            listOfRecords.addRecord(name, birthday);
            
            ////close
        
        }
        
        if (sauce == buttSaveAnother) {
            
            listOfRecords.addRecord(name, birthday);
            
        }
        
        if (sauce == buttBack) {
            
            ////close
        
        }
    
    }
    
}

class RemoveRecord implements ActionListener{

    ListOfRecords listOfRecords;
    
    private JFrame frame;
    private JPanel panName, panButton;
    private JLabel labName;
    private JTextField tfName;
    private JButton buttRemoveBack, buttRemoveAnother, buttBack;
    
    private String name;
            
    RemoveRecord() {
        
     ////For loop, sets yyyy into array of years, 1900 to current year;
    
     frame = new JFrame("Remove Record");
        
     panName = new JPanel();

     panButton = new JPanel();
     
     labName = new JLabel("Name: ");
     
     tfName = new JTextField(40);
     
     buttRemoveBack = new JButton("Save and Go Back");
     buttRemoveAnother = new JButton("Save and Add Another");
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
        
        buttRemoveBack.addActionListener(this);
        buttRemoveAnother.addActionListener(this);
        buttBack.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object sauce = actionEvent.getSource();
        
        if (sauce == buttRemoveBack) {
        
            listOfRecords.removeRecord(name);
            
            ////close
        
        }
        
        if (sauce == buttRemoveAnother) {
            
            listOfRecords.removeRecord(name);
            
        }
        
        if (sauce == buttBack) {
            
            ////close
        
        }
    
    }
    
    
}

class Person {

    Person(String name, Date birthday) {
    
    
    }
    
}

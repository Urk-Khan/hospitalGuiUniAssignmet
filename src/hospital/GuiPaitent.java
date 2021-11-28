package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class GuiPaitent extends JFrame implements ActionListener{
    JLabel lid, lname, lage, lcondition, l5;
    JRadioButton remergency, rnormal;
    JTextField tid, tname, tage;
    JButton binsert, bupdate, bdelete, bselect, bselectAll, bhome;
    JComboBox cb1;
    
    GuiPaitent()
    {
        setVisible(true);
        setSize(1000, 500);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());
        lid = new JLabel("Id No");
        lname = new JLabel("Name");
        lage = new JLabel("age");
        l5 = new JLabel("");        //for output
        lcondition =new JLabel("condition");
        
        remergency=new JRadioButton("Emergency");
        rnormal=new JRadioButton("Normal");
        cb1 = new JComboBox();
        
        tid = new JTextField(15);
        tname = new JTextField(15);
        tage = new JTextField(25);

        binsert = new JButton("Insert");
        bupdate = new JButton("Update");
        bdelete = new JButton("Delet");
        bselect = new JButton("Display");
        bselectAll = new JButton("Display_All");
        bhome =new JButton("Home");

        ButtonGroup bg=new ButtonGroup();
        bg.add(remergency);
        bg.add(rnormal);
        
        add(lid);       add(tid);
        add(lname);     add(tname);
        add(lage);     add(tage);
        
        add(lcondition);
        add(remergency); add(rnormal);
        add(binsert);
        add(bupdate);
        add(bdelete);
        add(bselect);
        add(bselectAll);

        add(l5);
        add(cb1);
        add(bhome);

        binsert.addActionListener(this);
        bupdate.addActionListener(this);
        bdelete.addActionListener(this);
        bselect.addActionListener(this);
        bselectAll.addActionListener(this);
        bhome.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DAOpaitent dao =new DAOpaitent();
            paitent p = new paitent();
            if (e.getSource() == binsert) {
                if (tid.getText() != null) {
                    p.id = Integer.parseInt(tid.getText());
                } else {
                    p.id = 0;
                }   
                p.name = tname.getText();
                p.age = Integer.parseInt(tage.getText());
                if (remergency.isSelected()){
                    p.condition="Emergency";
                }else{
                    p.condition="Normal";
                }   dao.insert(p);
                l5.setText("inseted");
            }
            else if (e.getSource() == bupdate) {
                if (tid.getText() != null) {
                    p.id = Integer.parseInt(tid.getText());
                } else {
                    p.id = 0;
                }
                p.name = tname.getText();
                p.age = Integer.parseInt(tage.getText());
                if (remergency.isSelected())
                {
                    p.condition="Emergency";
                }else if (rnormal.isSelected())
                {
                    p.condition="Normal";
                }
                dao.update(p);
                l5.setText("updated");
            }
            else if (e.getSource() == bdelete) {
                dao.delete(p);
                l5.setText("Deled");
            }
            else if (e.getSource() == bselect) {
                 if (tid.getText() != null) {
                     p.id = Integer.parseInt(tid.getText());
                 } else {
                     p.id = 0;  
                 }
                 cb1.removeAllItems();
                ResultSet ret = dao.SelectById(p);
                while (ret.next()) {
                   cb1.addItem("id: " + ret.getString(1) );
                   cb1.addItem("Name: " + ret.getString(2));
                   cb1.addItem("age: " +  ret.getString(3));
                   cb1.addItem("Condition: "+ret.getString(4));
                }
            }
            else if (e.getSource() == bselectAll)
            {
                cb1.removeAllItems();
                ResultSet ret = dao.SelectAll(p);
                while (ret.next()) {
                    cb1.addItem("id: " + ret.getString(1) );
                    cb1.addItem("Name: " + ret.getString(2));
                    cb1.addItem("Specialization: " +  ret.getString(3));
                    cb1.addItem("Shift: "+ret.getString(4));
                }
            }
            else if (e.getSource()==bhome)
            {
                form obj = new form();
            }
        }catch(Exception ex)
        {
            System.out.println("error try again");
            System.out.println(ex);
        }  
    } 
}
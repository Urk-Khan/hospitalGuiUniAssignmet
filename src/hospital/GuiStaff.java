package hospital;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class GuiStaff extends JFrame implements ActionListener{
    JLabel lid, lname, ldpt, l5;
    JRadioButton rmorning, rnight;
    JTextField tid, tname, tdpt;
    JButton bhome, binsert, bupdate, bdelete, bselect, bselectAll;
    JComboBox cb1;
    GuiStaff()
    {
        setVisible(true);
        setSize(1000, 500);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());
        lid = new JLabel("Id No");
        lname = new JLabel("Name");
        ldpt = new JLabel("Department");
        l5 = new JLabel("");        //for output
        
        rmorning=new JRadioButton("Morning");
        rnight=new JRadioButton("Night");
        cb1 = new JComboBox();

        tid = new JTextField(15);
        tname = new JTextField(15);
        tdpt = new JTextField(25);

        binsert = new JButton("Insert");
        bupdate = new JButton("Update");
        bdelete = new JButton("Delet");
        bselect = new JButton("Display");
        bselectAll = new JButton("Display_All");
        bhome =new JButton("Home");

        ButtonGroup bg=new ButtonGroup();
        bg.add(rmorning);
        bg.add(rnight);
        
        add(lid);       add(tid);
        add(lname);     add(tname);
        add(ldpt);     add(tdpt);
        
        add(rmorning); add(rnight);
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
        try{
            DAOstaff dao =new DAOstaff();
            staff s = new staff();
            if (e.getSource() == binsert) {
                if (tid.getText() != null) {
                    s.id = Integer.parseInt(tid.getText());
                } else {
                    s.id = 0;
                }   
                s.name = tname.getText();
                s.department = tdpt.getText();
                if (rmorning.isSelected()){
                    s.shift="Morning";
                }else{
                    s.shift="Night";
                }   
                dao.insert(s);
                l5.setText("inseted");
            }
            else if (e.getSource() == bupdate) {
                if (tid.getText() != null) {
                    s.id = Integer.parseInt(tid.getText());
                } else {
                    s.id = 0;
                }   
                s.name = tname.getText();
                s.department = tdpt.getText();
                if (rmorning.isSelected()){
                    s.shift="Morning";
                }else{
                    s.shift="Night";
                }
                dao.update(s);
                l5.setText("updated");
            }
            else if (e.getSource() == bdelete) {
                dao.delete(s);
                l5.setText("Deleted");
            }
            else if (e.getSource() == bselect) {
                 if (tid.getText() != null) {
                     s.id = Integer.parseInt(tid.getText());
                 } else {
                     s.id = 0;  
                 }
                 cb1.removeAllItems();
                ResultSet ret = dao.SelectById(s);
                while (ret.next()) {
                   cb1.addItem("id: " + ret.getString(1) );
                   cb1.addItem("Name: " + ret.getString(2));
                   cb1.addItem("Specialization: " +  ret.getString(3));
                   cb1.addItem("Shift: "+ret.getString(4));
                }
            }
            else if (e.getSource() == bselectAll)
            {
                cb1.removeAllItems();
                ResultSet ret = dao.SelectAll(s);
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
        }catch (Exception ex) {
            System.out.println("error, Try again");
            System.out.println(ex);
        }
    }
}
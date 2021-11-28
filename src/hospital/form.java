package hospital;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class form extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1;
    JButton bstf, bftf, bstaf;
    
    form()
    {
        setVisible(true);
        setSize(1000, 500);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());
        l1 = new JLabel("Hospital Management System");
        l2 = new JLabel("Select a Form to Enter Data");
        t1 =new JTextField(1);
        bstf = new JButton("Paitent Form");
        bftf = new JButton("Doctor Form");
        bstaf = new JButton("Staff Form");
        add (l1);
        add(bstf); add(bftf); add (bstaf);
        
        bstf.addActionListener(this);
        bftf.addActionListener(this);
        bstaf.addActionListener(this);
    }
                
    public void actionPerformed(ActionEvent ae) {
        try
        {
            if (ae.getSource() == bstf){
                GuiPaitent pobj = new GuiPaitent();
            }
            else if (ae.getSource() == bftf)
            {
                GuiDR dobj = new GuiDR();
            }
            else if (ae.getSource() == bstaf)
            {
                GuiStaff stf_obj = new GuiStaff();
            }
        }
        catch(Exception ex){
            System.out.println("Error In Form");
            System.out.println(ex);
        }
    }
    
            
}

/**
 * @author Dolunay Dagci
 * CISS 111-360
 * Assignment:  Ch20 Recursive Linked List
 * Due: 04.28.2019
 * This class is a graphical demonstration of the DD_Recursive Linked List class.
 * This class tests various methods, mainly the recursive sort and reverse methods
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DD_RecursiveLinkedListDemo extends JFrame
{
    private DD_RecursiveLinkedList ll;
    private JTextArea  listView;
    private JTextField cmdTextField;
    private JTextField resultTextField;

    private DD_RecursiveLinkedListDemo()
    {
        ll = new DD_RecursiveLinkedList();
        listView = new JTextArea();
        cmdTextField = new JTextField();
        resultTextField = new JTextField();

        // Create a panel and label for result field
        JPanel resultPanel = new JPanel(new GridLayout(1,2));
        resultPanel.add(new JLabel("Command Result"));
        resultPanel.add(resultTextField);
        resultTextField.setEditable(false);
        add(resultPanel, BorderLayout.SOUTH);

        // Put the textArea in the center of the frame
        add(listView);
        listView.setEditable(false);
        listView.setBackground(Color.WHITE);

        // Create a panel and label for the command text field
        JPanel cmdPanel = new JPanel(new GridLayout(1,2));
        cmdPanel.add(new JLabel("Command:"));
        cmdPanel.add(cmdTextField);
        add(cmdPanel, BorderLayout.NORTH);
        cmdTextField.addActionListener(new CmdTextListener());

        // Set up the frame
        setTitle("Recursive Linked List Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     Private class that responds to the command that
     the user types into the command entry text field.
     */
    private class CmdTextListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // Get the command from the command textfield.
            String cmdText = cmdTextField.getText();

            // Use a scanner to read the method in the command
            // and do a switch on it.
            Scanner sc = new Scanner(cmdText);
            String cmd = sc.next();
            switch(cmd)
            {
                case "add":
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    if (sc.hasNextInt())
                    {
                        // add index element
                        int index = sc.nextInt();
                        String element = sc.next();
                        ll.add(index, element);
                    }
                    else
                    {
                        // add element
                        String element = sc.next();
                        ll.add(element);
                    }
                    listView.setText(ll.toString());
                    pack();
                    return;
                case "remove":
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    if (sc.hasNextInt())
                    {
                        // remove index
                        int index = sc.nextInt();
                        String res = ll.remove(index);
                        resultTextField.setText(res);
                    }
                    else
                    {
                        // remove element
                        String element = sc.next();
                        boolean res = ll.remove(element);
                        String resText = String.valueOf(res);
                        resultTextField.setText(resText);
                    }
                    listView.setText(ll.toString());
                    pack();
                    return;
                case "isempty" :
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    String resText = String.valueOf(ll.isEmpty());
                    resultTextField.setText(resText);
                    return;
                case "size":
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    String resText1 = String.valueOf(ll.size());
                    resultTextField.setText(resText1);
                case "print":
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    listView.setText(ll.toString());
                    pack();
                    return;
                case "reverse":
                    listView.setText(""); //at the beginning of every command, all text fields are cleared
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    ll.reverse(); //reverse the list
                    listView.setText(ll.toString()); //print the list
                    pack();
                    return;
                case "sort":
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    ll.sort(); //sort the list in alphabetical order
                    listView.setText(ll.toString());
                    pack();
                    return;
                case "create": //create the list
                    listView.setText("");
                    cmdTextField.setText("");
                    resultTextField.setText("");
                    ll.add("Sandwich"); ll.add("Burger"); ll.add("Pizza"); ll.add("Cheese"); ll.add(1,"Sushi");
                    ll.add(5, "Muffin");
                    listView.setText(ll.toString());
                    pack();
                    return;
            }
        }
    }

    /**
     The main method creates an instance of the
     SwingLinkedList1Demo class which causes it to
     display its window.
     */
    public static void main(String [ ] args)
    {
        new DD_RecursiveLinkedListDemo();
    }
}
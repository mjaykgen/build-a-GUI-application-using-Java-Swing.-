package GUIClass;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

//student class
class Student {
    private int id;
    private String name;
    private boolean isGraduate;

    public Student(int id, String name, boolean isGraduate) {
        setID(id);
        this.name = name;
        this.isGraduate = isGraduate;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id > 0 ? id : -id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public void setGraduate(boolean isGraduate) {
        this.isGraduate = isGraduate;
    }

    public String toString() {
        return id + " " + name + " " + (isGraduate ? " Graduate" : " Undergraduate");
    }
}

class HandleStudents {
    private ArrayList<Student> arrStudents = new ArrayList<Student>();

    public void addStudent(Student s) {
        arrStudents.add(s);
    }

    public void deleteStudent(Student s) {
        // search for id and then remove
        arrStudents.remove(s);
    }

    public int countUnderGrad() {
        int c = 0;
        for (int i = 0; i < arrStudents.size(); i++)
            if (!arrStudents.get(i).isGraduate())
                c++;
        return c;
    }

    public ArrayList<Student> getArrStudents() {
        return arrStudents;
    }

    public void setArrStudents(ArrayList<Student> arrStudents) {
        this.arrStudents = arrStudents;
    }

}

class StudentFrame extends JFrame implements ActionListener {

    // data members
    private JTextField txtId, txtName;
    private JButton btnAdd;
    private JCheckBox chkGrad;
    private JList stdList; // a list that shows all the students added.
    // ArrayList<Student> arrStudents;

    // pass a HandleStudent obje
    HandleStudents hs;

    // constructor
    public StudentFrame(HandleStudents hs) {// ArrayList<Student> arrStudents) {
        super("Student Management");
        // this.arrStudents = arrStudents;
        this.hs = hs;
        txtId = new JTextField();
        txtName = new JTextField();
        btnAdd = new JButton("Add");
        chkGrad = new JCheckBox("Graduate?");// ,true);
        // String[] test={"hello","next"};
        stdList = new JList();// test);

        txtId.setBounds(80, 40, 100, 30);
        txtName.setBounds(80, 80, 100, 30);
        chkGrad.setBounds(200, 80, 100, 30);
        btnAdd.setBounds(80, 120, 80, 30);
        stdList.setBounds(80, 180, 400, 300);

        // allow the txtId to have digits only as input
        txtId.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' || e.getKeyChar() > '9')
                    e.consume();// stops the execution of key typed
            }
        });
        btnAdd.addActionListener(this);

        add(txtId);
        add(txtName);
        add(btnAdd);
        add(stdList);
        add(chkGrad);

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600, 600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = Integer.parseInt(txtId.getText());
        // arrStudents.add(new Student(id, txtName.getText(), chkGrad.isSelected()));
        hs.addStudent(new Student(id, txtName.getText(), chkGrad.isSelected()));
        // JOptionPane.showMessageDialog(this, s.toString());
        // show the lit of students in the JList
        stdList.setListData(hs.getArrStudents().toArray());

        // stdList.getSelectedIndex()
        // stdList.getse
        // clearing
        txtId.setText("");
        txtName.setText("");
        chkGrad.setSelected(false);
    }
}

public class TestStudentsCreation {
    public static void main(String[] args) {
        // ArrayList<Student> list = new ArrayList<Student>();
        new StudentFrame(new HandleStudents());
    }
}

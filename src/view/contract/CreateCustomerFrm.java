package view.contract;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Contract;
import model.User;
import model.Customer;
import dao.CustomerDAO;

public class CreateCustomerFrm extends JFrame implements ActionListener {

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtEmail;
    private JTextField txtPhoneNo;
    private JTextField txtDescription;

    private JButton btnCreate;

    private User user;
    private Contract contract;
    private CreateCustomerFrm mainFrm;

    public CreateCustomerFrm(User user, Contract contract) {
        super("Create Customer");
        this.user = user;
        this.contract = contract;
        txtName = new JTextField(15);
        txtAddress = new JTextField(15);
        txtEmail = new JTextField(15);
        txtPhoneNo = new JTextField(15);
        txtDescription = new JTextField(15);
        btnCreate = new JButton("Create");

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblHome = new JLabel("Create User");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHome.setFont(lblHome.getFont().deriveFont(20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel pnName = new JPanel();
        pnName.setLayout(new FlowLayout());
        pnName.add(new JLabel("Name:"));
        pnName.add(txtName);
        pnMain.add(pnName);

        JPanel pnAddress = new JPanel();
        pnAddress.setLayout(new FlowLayout());
        pnAddress.add(new JLabel("Address:"));
        pnAddress.add(txtAddress);
        pnMain.add(pnAddress);

        JPanel pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout());
        pnEmail.add(new JLabel("Email:"));
        pnEmail.add(txtEmail);
        pnMain.add(pnEmail);

        JPanel pnPhoneNo = new JPanel();
        pnPhoneNo.setLayout(new FlowLayout());
        pnPhoneNo.add(new JLabel("PhoneNo:"));
        pnPhoneNo.add(txtPhoneNo);
        pnMain.add(pnPhoneNo);

        JPanel pnDescription = new JPanel();
        pnDescription.setLayout(new FlowLayout());
        pnDescription.add(new JLabel("Description:"));
        pnDescription.add(txtDescription);
        pnMain.add(pnDescription);

        pnMain.add(btnCreate);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        btnCreate.addActionListener(this);
        this.setSize(400, 200);
        this.setLocation(200, 10);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton) e.getSource();
        if (btnClicked.equals(btnCreate)) {
            Customer customer = new Customer();
            customer.setName(txtName.getText());
            customer.setEmail(txtEmail.getText());
            customer.setPhoneNo(txtPhoneNo.getText());
            customer.setAddress(txtAddress.getText());
            customer.setDescription(txtDescription.getText());
            System.out.print(customer);
            CustomerDAO cd = new CustomerDAO();
            cd.addCustomer(customer);
//            if ((txtKey.getText() == null) || (txtKey.getText().length() == 0)) {
//                return;
//            }
//            CustomerDAO rd = new CustomerDAO();
//            listCustomer = rd.searchByName(txtKey.getText().trim());
//
//            String[] columnNames = {"id", "name", "email", "phoneNo", "address"};
//            String[][] value = new String[listCustomer.size()][5];
//            for (int i = 0; i < listCustomer.size(); i++) {
//                value[i][0] = listCustomer.get(i).getId() + "";
//                value[i][1] = listCustomer.get(i).getName();
//                value[i][2] = listCustomer.get(i).getEmail();
//                value[i][3] = listCustomer.get(i).getPhoneNo() + "";
//                value[i][4] = listCustomer.get(i).getAddress();
//            }
//            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
//                @Override
//                public boolean isCellEditable(int row, int column) {
//                    //unable to edit cells
//                    return false;
//                }
//            };
//            tblResult.setModel(tableModel);
        }
    }
}

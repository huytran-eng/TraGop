package view.contract;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import dao.CustomerDAO;
import model.Customer;
import model.Contract;

import model.User;

public class SearchCustomerFrm extends JFrame implements ActionListener {

    private ArrayList<Customer> listCustomer;
    private JTextField txtKey;
    private JButton btnSearch;
    private JTable tblResult;
    private JButton btnCreate;

    private User user;
    private Contract contract;
    private SearchCustomerFrm mainFrm;

    public SearchCustomerFrm(User user, Contract contract) {
        super("Search room to edit");
        this.user = user;
        this.contract = contract;
        mainFrm = this;
        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width - 5, this.getSize().height - 20);
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblHome = new JLabel("Search customer name");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHome.setFont(lblHome.getFont().deriveFont(20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel pn3 = new JPanel();
        pn3.setLayout(new BoxLayout(pn3, BoxLayout.X_AXIS));
        pn3.setSize(this.getSize().width - 5, 20);

//        pn3.add(new JLabel("Customer name "));
        btnCreate = new JButton("Create customer");
        btnCreate.addActionListener(this);
        pn3.add(btnCreate);
        pnMain.add(pn3);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.X_AXIS));
        pn1.setSize(this.getSize().width - 5, 20);
        pn1.add(new JLabel("Customer name "));
        txtKey = new JTextField();
        pn1.add(txtKey);
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);

        pn1.add(btnSearch);
        pnMain.add(pn1);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2, BoxLayout.Y_AXIS));
        tblResult = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblResult);
        tblResult.setFillsViewportHeight(false);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 250));

        tblResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = tblResult.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblResult.getRowHeight(); // get the row of the button

                // *Checking the row or column is valid or not
                if (row < tblResult.getRowCount() && row >= 0 && column < tblResult.getColumnCount() && column >= 0) {
                    System.out.println("da chon");
                    contract.setCustomer(listCustomer.get(row));
                    new SearchPartnerFrm(user, contract).setVisible(true);
                    mainFrm.dispose();
                }
            }
        });

        pn2.add(scrollPane);
        pnMain.add(pn2);
        this.add(pnMain);
        this.setSize(600, 300);
        this.setLocation(200, 10);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton) e.getSource();
        if (btnClicked.equals(btnSearch)) {
            if ((txtKey.getText() == null) || (txtKey.getText().length() == 0)) {
                return;
            }
            CustomerDAO rd = new CustomerDAO();
            listCustomer = rd.searchByName(txtKey.getText().trim());

            String[] columnNames = {"id", "name", "email", "phoneNo", "address"};
            String[][] value = new String[listCustomer.size()][5];
            for (int i = 0; i < listCustomer.size(); i++) {
                value[i][0] = listCustomer.get(i).getId() + "";
                value[i][1] = listCustomer.get(i).getName();
                value[i][2] = listCustomer.get(i).getEmail();
                value[i][3] = listCustomer.get(i).getPhoneNo() + "";
                value[i][4] = listCustomer.get(i).getAddress();
            }
            DefaultTableModel tableModel = new DefaultTableModel(value, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    //unable to edit cells
                    return false;
                }
            };
            tblResult.setModel(tableModel);
        } else {
            (new CreateCustomerFrm(user, contract)).setVisible(true);
            this.dispose();
        }
    }
}

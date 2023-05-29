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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.Contract;
import model.Payment;

public class PaymentFrm extends JFrame implements ActionListener {

    private JButton btnConfirm;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField txtDuration;
    private JTextField txtInterest;
    private JTextField txtPayCycle;
    private User user;
    private Contract contract;

    public PaymentFrm(Contract contract) {
        super("Payment Info");

        jLabel1 = new javax.swing.JLabel();
        txtDuration = new javax.swing.JTextField(15);
        jLabel2 = new javax.swing.JLabel();
        txtInterest = new javax.swing.JTextField(15);
        jLabel3 = new javax.swing.JLabel();
        txtPayCycle = new javax.swing.JTextField(15);
        btnConfirm = new javax.swing.JButton("confirm");
        this.user = user;
        this.contract = contract;

        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width - 5, this.getSize().height - 20);
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblHome = new JLabel("Payment Info");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHome.setFont(lblHome.getFont().deriveFont(20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel pnDuration = new JPanel();
        pnDuration.setLayout(new FlowLayout());
        pnDuration.add(new JLabel("Duration (months) :"));
        pnDuration.add(txtDuration);
        pnMain.add(pnDuration);

        JPanel pnInterest = new JPanel();
        pnInterest.setLayout(new FlowLayout());
        pnInterest.add(new JLabel("Interest Rate (%):"));
        pnInterest.add(txtInterest);
        pnMain.add(pnInterest);

        JPanel pnPayCycle = new JPanel();
        pnPayCycle.setLayout(new FlowLayout());
        pnPayCycle.add(new JLabel("Pay Cycle (month):"));
        pnPayCycle.add(txtPayCycle);
        pnMain.add(pnPayCycle);

        pnMain.add(btnConfirm);
        pnMain.add(Box.createRigidArea(new Dimension(0, 10)));
        btnConfirm.addActionListener(this);

        this.setSize(400, 200);
        this.setLocation(200, 10);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() instanceof JButton) && (((JButton) e.getSource()).equals(btnConfirm))) {
            contract.setDuration(Integer.valueOf(txtDuration.getText()));
            contract.setInterestRate(Float.valueOf(txtInterest.getText()));
            contract.setPayCycle(Integer.valueOf(txtPayCycle.getText()));
            contract.setTotalPrice(contract.getProductsPrice() * contract.getInterestRate());

            (new ContractFrm(contract)).setVisible(true);
            this.dispose();
        }
    }

}

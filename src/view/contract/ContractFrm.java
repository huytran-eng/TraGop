/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.contract;

import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import model.Contract;
import model.Payment;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import model.ContractProduct;
import dao.ContractDAO;

/**
 *
 * @author admin
 */
public class ContractFrm extends javax.swing.JFrame implements ActionListener {
    // Variables declaration - do not modify                     

    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private Contract contract;
    private ArrayList<Payment> payments;

    // End of variables declaration 
    /**
     * Creates new form test
     */
    public ContractFrm(Contract contract) {
        initComponents(contract);
    }

    private void initComponents(Contract contract) {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnConfirm = new javax.swing.JButton();
        this.contract = contract;
        int paymentTime = contract.getDuration() / contract.getPayCycle();
        float amount = contract.getTotalPrice() / (float) paymentTime;
        ArrayList<Payment> payments = new ArrayList<Payment>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date();
        for (int i = 0; i < paymentTime; i++) {
            Payment p = new Payment();

            p.setPaymentDate(addMonths(date, i));
            p.setAmount(amount);
            p.setContract(contract);
            payments.add(p);
        }
        contract.setPayments(payments);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        String[][] value = new String[payments.size()][3];
        for (int i = 0; i < payments.size(); i++) {
            value[i][0] = String.valueOf(i + 1) + "";
            value[i][1] = formatter.format(payments.get(i).getPaymentDate()) + "";
            value[i][2] = String.format("%.2f", payments.get(i).getAmount()) + "";
        }

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                value,
                new String[]{
                    "No", "Date", "Amount"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

        });
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setText("Payment Information");

        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(19, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnConfirm)
                                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnConfirm)
                                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton) e.getSource();
        if (btnClicked.equals(btnConfirm)) {
            ContractDAO cd = new ContractDAO();
            boolean result = cd.createContract(contract);
            this.dispose();
        }

    }

    private Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.contract;

import dao.ProductDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;
import java.util.ArrayList;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class SelectedProductFrm extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form SeletedProductView
     */
    public SelectedProductFrm(User user, Contract contract) {
        System.out.println("selected product view");
        initComponents(user, contract);
    }

    private void initComponents(User user, Contract contract) {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        this.user = user;
        this.contract = contract;
        ArrayList<ContractProduct> cps = contract.getContractProducts();
        String[][] value = new String[cps.size()][7];
        for (int i = 0; i < cps.size(); i++) {
            value[i][0] = cps.get(i).getId() + "";
            value[i][1] = cps.get(i).getProduct().getName() + "";
            value[i][2] = cps.get(i).getProduct().getCalculationUnit() + "";
            value[i][3] = String.format("%.2f", cps.get(i).getProduct().getPrice()) + "";
            value[i][4] = Integer.toString(cps.get(i).getQuantity()) + "";
            value[i][5] = String.format("%.2f", cps.get(i).getPrice()) + "";
            value[i][6] = cps.get(i).getProduct().getDescription() + "";
            System.out.println("here");
        }
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                value,
                new String[]{
                    "id", "name", "calculation unit", "price / unit", "quantity", "price", "description"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable1.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent evt) {
                int row = evt.getFirstRow();
                int col = evt.getColumn();
                if (col == 4) {
                    int val = Integer.valueOf((String) jTable1.getValueAt(row, col));
                    float pricePerUnit = Float.parseFloat((String) jTable1.getValueAt(row, 3));
                    System.out.println("price/user" + String.format("%.2f", pricePerUnit));
                    cps.get(row).setQuantity(val);
                    cps.get(row).setPrice((float) val * pricePerUnit);
                    System.out.println(String.format("%.2f", cps.get(row).getPrice()));
                    jTable1.setValueAt(String.format("%.0f", cps.get(row).getPrice()), row, col + 1);
                }
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(this);
        btnNext.setText("Next");
        btnNext.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNext)
                                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnBack)
                                        .addComponent(btnNext))
                                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton) e.getSource();
        if (btnClicked.equals(btnBack)) {
            new SearchProductFrm(user, contract).setVisible(true);
            this.dispose();
        }
        if (btnClicked.equals(btnNext)) {
            float productsPrice = 0;
            ArrayList<ContractProduct> cps = contract.getContractProducts();
            for (int i = 0; i < cps.size(); i++) {
                productsPrice += cps.get(i).getProduct().getPrice();
            }
            contract.setProductsPrice(productsPrice);
            (new PaymentFrm(contract)).setVisible(true);
            this.dispose();
        }
    }

    /**
     * @param args the command line arguments
     */
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private User user;
    private Contract contract;

    // End of variables declaration                   
}

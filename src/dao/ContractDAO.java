/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Contract;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.ContractProduct;
import model.Payment;

/**
 *
 * @author admin
 */
public class ContractDAO extends DAO {

    public ContractDAO() {
        super();
    }

    public boolean createContract(Contract contract) {
        String sqlAddContract = "INSERT INTO tblContract(tblUserId, tblCustomerId, tblPartnerId, createdDate, interestRate, totalPrice, duration,payCycle) VALUES(?,?,?,?,?,?,?,?)";
        String sqlAddPayment = "INSERT INTO tblPayment(date,amount,tblContractId)"
                + "VALUES(?,?,?)";
        String sqlAddContractProduct = "INSERT INTO tblContractProduct(quantity,price,tblProductId,tblContractId)"
                + "VALUES(?,?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        boolean result = true;

        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sqlAddContract, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, contract.getUser().getId());
            ps.setInt(2, contract.getCustomer().getId());
            ps.setInt(3, contract.getPartner().getId());
            ps.setString(4, sdf.format(contract.getCreatedDate()));
            ps.setFloat(5, contract.getInterestRate());
            ps.setFloat(6, contract.getTotalPrice());
            ps.setInt(7, contract.getDuration());
            ps.setInt(8, contract.getPayCycle());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                contract.setId(generatedKeys.getInt(1));
                for (ContractProduct cp : contract.getContractProducts()) {
                    ps = con.prepareStatement(sqlAddContractProduct);
                    ps.setInt(1, cp.getQuantity());
                    ps.setFloat(2, cp.getPrice());
                    ps.setInt(3, cp.getProduct().getId());
                    ps.setInt(4, contract.getId());
                    ps.executeUpdate();

                }
                for (Payment payment : contract.getPayments()) {
                    ps = con.prepareStatement(sqlAddPayment);
                    ps.setString(1, sdf.format(payment.getPaymentDate()));
                    ps.setFloat(2, payment.getAmount());
                    ps.setInt(3, contract.getId());
                    ps.executeUpdate();

                }
            }

            con.commit();//set this line into comment in JUnit test mode
        } catch (Exception e) {
            result = false;
            try {
                con.rollback();
            } catch (Exception ex) {
                result = false;
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                //con.setAutoCommit(true);//set this line into comment in JUnit test mode
            } catch (Exception ex) {
                result = false;
                ex.printStackTrace();
            }
        }
        return result;

    }
}

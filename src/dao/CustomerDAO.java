package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Customer;

public class CustomerDAO extends DAO {

    /**
     * search all customers in the tblCustomer whose name contains the @key
     *
     * @param key
     * @return list of customer whose name contains the @key
     */
    public ArrayList<Customer> searchByName(String key) {
        ArrayList<Customer> result = new ArrayList<>();
        String sql = "SELECT * FROM tblcustomer WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNo(rs.getString("phoneNo"));
                customer.setAddress(rs.getString("address"));
                customer.setDescription(rs.getString("description"));
                result.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO tblcustomer(name, email, address, phoneNo,  description) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getPhoneNo());
            ps.setString(5, customer.getDescription());

            ps.executeUpdate();

            //get id of the new inserted customer
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

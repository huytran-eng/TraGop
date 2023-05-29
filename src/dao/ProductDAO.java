package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Product;

public class ProductDAO extends DAO {

    /**
     * search all products in the tblCustomer whose name contains the @key
     *
     * @param key
     * @return list of product whose name contains the @key
     */
    public ArrayList<Product> searchByName(String key) {
        ArrayList<Product> result = new ArrayList<>();
        String sql = "SELECT * FROM tblproduct WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCalculationUnit(rs.getString("calculationUnit"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDescription(rs.getString("description"));
                result.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Partner;

public class PartnerDAO extends DAO {

    /**
     * search all partners in the tblPartner whose name contains the @key
     *
     * @param key
     * @return list of partner whose name contains the @key
     */
    public ArrayList<Partner> searchByName(String key) {
        ArrayList<Partner> result = new ArrayList<>();
        String sql = "SELECT * FROM tblpartner WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Partner partner = new Partner();
                partner.setId(rs.getInt("id"));
                partner.setName(rs.getString("name"));
                partner.setEmail(rs.getString("email"));
                partner.setPhoneNo(rs.getString("phoneNo"));
                partner.setAddress(rs.getString("address"));
                partner.setDescription(rs.getString("description"));
                result.add(partner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

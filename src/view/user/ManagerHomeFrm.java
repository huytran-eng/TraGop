package view.user;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.User;
import view.contract.SearchCustomerFrm;
import model.Contract;
import java.util.Date;
//import view.room.ManageRoomFrm;

public class ManagerHomeFrm extends JFrame implements ActionListener {

    private JButton btnCreateContract;
    private User user;

    public ManagerHomeFrm(User user) {
        super("Manager home");
        this.user = user;
        System.out.println(user);
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        JPanel lblPane = new JPanel();
        lblPane.setLayout(new BoxLayout(lblPane, BoxLayout.LINE_AXIS));
        lblPane.add(Box.createRigidArea(new Dimension(450, 0)));
        JLabel lblUser = new JLabel("Loged in as: " + user.getUsername());
        lblUser.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblPane.add(lblUser);
        listPane.add(lblPane);
        listPane.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblHome = new JLabel("Manager's home");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHome.setFont(lblHome.getFont().deriveFont(28.0f));
        listPane.add(lblHome);
        listPane.add(Box.createRigidArea(new Dimension(0, 20)));

        btnCreateContract = new JButton("Create Contract");
        btnCreateContract.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCreateContract.addActionListener(this);
        listPane.add(btnCreateContract);
        listPane.add(Box.createRigidArea(new Dimension(0, 10)));

        this.setSize(600, 300);
        this.setLocation(200, 10);
        this.add(listPane, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if ((e.getSource() instanceof JButton) && (((JButton) e.getSource()).equals(btnCreateContract))) {
            System.out.print("tao contracy");
            Contract contract = new Contract();
            contract.setUser(user);
            contract.setCreatedDate(new Date());
            (new SearchCustomerFrm(user, contract)).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "This function is under construction!");
        }
    }
}

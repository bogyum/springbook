package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // DB connection generation
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springtest?serverTimezone=UTC", "jarvis", "woalsdkQk3608!");

        // SQL statement generation
        PreparedStatement ps = c.prepareStatement( "insert into users(id, name, password) values(?, ?, ?)" );

        // Data insert
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        // execute
        ps.executeUpdate();

        // close;
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // DB connection generation
        Connection c = DriverManager.getConnection( "jdbc:mysql://localhost/springtest?serverTimezone=UTC", "jarvis", "woalsdkQk3608!");

        // SQL statement generation
        PreparedStatement ps = c.prepareStatement("Select * from users where id = ?");

        // Allocate variation
        ps.setString(1, id);

        // execute
        ResultSet rs = ps.executeQuery();
        rs.next();

        // Get data
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // close
        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("김보겸");
        user.setPassword("married");

        //dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");

    }

}

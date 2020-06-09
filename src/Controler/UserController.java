package Controler;

import Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private String url = "jdbc:mysql://localhost:3306/demo_linkedtosql";
    private String  userName = "root";
    private String password = "131071";
    private final String SELECT_USER_BY_ID = "SELECT * FROM userManager WHERE id = ?;";
    private final String SELECT_ALL_USER_BY_ID = "SELECT * FROM userManager;";
    private final String DELETE_USER_BY_ID = "DELETE FROM userManager WHERE id =?;";
    private final String FIND_USER_BY_NAME = "SELECT * FROM userManager WHERE name =?;";
    private final String UPDATE_USER_BY_NAME = "UPDATE usermanager SET name = ?, email = ?  WHERE id = ?;";
    private final String ADD_NEW_USER = "INSERT INTO demo_linkedtosql.usermanager"+ " (id, name, email) VALUES" +" (?, ?, ?);";
    private final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY = ? WHERE NAME =?";
    public final String SQL_TABLE_CREAT = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID serial,"
            + " NAME varchar(100) NOT NULL,"
            + " SALARY numeric(15, 2) NOT NULL,"
            + " CREATED_DATE timestamp,"
            + " PRIMARY KEY (ID)"
            + ");";
    public final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS EMPLOYEE;";

    public UserController() {
    }

    public Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("ok");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Not ok");
            e.printStackTrace();
        }
        return connection;
    }
    public User selectUserByID(String id){
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String newID = resultSet.getString("id");
                String newName = resultSet.getString("name");
                String newEmail = resultSet.getString("email");
                user = new User(newID, newName, newEmail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //Thuc hanh dung procedure trong JDBC
    public User selectUserByIDUseProcedure(String id){
        User user = null;
        String query = "CALL getUserByID (?)";
        try{
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1,id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                                                        user = new User(id, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User selectUserByName(String name){
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String newID = resultSet.getString("id");
                String newName = resultSet.getString("name");
                String newEmail = resultSet.getString("email");
                user = new User(newID, newName, newEmail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUser(){
        User user = null;
        List<User> myUser = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String newID = resultSet.getString("id");
                String newName = resultSet.getString("name");
                String newEmail = resultSet.getString("email");
                user = new User(newID, newName, newEmail);
                myUser.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myUser;
    }
    public void addNewUser(User user){
        String newId =  user.getId();
        String newName = user.getUsername();
        String newEmail = user.getMail();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER);
            preparedStatement.setString(1, newId);
            preparedStatement.setString(2, newName);
            preparedStatement.setString(3, newEmail);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUserByID(String id){
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editUserByID(User user){
        String id =  user.getId();
        String newName = user.getUsername();
        String newEmail = user.getMail();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_NAME);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editUserByIDUseJDBC(User user){
        String query = "CALL EditUserInformation(?, ?, ?)";
        String id = user.getId();
        String newName = user.getUsername();
        String newEmail = user.getMail();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, newName);
            preparedStatement.setString(3, newEmail);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertNewUserUseJDBC(User user){
        String query = "CALL insertNewUser(?, ?, ?)";
        String newID = user.getId();
        String newName = user.getUsername();
        String newEmail = user.getMail();
        try{
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setString(1,newID);
            callableStatement.setString(2,newName);
            callableStatement.setString(3,newEmail);
            callableStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<User> selectAllUserUseJDBC(){
        ArrayList<User> myList = new ArrayList<>();
        String query = "CALL showAllUser()";
        User user = null;
        try{
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet resulset = callableStatement.executeQuery();
            while (resulset.next()){
                String id = resulset.getString("id");
                String name = resulset.getString("name");
                String email = resulset.getString("email");
                user = new User(id, name, email);
                myList.add(user);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }
    public void insertNewUserWithTransaction() throws SQLException {

        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedstatementInsert = connection.prepareStatement(SQL_INSERT);
            PreparedStatement preparedstatementUpdate = connection.prepareStatement(SQL_UPDATE);
            statement.execute(SQL_TABLE_DROP);
            statement.execute(SQL_TABLE_CREAT);

            connection.setAutoCommit(false);

            preparedstatementInsert.setString(1, "Hoàng");
            preparedstatementInsert.setBigDecimal(2, new BigDecimal(10));
            preparedstatementInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedstatementInsert.execute();

            preparedstatementInsert.setString(1, "Linh");
            preparedstatementInsert.setBigDecimal(2, new BigDecimal(20));
            preparedstatementInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedstatementInsert.execute();

            preparedstatementUpdate.setString(1, "Hoàng Anh");
            preparedstatementUpdate.setBigDecimal(2, new BigDecimal(30));
            preparedstatementUpdate.execute();

            preparedstatementUpdate.setBigDecimal(2, new BigDecimal(30));
            preparedstatementUpdate.setString(2, "Hoàng Anh");
            preparedstatementUpdate.execute();



            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
//            if(connection != null){
//                connection.rollback();
//            }
        }
    }
    public void insertNewUserWithoutTransaction(){
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedstatementInsert = connection.prepareStatement(SQL_INSERT);
            PreparedStatement preparedstatementUpdate = connection.prepareStatement(SQL_UPDATE);
            statement.execute(SQL_TABLE_DROP);
            statement.execute(SQL_TABLE_CREAT);

            preparedstatementInsert.setString(1, "Hoàng");
            preparedstatementInsert.setBigDecimal(2, new BigDecimal(10));
            preparedstatementInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedstatementInsert.execute();

            preparedstatementInsert.setString(1, "Linh");
            preparedstatementInsert.setBigDecimal(2, new BigDecimal(20));
            preparedstatementInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedstatementInsert.execute();

            preparedstatementUpdate.setBigDecimal(2, new BigDecimal(30));
            preparedstatementUpdate.setString(2, "Hoàng Anh");
            preparedstatementUpdate.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

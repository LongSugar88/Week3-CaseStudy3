import Controler.UserController;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MyUserServlet", urlPatterns = "/myuser")
public class MyUserServlet extends HttpServlet {
    private UserController userController;
    public void init(){
        userController = new UserController();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "creat":
                addNewUser(request, response);
                break;
            case "edit":
                editUserUseJDBC(request, response);
                break;
            case "find":
                findUserByID(request, response);
                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "creat":
                formAddNewUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "edit":
                formEditUser(request, response);
                break;
            case "find":
                formFindUser(request, response);
                break;
            case "withoutTransaction":
                testInsertWithoutTransaction(request, response);
                break;
            case "withTransaction":
                try {
                    testInsertWithTransaction(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                showAllUserUseJDBC(request, response);
                break;
        }

    }
    public void showAllUser(HttpServletRequest request, HttpServletResponse response){
        List<User> myUser = userController.selectAllUser();
        request.setAttribute("myUser", myUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/List.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showAllUserUseJDBC(HttpServletRequest request, HttpServletResponse response){
        List<User> myUser = userController.selectAllUserUseJDBC();
        request.setAttribute("myUser", myUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/List.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addNewUser(HttpServletRequest request, HttpServletResponse response){
        String newId = request.getParameter("newID");
        String newName = request.getParameter("newName");
        String newEmail = request.getParameter("mail");
        User user = new User(newId, newName, newEmail);
        userController.insertNewUserUseJDBC(user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/addNewUser.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void formAddNewUser(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/addNewUser.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void formEditUser(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        User user = userController.selectUserByID(id);
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/editUserName.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void formFindUser(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/findUserByID.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        userController.deleteUserByID(id);
        showAllUser(request, response);
    }
    public void editUser(HttpServletRequest request, HttpServletResponse response){
        String newID = request.getParameter("editID");
        String newName = request.getParameter("editName");
        String newEmail = request.getParameter("editEmail");
        User user = new User(newID, newName, newEmail);
        userController.editUserByID(user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/editUserName.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void editUserUseJDBC (HttpServletRequest request, HttpServletResponse response){
        String newID = request.getParameter("editID");
        String newName = request.getParameter("editName");
        String newEmail = request.getParameter("editEmail");
        User user = new User(newID, newName, newEmail);
        userController.editUserByIDUseJDBC(user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/editUserName.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void findUserByID(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("findID");
        User user = userController.selectUserByIDUseProcedure(id);
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("View/findUserByID.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void testInsertWithoutTransaction(HttpServletRequest request, HttpServletResponse response){
        userController.insertNewUserWithoutTransaction();
    }
    public void testInsertWithTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        userController.insertNewUserWithTransaction();
    }
}

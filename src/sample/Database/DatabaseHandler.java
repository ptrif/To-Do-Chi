package sample.Database;

import sample.mods.Task;
import sample.mods.User;
import java.sql.*;

public class DatabaseHandler extends Configurations {

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost
                + ":" + dbPort
                + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    //write

    public void signUpUser(User user) {
        String insert = "INSERT INTO" + " " + Const.USERS_TABLE
                + "(" + Const.USERS_FULLNAME
                + "," + Const.USERS_USERNAME
                + "," + Const.USERS_PASSWORD
                + "," + Const.USERS_GENDER
                + ")"
                + "Values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getGender());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "select * from users where username=? and password=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else
            System.out.println("Please, enter your information");

        return resultSet;
    }

    public void loadUpTask(Task task) {
        String insert = "INSERT INTO" + " " + Const.TASKS_TABLE
                + "(" + Const.USERS_ID
                + "," + Const.TASKS_DATE
                + "," + Const.TASKS_DESCRIPTION
                + "," + Const.TASKS_TASK
                + ")"
                + "Values(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setTimestamp(2, task.getDatecreated());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getTask());

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getTasksByUserId(int userId) {

        ResultSet resultTasks = null;

        String query = "select * from tasks where userid=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);

            resultTasks = preparedStatement.executeQuery();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return resultTasks;
    }

    public ResultSet getTask(Task task) {
        ResultSet resultSet = null;

        if (!task.getTask().equals("") || !task.getDescription().equals("")) {
            String query = "select * from" + " " + Const.TASKS_TABLE
                    + " where " + Const.TASKS_TASK + "=?"
                    + " and " + Const.TASKS_DESCRIPTION + "=?"
                    + " and " + Const.TASKS_DATE + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, task.getTask());
                preparedStatement.setString(2, task.getDescription());
                preparedStatement.setTimestamp(3, task.getDatecreated());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else
            System.out.println("Please, enter your task");

        return resultSet;
    }


    //delete task
    public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM tasks WHERE userid=? AND taskid=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, taskId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    //edit task


}

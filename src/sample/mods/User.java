package sample.mods;

public class User {
    private String fullName;
    private String userName;
    private String password;
    private String gender;

    public User() {
    }

    public User(String fullName, String userName, String password, String gender) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

}

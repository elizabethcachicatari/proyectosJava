package pe.escuelaconductores.clases;

import java.time.LocalDate;

public class User {
    private String user;
    private String password;
    private String fullName;
    private LocalDate dateRegister;

    public User() {
    }
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public User(String user, String password, String fullName, LocalDate dateRegister) {
        this.user = user;
        this.password = password;
        this.fullName = fullName;
        this.dateRegister = dateRegister;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateRegister=" + dateRegister +
                '}';
    }
}

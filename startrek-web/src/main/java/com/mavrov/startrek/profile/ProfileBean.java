package com.mavrov.startrek.profile;

import javax.faces.bean.RequestScoped;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class ProfileBean {

    private String result;
    private String login;
    private String password;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result + " SUCCESS!";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
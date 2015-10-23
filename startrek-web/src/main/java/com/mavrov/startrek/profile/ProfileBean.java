package com.mavrov.startrek.profile;

import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * @author serg.mavrov@gmail.com
 */
@RequestScoped
public class ProfileBean implements Serializable {

    private String result;
    private String login;
    private String password;

    private String name1;
    private String name2;
    private String position;
    private String companyName;

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

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
package com.mavrov.startrek.profile;

import com.mavrov.entity.ProfileEntity;
import com.mavrov.repository.ProfileRepository;
import org.jboss.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author serg.mavrov@gmail.com
 */
@ManagedBean(name = "profileBean")
@SessionScoped
public class ProfileBean implements Serializable {

    @Inject
    private transient Logger logger;

    @Inject
    private ProfileRepository profileRepo;

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

    public void load() {
        ProfileEntity dbProfile = profileRepo.findByEmail(login);
        if (dbProfile != null) {
            dbProfile = new ProfileEntity();
            setName1(dbProfile.getName1());
            setName2(dbProfile.getName2());
            setPosition(dbProfile.getPosition());
            setCompanyName(dbProfile.getCompanyName());
        } else {
            setName1("");
            setName2("");
            setPosition("");
            setCompanyName("");
        }
    }

    public void save() {
        ProfileEntity dbProfile = profileRepo.findByEmail(getLogin());
        if (dbProfile == null) {
            dbProfile = new ProfileEntity(
                    getLogin(),
                    getName1(),
                    getName2(),
                    getPosition(),
                    getCompanyName()
            );
            profileRepo.create(dbProfile);
        } else {
            dbProfile.setName1(getName1());
            dbProfile.setName2(getName2());
            dbProfile.setPosition(getPosition());
            dbProfile.setCompanyName(getCompanyName());
            profileRepo.update(dbProfile);
        }
    }

}
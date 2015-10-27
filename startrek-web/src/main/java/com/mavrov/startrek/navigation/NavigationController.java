package com.mavrov.startrek.navigation;

import com.mavrov.startrek.profile.ProfileBean;
import org.jboss.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author serg.mavrov@gmail.com
 */
@ManagedBean(name = "navigationController")
@SessionScoped
public class NavigationController implements Serializable {

    private static final long serialVersionUID = 3757861024867792981L;

    @Inject
    private transient Logger logger;

    @ManagedProperty(value = "#{profileBean}")
    private ProfileBean profileBean;

    public String showPageAfterLogin(String email) {
        logger.info(">>>>>");
        String param = email.isEmpty() ? "empty" : "not empty";
        logger.info("email parameter is " + param);
        logger.info("email parameter=<" + email + ">");
        logger.info("<<<<<");

        profileBean.setLogin(email);

        if (email.isEmpty()) {
            return "profile-error";
        }

        if (profileBean.load()) {
            return "profile-view";
        } else {
            return "profile-edit";
        }
    }

    public String saveCurrentProfile() {
        profileBean.save();
        return "profile-save";
    }

    public ProfileBean getProfileBean() {
        return profileBean;
    }

    public void setProfileBean(ProfileBean profileBean) {
        this.profileBean = profileBean;
    }

}

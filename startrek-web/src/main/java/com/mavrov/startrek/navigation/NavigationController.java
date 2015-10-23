package com.mavrov.startrek.navigation;

import com.mavrov.entity.ProfileEntity;
import com.mavrov.repository.ProfileRepository;
import com.mavrov.startrek.profile.ProfileBean;
import org.jboss.logging.Logger;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author serg.mavrov@gmail.com
 */
@SessionScoped
public class NavigationController implements Serializable {

    @Inject
    private transient Logger logger;

    @Inject
    private ProfileRepository profileRepo;

    @ManagedProperty(value = "#{profileBean}")
    private ProfileBean currentProfile;

//    public String showPage() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
//        String email = parameters.get("email");
//        return showPageAfterLogin(email);
//    }

    public String showPageAfterLogin(String email) {
        logger.info(">>>>>");
        String param = email.isEmpty() ? "empty" : "not empty";
        logger.info("email parameter is " + param);
        logger.info("email parameter=<" + email + ">");
        logger.info("<<<<<");
        if (!email.isEmpty() && profileRepo.findByEmail(email) != null) {
            ProfileEntity dbProfile = profileRepo.findByEmail(email);
            //-=-=-
            currentProfile.setName1(dbProfile.getName1());
            currentProfile.setName2(dbProfile.getName2());
            currentProfile.setPosition(dbProfile.getPosition());
            currentProfile.setCompanyName(dbProfile.getCompanyName());
            return "profile-view";
        } else {
            return "profile-edit";
        }
    }
}

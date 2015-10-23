package com.mavrov.startrek.navigation;

import com.mavrov.repository.ProfileRepository;
import org.jboss.logging.Logger;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

/**
 * @author serg.mavrov@gmail.com
 */
@SessionScoped
public class NavigationController implements Serializable {

    @Inject
    private transient Logger logger;

    @Inject
    private ProfileRepository profileRepo;

    public String showPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        String email = parameters.get("email");
        return showPage(email);
    }

    public String showPage(String email) {
        logger.info(">>>>>");
        String param = email.isEmpty() ? "empty" : "not empty";
        logger.info("email parameter is " + param);
        logger.info("email parameter=<" + email + ">");
        logger.info("<<<<<");
        if (!email.isEmpty() && profileRepo.findByEmail(email) != null) {
            return "profile-view";
        } else {
            return "profile-edit";
        }
    }
}

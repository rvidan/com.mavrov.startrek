package com.mavrov.startrek.navigation;

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

    public String showPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        String email = parameters.get("email");
        return showPage(email);
    }

    public String showPage(String email) {
        logger.info("email parameter=" + email);
        if (!email.isEmpty()) {
            return "profile-view";
        } else {
            return "profile-edit";
        }
    }
}

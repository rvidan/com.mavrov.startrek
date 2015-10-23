package com.mavrov.startrek.navigation;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

/**
 * @author serg.mavrov@gmail.com
 */
@SessionScoped
public class NavigationController implements Serializable {

    public String showPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        String email = parameters.get("email");
        if (!email.isEmpty()) {
            return "profile-view";
        } else {
            return "profile-edit";
        }
    }

}

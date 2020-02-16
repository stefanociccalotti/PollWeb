package it.univaq.disim.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

public class SecurityLayer {

    public static HttpSession createSession(HttpServletRequest request, String username,String type, Integer userid){

        HttpSession s = request.getSession(true);

        s.setAttribute("userID",userid);
        s.setAttribute("user",username);
        s.setAttribute("type",type);
        s.setAttribute("ip", request.getRemoteHost());
        s.setAttribute("inizio-sessione", Calendar.getInstance());
        s.setMaxInactiveInterval(20*60);
        return s;
    }
    public static void disposeSession(HttpServletRequest request) {
        HttpSession s = request.getSession(false);
        if (s != null) {
            s.invalidate();
        }
    }
}

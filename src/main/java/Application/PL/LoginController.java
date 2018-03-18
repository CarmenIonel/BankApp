package Application.PL;

import Application.DL.Model.Record;
import Application.DL.Model.User;
import Application.DL.Repository.RecordRepository;
import Application.DL.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Ionel Carmen on 4/7/2017.
 */
@Controller
public class LoginController
{

    public static int idLog=-1;

    @Autowired
    private UserRepository ur;
    @Autowired
    private RecordRepository rr;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getUserLog1(HttpServletRequest request)
    {
        return "/login";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String getUserLog(HttpServletRequest request)
    {
        System.out.print(request.getParameter("username"));
        User u=ur.findByUsername(request.getParameter("username"));
        String pass= request.getParameter("password");
        System.out.print(u.getAdmin());
        System.out.print(u.getAdmin().equals("admin"));
        System.out.print(pass);
        if(!(pass.equals(u.getPassword())))
            return "redirect:/login";
        if(u.getAdmin().equals("admin"))
            return "redirect:/adminMeniu";
        if(u.getAdmin().equals("employee"))
        {
            if(idLog!=-1) {
                Record r = new Record(new Date(), "Log out");
                User us = ur.findOne(idLog);
                r.setUser(us);
                rr.save(r);
            }
            idLog = u.getId();
            Record r = new Record(new Date(), "Log in");
            User user = ur.findOne(idLog);
            r.setUser(user);
            rr.save(r);
            return "redirect:/employeeMeniu";

        }
        else  return "redirect:/login";
    }
}
package Application.PL;

import Application.BL.Exception.UserNotFound;
import Application.DL.Model.Record;
import Application.DL.Model.User;
import Application.DL.Repository.RecordRepository;
import Application.DL.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by Ionel Carmen on 4/3/2017.
 */
@Controller
public class UserController
{
    @Autowired
    private UserRepository ur;

    @Autowired
    private RecordRepository rr;

    //creare angajat
    @RequestMapping(value="/createEmployee", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/createEmployee";
    }
    @RequestMapping(path="/createEmployee", method= RequestMethod.POST)
    public String addUser(HttpServletRequest ht)throws Exception
    {
        User user=null;
        if(ur.findByUsername(ht.getParameter("name"))==null) {
            user = new User(ht.getParameter("name"), ht.getParameter("address"), ht.getParameter("email"), ht.getParameter("phone"),
                    ht.getParameter("username"), ht.getParameter("password"));
            ur.save(user);
        }
        else
            throw new Exception();

        return "/adminMeniu";
    }

    //update angajat
    @RequestMapping(value="/updateEmployee", method= RequestMethod.GET)
    public String u(HttpServletRequest ht)
    {
        return "/updateEmployee";
    }
    @RequestMapping(path="/updateEmployee", method= RequestMethod.POST)
    public String updateUser(HttpServletRequest ht) throws Exception
    {
        User user=ur.findOne(Integer.parseInt(ht.getParameter("id")));
        if(user==null)
            throw new UserNotFound();
        if(ht.getParameter("name")!="")
            user.setName(ht.getParameter("name"));
        if(ht.getParameter("address")!="")
            user.setAddress(ht.getParameter("address"));
        if(ht.getParameter("email")!="")
            user.setEmail(ht.getParameter("email"));
        if(ht.getParameter("phone")!="")
            user.setPhone(ht.getParameter("phone"));
        if(ht.getParameter("password")!="")
            user.setPassword(ht.getParameter("password"));
        if(ht.getParameter("username")!="")
            user.setUsername(ht.getParameter("username"));

        ur.save(user);
        return "/adminMeniu";
    }

    //delete
    @RequestMapping(value="/deleteEmployee", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {
        return "/deleteEmployee";
    }
    @RequestMapping(path="/deleteEmployee", method= RequestMethod.POST)
    public String deleteUser(HttpServletRequest ht)throws Exception
    {
        User user=ur.findOne(Integer.parseInt(ht.getParameter("id")));
        if(user==null)
            throw new UserNotFound();
        ur.delete(user);
        return "/adminMeniu";
    }


    //report
    @RequestMapping(path="/{idu}/getReport", method= RequestMethod.GET)
    public String g(@PathVariable int idu, Model m)
    {
        User u=ur.findOne(idu);
        List<Record> r =rr.findByU(u);
        m.addAttribute("report", r);
        return "/getReport";
    }

    @RequestMapping(value="/getReport", method= RequestMethod.GET)
    public String g(HttpServletRequest ht)
    {
        return "/getReport";
    }

    @RequestMapping(value = "/getReport", method= RequestMethod.POST)
    public String get(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        int id=Integer.parseInt(request.getParameter("id"));
        User u=ur.findOne(id);
        if(u==null)
            throw new UserNotFound();
        int idu=u.getId();
        return "redirect:/"+idu+"/getReport";
    }

    //view employee
    @RequestMapping(path="/{id}/viewEmployee", method= RequestMethod.GET)
    public String e(@PathVariable int id, Model m)
    {
        User u=ur.findOne(id);
        m.addAttribute("employee", u);
        return "/viewEmployee";
    }

    @RequestMapping(value="/viewEmployee", method= RequestMethod.GET)
    public String em(HttpServletRequest ht)
    {
        return "/viewEmployee";
    }

    @RequestMapping(value = "/viewEmployee", method= RequestMethod.POST)
    public String emp(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        int id=Integer.parseInt(request.getParameter("id"));
        User u=ur.findOne(id);
        if(u==null)
            throw new UserNotFound();
        return "redirect:/"+u.getId()+"/viewEmployee";
    }

    @ExceptionHandler(UserNotFound.class)
    public String handleClientNot(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Employee not founded!";
        m.addAttribute("text", x);
        return "/error";
    }

    @ExceptionHandler(Exception.class)
    public String handle(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Employee exists!";
        m.addAttribute("text", x);
        return "/error";
    }
}

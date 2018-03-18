package Application.PL;

import Application.BL.Exception.ClientNotFound;
import Application.DL.Model.Account;
import Application.DL.Model.Client;
import Application.DL.Model.Record;
import Application.DL.Model.User;
import Application.DL.Repository.AccountRepository;
import Application.DL.Repository.ClientRepository;
import Application.DL.Repository.RecordRepository;
import Application.DL.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Ionel Carmen on 4/4/2017.
 */
@Controller
public class ClientController
{
    @Autowired
    private ClientRepository cr;

    @Autowired
    private AccountRepository ar;

    @Autowired
    private RecordRepository rr;

    @Autowired
    private UserRepository ur;

    //create client si cont
    @RequestMapping(value="/createClientAccount", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/createClientAccount";
    }
    @RequestMapping(path="/createClientAccount", method= RequestMethod.POST)
    public String addCA(HttpServletRequest ht)throws Exception
    {
        if(cr.findByName(ht.getParameter("name"))==null)
        {
            Account a = new Account(Double.parseDouble(ht.getParameter("suma")), ht.getParameter("card"));
            Client c = new Client(ht.getParameter("name"), ht.getParameter("address"), ht.getParameter("phone"),
                    ht.getParameter("email"), a);
            cr.save(c);
            ar.save(a);
            Record r = new Record(new Date(), "Create client and accound");
            User u=ur.findOne(LoginController.idLog);
            r.setUser(u);
            rr.save(r);
        }
        else
            throw new Exception();
        return "/employeeMeniu";
    }

    //update client
    @RequestMapping(value="/updateClient", method= RequestMethod.GET)
    public String u(HttpServletRequest ht)
    {
        return "/updateClient";
    }
    @RequestMapping(path="/updateClient", method= RequestMethod.POST)
    public String updateClient(HttpServletRequest ht) throws Exception
    {
        Client c=cr.findOne(Integer.parseInt(ht.getParameter("id")));
        if(c==null)
            throw new ClientNotFound();
        if(ht.getParameter("name")!="")
            c.setName(ht.getParameter("name"));
        if(ht.getParameter("address")!="")
            c.setAddress(ht.getParameter("address"));
        if(ht.getParameter("email")!="")
            c.setEmail(ht.getParameter("email"));
        if(ht.getParameter("phone")!="")
            c.setPhone(ht.getParameter("phone"));
        cr.save(c);
        Record r=new Record(new Date(),"Update Client");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "/employeeMeniu";
    }

    //delete client
    @RequestMapping(value="/deleteClient", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {
        return "/deleteClient";
    }
    @RequestMapping(path="/deleteClient", method= RequestMethod.POST)
    public String deleteClient(HttpServletRequest ht)throws Exception
    {
        Client c=cr.findOne(Integer.parseInt(ht.getParameter("id")));
        if(c==null)
            throw new ClientNotFound();
        Account a=c.getA();
        cr.delete(c);
        ar.delete(a);
        Record r=new Record(new Date(),"Delete Client");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "/employeeMeniu";
    }

    //view client
    @RequestMapping(path="/{id}/viewClient", method= RequestMethod.GET)
    public String e(@PathVariable int id, Model m)
    {
        Client c=cr.findOne(id);
        m.addAttribute("client", c);
        return "/viewClient";
    }

    @RequestMapping(value="/viewClient", method= RequestMethod.GET)
    public String em(HttpServletRequest ht)
    {
        return "/viewClient";
    }

    @RequestMapping(value = "/viewClient", method= RequestMethod.POST)
    public String emp(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        int id=Integer.parseInt(request.getParameter("id"));
        Client c=cr.findOne(id);
        if(c==null)
            throw new ClientNotFound();
        Record r=new Record(new Date(),"View Client");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "redirect:/"+c.getId()+"/viewClient";
    }

    @ExceptionHandler(ClientNotFound.class)
    public String handleClientNot(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Client not founded!";
        m.addAttribute("text", x);
        return "/error";
    }
    @ExceptionHandler(Exception.class)
    public String handle(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Client exists!";
        m.addAttribute("text", x);
        return "/error";
    }
}


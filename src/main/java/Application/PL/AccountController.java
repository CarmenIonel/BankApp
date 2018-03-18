package Application.PL;

/**
 * Created by Ionel Carmen on 4/4/2017.
 */

import Application.BL.Exception.AccountNotFound;
import Application.DL.Model.Account;
import Application.DL.Model.Record;
import Application.DL.Model.User;
import Application.DL.Repository.AccountRepository;
import Application.DL.Repository.RecordRepository;
import Application.DL.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AccountController
{
    @Autowired
    private AccountRepository ar;

    @Autowired
    private RecordRepository rr;

    @Autowired
    private UserRepository ur;

    //update cont
    @RequestMapping(value="/updateAccount", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/updateAccount";
    }
    @RequestMapping(path="/updateAccount", method= RequestMethod.POST)
    public String updateAccount(HttpServletRequest ht) throws Exception
    {
        Account a=ar.findOne(Integer.parseInt(ht.getParameter("id")));
        if(a==null)
            throw new AccountNotFound();
        if(ht.getParameter("card")!="")
            a.setCardNumber(ht.getParameter("card"));
        if(ht.getParameter("suma")!="")
            a.setSumAcc(Double.parseDouble(ht.getParameter("suma")));
        ar.save(a);
        Record r=new Record(new Date(),"Update Account");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "/employeeMeniu";
    }

    //delete cont
    @RequestMapping(value="/deleteAccount", method= RequestMethod.GET)
    public String d(HttpServletRequest ht)
    {
        return "/deleteAccount";
    }
    @RequestMapping(path="/deleteAccount", method= RequestMethod.POST)
    public String deleteAcc(HttpServletRequest ht)throws Exception
    {
        Account a=ar.findOne(Integer.parseInt(ht.getParameter("id")));
        if(a==null)
            throw new AccountNotFound();
        ar.delete(a);
        Record r=new Record(new Date(),"Delete Account");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "employeeMeniu";
    }

    //transfer
    @RequestMapping(value="/transfer", method= RequestMethod.GET)
    public String t(HttpServletRequest ht)
    {
        return "/transfer";
    }
    @RequestMapping(path="/transfer", method= RequestMethod.POST)
    public String transf(HttpServletRequest ht)throws Exception
    {
        int id1,id2;
        double suma;
        id1=Integer.parseInt(ht.getParameter("id1"));
        id2=Integer.parseInt(ht.getParameter("id2"));
        suma=Double.parseDouble(ht.getParameter("suma"));

        Account a=ar.findOne(id1);
        Account b=ar.findOne(id2);
        if(a.getSumAcc()<suma)
            throw new Exception();
        a.setSumAcc(a.getSumAcc()-suma);
        b.setSumAcc(b.getSumAcc()+suma);

        ar.save(a);
        ar.save(b);
        Record r=new Record(new Date(),"Transfer");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "/employeeMeniu";
    }

    //view account
    @RequestMapping(path="/{id}/viewAccount", method= RequestMethod.GET)
    public String e(@PathVariable int id, Model m)
    {
        Account a=ar.findOne(id);
        m.addAttribute("account", a);
        return "/viewAccount";
    }

    @RequestMapping(value="/viewAccount", method= RequestMethod.GET)
    public String em(HttpServletRequest ht)
    {
        return "/viewAccount";
    }

    @RequestMapping(value = "/viewAccount", method= RequestMethod.POST)
    public String emp(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        int id=Integer.parseInt(request.getParameter("id"));
        Account a=ar.findOne(id);
        if(a==null)
            throw new AccountNotFound();
        Record r=new Record(new Date(),"View Account");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);
        return "redirect:/"+a.getId()+"/viewAccount";
    }

    @ExceptionHandler(AccountNotFound.class)
    public String handleClientNot(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Account not founded!";
        m.addAttribute("text", x);
        return "/error";
    }

    @ExceptionHandler(Exception.class)
    public String handle(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Insuficient sold!";
        m.addAttribute("text", x);
        return "/error";
    }
}

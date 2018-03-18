package Application.PL;

import Application.DL.Model.*;
import Application.DL.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Ionel Carmen on 4/4/2017.
 */
@Controller
public class BillController
{
    @Autowired
    private BillRepository br;

    @Autowired
    private AccountRepository ar;

    @Autowired
    private ClientRepository cr;

    @Autowired
    private UserRepository ur;

    @Autowired
    private RecordRepository rr;
    //payBill
    @RequestMapping(value="/payBill", method= RequestMethod.GET)
    public String c(HttpServletRequest ht)
    {
        return "/payBill";
    }
    @RequestMapping(path="/payBill", method= RequestMethod.POST)
    public String pay(HttpServletRequest ht) throws Exception
    {
        Bill b=br.findOne(Integer.parseInt(ht.getParameter("idb")));
        Account accB=b.getA();
        Client c=cr.findOne(Integer.parseInt(ht.getParameter("idc")));
        Account accC=c.getA();
        double suma=Double.parseDouble(ht.getParameter("sum"));

        if(accC.getSumAcc()<suma)
            throw new Exception();
        accB.setSumAcc(accB.getSumAcc()+suma);
        accC.setSumAcc(accC.getSumAcc()-suma);

        b.setPayed("true");
        ar.save(accB);
        ar.save(accC);

        Record r=new Record(new Date(),"Pay Bill");
        User u=ur.findOne(LoginController.idLog);
        r.setUser(u);
        rr.save(r);

        return "/employeeMeniu";
    }

    @ExceptionHandler(Exception.class)
    public String handleClientNot(HttpServletRequest request, Exception ex, Model m)
    {
        String x="Insuficient sold!";
        m.addAttribute("text", x);
        return "/error";
    }
}

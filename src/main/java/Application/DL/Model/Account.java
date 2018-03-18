package Application.DL.Model;

/**
 * Created by Ionel Carmen on 3/29/2017.
 */
import java.text.*;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="account")
public class Account
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private double sumAcc;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String dataCreation;

    public Account()
    {

    }
    public Account(double sum, String card)
    {
        this.sumAcc=sum;
        this.cardNumber=card;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date= new Date();
        dataCreation=dateFormat.format(date);
    }

    public int getId()
    {
        return id;
    }

    public double getSumAcc()
    {
        return sumAcc;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public String getDataCreation()
    {
        return dataCreation;
    }
    public void setSumAcc(double sumAcc)
    {
        this.sumAcc = sumAcc;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public void setDataCreation(String dataCreation)
    {
        this.dataCreation = dataCreation;
    }

}
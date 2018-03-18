package Application.DL.Model;

/**
 * Created by Ionel Carmen on 3/29/2017.
 */
import javax.persistence.*;

@Entity
@Table(name="bill")
public class Bill
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(nullable = false, name="name")
    private String name;
    @Column(nullable = false, name="suma")
    private double sumB;
    @Column(nullable = false, name="payed")
    private String payed;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAccount")
    private Account a;

    public Bill()
    {

    }
    public Bill(String name, double sum)
    {
        this.name=name;
        this.sumB=sum;
        payed="false";
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getSumB()
    {
        return sumB;
    }

    public String isPayed()
    {
        return payed;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSumB(double sumB)
    {
        this.sumB = sumB;
    }

    public void setPayed(String payed)
    {
        this.payed = payed;
    }

    public Account getA() {
        return a;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setA(Account a)
    {
        this.a = a;
    }

}
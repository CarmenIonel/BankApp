package Application.DL.Model;
import javax.persistence.*;
/**
 * Created by Ionel Carmen on 3/29/2017.
 */
@Entity
@Table(name="client")
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAcc")
    private Account a;

    public Client()
    {

    }
    public Client(String name, String address, String phone, String email, Account a)
    {
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.a=a;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getA() {
        return a;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setA(Account a) {
        this.a = a;
    }

}
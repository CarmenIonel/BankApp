package Application.DL.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ionel Carmen on 4/1/2017.
 */
@Entity
@Table(name="record")
public class Record
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private Date data;
    @Column(nullable = false)
    private String record;

    @OneToOne
    private User u;

    public Record()
    {

    }
    public Record(Date data, String record)
    {
        this.data=data;
        this.record=record;
    }
    public int getID()
    {
        return id;
    }
    public Date getData()
    {
        return data;
    }
    public String getRecord()
    {
        return record;
    }
    public void setID(int id)
    {
        this.id=id;
    }
    public void setData(Date data)
    {
        this.data=data;
    }
    public void setRecord(String record)
    {
        this.record=record;
    }
    public void setUser(User u)
    {
        this.u=u;
    }
}
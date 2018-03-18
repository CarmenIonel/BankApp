package Application.DL.Model;

/**
 * Created by Ionel Carmen on 3/29/2017.
 */
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user")
public class User
{
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private int id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false)
        private String address;
        @Column(nullable = false)
        private String email;
        @Column(nullable = false)
        private String phone;
        @Column(nullable = false)
        private String username;
        @Column(nullable = false)
        private String password;
        @Column(nullable = false)
        private String admin;

        public User()
        {

        }
        public User(String name, String address, String email, String phone, String username, String password)
        {
                this.name=name;
                this.address=address;
                this.email=email;
                this.phone=phone;
                this.username=username;
                this.password=password;
                this.admin="employee";
        }
        public int getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public String getAddress()
        {
            return address;
        }

        public String getEmail()
        {
            return email;
        }

        public String getPhone()
        {
            return phone;
        }

        public String getUsername()
        {
            return username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public String getAdmin() {return this.admin;}

        public void setAdmin() {this.admin="admin";}
}

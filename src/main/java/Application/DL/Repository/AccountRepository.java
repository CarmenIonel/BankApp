package Application.DL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ionel Carmen on 3/29/2017.
 */
import Application.DL.Model.Account;
public interface AccountRepository extends JpaRepository<Account,Integer>
{

}

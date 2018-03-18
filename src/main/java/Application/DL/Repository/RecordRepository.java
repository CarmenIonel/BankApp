package Application.DL.Repository;

import Application.DL.Model.Record;
import Application.DL.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ionel Carmen on 4/1/2017.
 */
public interface RecordRepository extends JpaRepository<Record,Integer>
{
    public List<Record> findByU(User u);
}

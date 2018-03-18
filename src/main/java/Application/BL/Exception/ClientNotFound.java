package Application.BL.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ionel Carmen on 3/29/2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Client Not Found")
public class ClientNotFound extends Exception
{

}

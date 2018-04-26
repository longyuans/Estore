import com.estore.estoreEnum.ErrorCodeEnum;
import com.estore.utils.EstoreException;

public class ExceptionTest {
    public static void main(String[] args) throws EstoreException {
        System.out.println("start");
        throw new EstoreException(ErrorCodeEnum.generalError.toString(),"内部错误");
    }
}

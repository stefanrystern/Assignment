import static org.junit.Assert.assertEquals;


import org.junit.Ignore;
import org.junit.Test;

import com.google.java.contract.PreconditionError;
import com.google.java.contract.PostconditionError;
import com.google.java.contract.InvariantError;

//@Ignore
//@Test(expected = PreconditionError.class)
//@Test(expected = PostconditionError.class)

public class ProgramTest {
   
	//@Ignore
	@Test (expected = PreconditionError.class)
    public void test1() {
    	PasswordCriteria cr = new PasswordCriteria();
    	cr.setMinLength(3);
    }
    
	//@Ignore
    @Test (expected = InvariantError.class)
    public void test2() {
    	PasswordCriteria cr = new PasswordCriteria();
    	cr.setMaxLength(3);
    }
    
    
    
}

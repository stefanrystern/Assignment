import org.junit.Test;

import com.google.java.contract.InvariantError;
import com.google.java.contract.PreconditionError;


public class ProgramTest {
   
	@Test (expected = PreconditionError.class)
    public void test1() {
    	PasswordCriteria cr = new PasswordCriteria();
    	cr.setMinLength(3);
    }
    
    @Test (expected = InvariantError.class)
    public void test2() {
    	PasswordCriteria cr = new PasswordCriteria();
    	cr.setMaxLength(3);
    }
    
	@Test(expected = InvariantError.class)
	public void hasMixedCase_hasNoLetters() {
		PasswordCriteria cr = new PasswordCriteria();
		cr.setHasLetters(false);
		cr.setHasMixedCase(true);
	}

	public void hasMixedCase_hasLetters() {
		PasswordCriteria cr = new PasswordCriteria();
		cr.setHasMixedCase(true);
	}

	public void hasNumbers_hasLetters() {
		PasswordCriteria cr = new PasswordCriteria();
		cr.setHasNumbers(true);
	}

	public void hasNumbers_hasNoLetters() {
		PasswordCriteria cr = new PasswordCriteria();
		cr.setHasNumbers(true);
		cr.setHasLetters(false);
	}

	@Test(expected = InvariantError.class)
	public void hasNoNumbers_hasNoLetters() {
		PasswordCriteria cr = new PasswordCriteria();
		cr.setHasNumbers(false);
		cr.setHasLetters(false);
	}
    
}

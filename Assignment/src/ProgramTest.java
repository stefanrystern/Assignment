import org.junit.Before;
import org.junit.Test;

import com.google.java.contract.InvariantError;
import com.google.java.contract.PreconditionError;


public class ProgramTest {

    PasswordCriteria cr;

    @Before
    public void setUp() throws Exception {
    	cr = new PasswordCriteria();
    }

    @Test (expected = PreconditionError.class)
    public void testSetMinLength() {
    	cr.setMinLength(3);
    }

    @Test(expected = PreconditionError.class)
    public void testSetMaxLength() throws Exception {
        cr.setMaxLength(2);
    }

	@Test
	public void testSetMinLengthAllowed() throws Exception {
		cr.setMinLength(5);
	}

	@Test
	public void testSetMaxLengthAllowed() throws Exception {
		cr.setMaxLength(10);
	}

	@Test(expected = InvariantError.class)
	public void hasMixedCase_hasNoLetters() {
		cr.setHasLetters(false);
		cr.setHasMixedCase(true);
	}

	@Test
	public void hasMixedCase_hasLetters() {
		cr.setHasMixedCase(true);
	}

	@Test
	public void hasNumbers_hasLetters() {
		cr.setHasNumbers(true);
	}

	@Test
	public void hasNumbers_hasNoLetters() {
		cr.setHasNumbers(true);
		cr.setHasLetters(false);
	}

	@Test(expected = InvariantError.class)
	public void hasNoNumbers_hasNoLetters() {
		cr.setHasNumbers(false);
		cr.setHasLetters(false);
	}

}

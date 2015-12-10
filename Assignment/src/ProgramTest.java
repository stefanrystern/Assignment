import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.java.contract.InvariantError;
import com.google.java.contract.PostconditionError;
import com.google.java.contract.PreconditionError;


public class ProgramTest {

    PasswordCriteria cr;

    @Before
    public void setUp() throws Exception {
        cr = new PasswordCriteria();
    }

    @Test(expected = PreconditionError.class)
    public void testSetMinLength() {
        cr.setMinLength(3);
    }

    @Test(expected = PreconditionError.class)
    public void testSetMaxLength() throws Exception {
        cr.setMaxLength(2);
    }

	@Ignore
	@Test(expected = PostconditionError.class)
	public void testGetMinLength_postconditionError() {
		cr.getMinLength();
	}

	@Ignore
	@Test(expected = PostconditionError.class)
	public void testGetMaxLength_postconditionError() {
		cr.getMaxLength();
	}

    @Test
    public void testGetMaxLength() throws Exception {
        cr.setMaxLength(10);
        Assert.assertTrue(10 == cr.getMaxLength());
    }

    @Test(expected = PreconditionError.class)
    public void testSetMinLengthAllowed() throws Exception {
        cr.setMinLength(5);
    }

    @Test
    public void testSetMaxLengthAllowed() throws Exception {
        cr.setMaxLength(10);
    }

	@Ignore
	@Test(expected = PostconditionError.class)
	public void testSetMinLength_postconditionError() {
		cr.setMinLength(4);
	}

	@Test(expected = PostconditionError.class)
	public void setHasLetters_postconditionError() {
		cr.setHasLetters(true);
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

	@Test(expected = PreconditionError.class)
	public void isValid() {
		cr.isValid(null);
	}

}

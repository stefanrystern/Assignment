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

        //        public Integer getMinLength() {
        //            return 3;
        //        }

        cr.getMinLength();
    }

    @Ignore
    @Test(expected = InvariantError.class)
    public void testSetMaxLength_invariantError() {

//        public void setMaxLength(int max) {
//            maxLength = max;
//            minLength = Integer.MAX_VALUE;
//        }

        cr.setMaxLength(10);
    }

    @Ignore
    @Test(expected = InvariantError.class)
    public void testSetMinLength_invariantError() {

//        public void setMinLength(int min) {
//            minLength = min;
//            maxLength = 0;
//        }

        cr.setMinLength(4);
    }

    @Ignore
    @Test(expected = InvariantError.class)
    public void testSetMaxLength_invariantErrorLowerThanMIN() {

//        public void setMaxLength(int max) {
//            maxLength = max;
//            minLength = 3;
//        }

        cr.setMaxLength(7);
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
        cr.setMaxLength(4);
        cr.setMinLength(5);
    }


    @Test(expected = PreconditionError.class)
    public void testSetMaxLengthAllowed() throws Exception {
        cr.setMinLength(5);
        cr.setMaxLength(4);
    }

	@Ignore
	@Test(expected = PostconditionError.class)
	public void testSetMinLength_postconditionError() {
		cr.setMinLength(4);
	}

	@Ignore
	@Test(expected = PostconditionError.class)
	public void testSetHasLetters_postconditionError() {
		cr.setHasLetters(true);
	}

	@Ignore
	@Test(expected = PostconditionError.class)
	public void testGetHasLetters_postconditionError() {
		cr.getHasLetters();
	}

    @Test(expected = InvariantError.class)
    public void hasMixedCase_hasNoLetters() {
        cr.setHasLetters(false);
        cr.setHasMixedCase(true);
    }

    @Test(expected = PreconditionError.class)
    public void testIsValidWithNullPassword() {
        cr.isValid(null);
    }

    @Test(expected = PostconditionError.class)
    public void testIsValidWithInvalidMixedCasePassword() {
        cr.isValid("Aaaa");
    }

    @Test
    public void testIsValidWithMixedCasePassword() {
        cr.setHasMixedCase(true);
        cr.isValid("Aaaa");
    }

    @Test(expected = PostconditionError.class)
    public void testIsValidWithInvalidOnlyNumberPassword() {
        cr.isValid("1234");
    }

    @Test
    public void testIsValidWithOnlyNumberPassword() {
        cr.setHasNumbers(true); // this must come first or we'll get invariant ex.
        cr.setHasLetters(false);
        cr.isValid("1234");
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
    public void testHasNoLetters() {
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

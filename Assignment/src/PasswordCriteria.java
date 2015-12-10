import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import com.google.java.contract.Invariant;

@Invariant({
	"minLength >= MIN",
	"minLength <= maxLength",
	"!hasMixedCase || hasLetters",
	"hasNumbers || hasLetters"
})

/*
 * PasswordCriteria represents an abstraction of criteria for acceptance
 * of passwords. It captures certain rules and length requirements and 
 * provides means to check the validity of a password based on the 
 * criteria. The password may contain only letters (both cases) and numbers.
 * Special characters are not allowed.
 */
public class PasswordCriteria {

	/*
	 * The constructor of the class initializes the value of the attributes.
	 */
	PasswordCriteria() {
		minLength = maxLength = MIN;
		hasLetters = true;
	}

	/*
	 * The method returns true if the password is compliant with the citeria,
	 * otherwise false.
	 */
    @Requires({
            "pw != null"
    })
    @Ensures({
            "isPasswordValid(old(pw))"
    })
    public boolean isValid(String pw) {

		boolean res = true;

		if(pw.length() < minLength || pw.length() > maxLength) {
			return false;
		}

		ArrayList<Character> ar = new ArrayList<Character>();
		for(char c : pw.toCharArray()) {
			ar.add(c);
		}

		Iterator<Character> it = ar.iterator();
		while (it.hasNext()) {
			Character ch = it.next();
			it.remove();

			if(!isDigit(ch) && !isLetterLower(ch) && !isLetterUpper(ch)) {
				res = false;
				break;
			}

			if(((isDigit(ch) && !numbers())
					|| (isLetterLower(ch) && !letters())
					|| (isLetterUpper(ch) && !mixedCase()))
					|| (ar.contains(ch) && allDifferent())) {

				res = false;
				break;
			}
		}

		return res;

	}


	/*
	 * Setter for min length
	 */
	@Requires({
			"min >= MIN",
			"min <= maxLength"
	})
	@Ensures({
			"minLength == old(min)"
	})
	public void setMinLength(int min) {
		minLength = min;
	}


	/*
	 * Getter for min length
	 */
	@Ensures({
			"result == minLength"
	})
	public Integer getMinLength() {
		return minLength;
	}

	/*
	 * Setter for max length
	 */
	@Requires({
			"max >= MIN",
			"max >= minLength"
	})
	@Ensures({
			"maxLength == old(max)"
	})
	public void setMaxLength(int max) {
		maxLength = max;
	}

	/*
	 * Getter for max length
	 */
	@Ensures({
			"result == maxLength"
	})
	public Integer getMaxLength() {
		return maxLength;
	}


	/*
	 * Setter for hasLetters attribute
	 */
	@Ensures({
            "hasLetters == old(val)"
    })
	public void setHasLetters(boolean val) {
		hasLetters = val;
	}

	/*
	 * Getter for hasLetters attribute
	 */
	@Ensures({
			"result == letters()"
	})
	public boolean getHasLetters() {
		return letters();
	}

	/*
	 * Setter for hasMixedCase attribute
	 */
    @Ensures({
            "hasMixedCase == old(val)"
    })
    public void setHasMixedCase(boolean val) {
		hasMixedCase = val;
	}

	/*
	 * Getter for hasMixedCase attribute
	 */
    @Ensures({
            "result == mixedCase()"
    })
    public boolean getHasMixedCase() {
		return numbers();
	}


	/*
	 * Setter for hasNumbers attribute
	 */
    @Ensures({
            "hasNumbers == old(val)"
    })
    public void setHasNumbers(boolean val) {
		hasNumbers = val;
	}

	/*
	 * Getter for hasNumbers attribute
	 */
    @Ensures({
            "result == numbers()"
    })
    public boolean getHasNumbers() {
		return mixedCase();
	}

	/*
	 * Setter for hasAllDifferent attribute
	 */
    @Ensures({
            "hasAllDifferent == old(val)"
    })
    public void setHasAllDifferent(boolean val) {
		hasAllDifferent = val;
	}

	/*
	 * Getter for hasAllDifferent attribute
	 */
    @Ensures({
            "result == allDifferent()"
    })
    public boolean getHasAllDifferent() {
		return allDifferent();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * The following 3 private methods are used by the isValid(String pw)
	 * method to check the validity of the password. These methods need not
	 * be addressed for the task.
	 * 
	 */
	private boolean isLetterLower(char ch) {
		int ac = (int)ch;
		return (ac >= 97 && ac <= 122);
	}

	private boolean isLetterUpper(char ch) {
		int ac = (int)ch;
		return (ac >= 65 && ac <= 90);
	}

	private boolean isDigit(char ch) {
		int ac = (int)ch;
		return (ac >= 48 && ac <= 57);
	}

///////////////////////////////////////////////////////////////////////////////////////////////
/*
 * The following private methods may be used in the contracts for
 * the public methods above. We need not add any contracts to these
 * private methods.
 * 
 */
///////////////////////////////////////////////////////////////////////////////////////////////

	private boolean letters() {
		return hasLetters;
	}

	private boolean mixedCase() {
		return hasMixedCase;
	}

	private boolean numbers() {
		return hasNumbers;
	}

	private boolean allDifferent() {
		return hasAllDifferent;
	}

	/*
	 * The method uses alternate means to check the validity
	 * of the password. This may be used in the contracts for
	 * isValid(String pw) method.
	 * 
	 */
	private boolean isPasswordValid(String pw) {

		String range = "{" + minLength + "," + maxLength + "}";
		String reg = "^[a-zA-Z0-9]" + range + "$";
		String regl = "^.*[a-z].*$";
		String regmc = "^.*[A-Z].*$";
		String regn = "^.*[0-9].*$";
		String regad = "(.).*?(?=.*?\\1)";


		if(!Pattern.matches(reg, pw)) {
			return false;
		}

		if(letters() != Pattern.matches(regl, pw)) {
			return false;
		}

		if(mixedCase() != Pattern.matches(regmc, pw)) {
			return false;
		}

		if(numbers() != Pattern.matches(regn, pw)) {
			return false;
		}

		if(allDifferent() && Pattern.compile(regad).matcher(pw).find()) {
			return false;
		}

		return true;
	}


	private int minLength;
	private int maxLength;

	private boolean hasLetters;
	private boolean hasNumbers;
	private boolean hasMixedCase;
	private boolean hasAllDifferent;
	private static final int MIN = 4;
}

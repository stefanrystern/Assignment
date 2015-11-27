import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(ProgramTest.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.getTestHeader());
         System.out.println(failure.getException());
         System.out.println(failure.getTrace());
         System.out.println();
      }
      
      System.out.println(result.wasSuccessful()? "PASSED":"FAILED");
   }
}  	
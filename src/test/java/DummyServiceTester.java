import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DummyServiceTester {
  public static void main(String[] args) {
   
    Logger log = LogManager.getLogger(DummyServiceTester.class);
    log.info("this is a test log message");

  }
}

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestExport.class,
        TestImport.class
})

public class JunitTestSuite {
}

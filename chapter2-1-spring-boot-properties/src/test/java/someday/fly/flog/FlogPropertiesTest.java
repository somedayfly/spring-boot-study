package someday.fly.flog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class FlogPropertiesTest {

    @Autowired
    FlogProperties flogProperties;

    @Test
    public void shouldGetFlogPropertiesSuccess(){
        assertEquals(flogProperties.getName(), "somedayfly");
        assertEquals(flogProperties.getTitle(), "my flog");
        assertEquals(flogProperties.getDesc(), "somedayfly is working on my flog");
    }

}
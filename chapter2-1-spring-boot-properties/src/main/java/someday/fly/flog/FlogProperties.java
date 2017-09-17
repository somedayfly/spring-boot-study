package someday.fly.flog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlogProperties {
    @Value("${flog.name}")
    String name;

    @Value("${flog.title}")
    String title;

    @Value("${flog.desc}")
    String desc;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}

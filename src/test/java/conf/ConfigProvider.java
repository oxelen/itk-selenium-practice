package conf;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config CONFIG = ConfigFactory.load();
}

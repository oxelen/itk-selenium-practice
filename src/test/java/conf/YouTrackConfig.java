package conf;

import static conf.ConfigProvider.CONFIG;

public interface YouTrackConfig {
    String baseUrl = CONFIG.getString("youTrack.baseUrl");

    String dataPath = CONFIG.getString("youTrack.data.path");

    String adminLogin = CONFIG.getString("youTrack.login.admin");

    String adminPassword = CONFIG.getString("youTrack.password.admin");
}

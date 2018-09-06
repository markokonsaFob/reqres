package com.fobsolutions.utils

import com.fobsolutions.utils.models.Account

/**
 * Created by FOB Solutions
 */
class ConfigurationParser {

    static final String CONFIG_FILE = "src/test/resources/application.properties"
    static Properties properties = initConfiguration()

    /**
     * Gets environment URl from properties
     * @return
     */
    static String getEnvironmentUrl() {
        if (properties == null) {
            initConfiguration()
        }
        return properties.getProperty("ENVIRONMENT_URL")
    }

    /**
     * Gets valid account from properties
     * @return
     */
    static Account getValidAccount() {
        String email = properties.getProperty("VALID_EMAIL")
        String password = properties.getProperty("VALID_PASSWORD")
        new Account(email, password)
    }

    /**
     * Init configuration
     */
    private static void initConfiguration() {
        properties = new Properties()
        properties.load(new FileInputStream(CONFIG_FILE))
    }

}

package general

import com.typesafe.config.ConfigFactory

object PropertiesHocon {
  private const val DEFAULT_ENV = "tst"

    val hoconProps: PropsModel by lazy {
        val env = System.getProperty("env", DEFAULT_ENV)
        val configFileName = "$env.conf"

        val config = ConfigFactory.parseResources(configFileName)
            .resolve()

        PropsModel(
            browserName = config.getString("browserName"),
            browserVersion = config.getString("browserVersion"),
            frontendUrl = config.getString("frontendUrl"),
            backendUrl = config.getString("backendUrl"),
            selenoidUrl = config.getString("selenoidUrl")
        )
    }
}
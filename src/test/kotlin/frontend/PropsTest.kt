package frontend

import general.Config.get
import general.Properties.Companion.properties
import general.PropertiesHocon.hoconProps
import general.PropertiesJson.jsonProps
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PropsTest {

  @Test
  @Disabled("Отключено для проверки работы TestListener")
  @DisplayName("Проверка загрузки свойств из файла properties")
  fun testPropertyLoading() {
    println("Property file: $properties")
  }

  @Test
  @DisplayName("Проверка загрузки свойств из файла properties в зависимости от окружения")
  fun testPropertyKoLoading() {
    System.setProperty("env", "dev")
    println("Property file: $get")
  }

  @Test
  @DisplayName("Проверка загрузки свойств из файла json")
  fun testPropertyJsonLoading() {
    println("Property file: $jsonProps")
    println("Browser: ${jsonProps.browserName}")
    println("Browser version: ${jsonProps.browserVersion}")
    println("Front url: ${jsonProps.frontendUrl}")
  }

  @Test
  @DisplayName("Проверка загрузки свойств из файла .conf при помощи библиотеки Hocon")
  fun testPropertyHoconLoading() {
    println("Property file: $hoconProps")
    hoconProps.browserName shouldBe "chrome"
  }
}
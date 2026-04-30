package frontend

import general.AllureTestWatcher
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(AllureTestWatcher::class)
class AllureWatcherTest {

  @Test
  fun testAllureWatcher() {
    assertTrue(false)
  }

  @Test
  fun testAllureWatcher2() {
    assertTrue(true)
  }

  @Disabled("Отключено")
  @Test
  fun testAllureWatcher3() {
    assertTrue(true)
  }
}
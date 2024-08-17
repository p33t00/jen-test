package example.com.model

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class FakeTaskRepositoryTest {
    @Test
    fun testRemoveTask() {
        val FR = FakeTaskRepository()
        val res = FR.removeTask("cleaning")
        assertIs<Boolean>(res)
        assertTrue { res }
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
    }

    @Test
    fun testRemoveTask_Fail() {
        val FR = FakeTaskRepository()
        val res = FR.removeTask("chilin")
        assertIs<Boolean>(res)
        assertFalse { res }
//            assertEquals(HttpStatusCode.OK, status)
//            assertEquals("Hello World!", bodyAsText())
    }
}
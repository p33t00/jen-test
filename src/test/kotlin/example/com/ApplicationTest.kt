package example.com

import example.com.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun testDeleteTaskBadRequest() = testApplication {
        client.delete("/tasks/%20").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
        }
    }

    @Test
    fun testDeleteTaskNotFound() = testApplication {
        client.delete("/tasks/notexisting").apply {
            assertEquals(HttpStatusCode.NotFound, status)
        }
    }

    @Test
    fun testDeleteTask() = testApplication {
        client.delete("/tasks/cleaning").apply {
            assertEquals(HttpStatusCode.NoContent, status)
        }
    }
}

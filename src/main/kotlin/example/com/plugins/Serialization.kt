package example.com.plugins

import example.com.model.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureSerialization(repository: TaskRepository) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        singlePageApplication {
//            useResources = true
            filesPath = "static"
//            defaultPage = "main.html"
        }

        route("/tasks") {
            get {
                call.respond(repository.allTasks())
            }

            get("man") {
                val respContent: String = javaClass.getResource("/static/index.html")?.readText() ?: "<h2>0_- .... Smtns wrong</h1>"
                call.response.header("Content-Type", "text/html")
                call.respond(HttpStatusCode.OK, respContent)
            }

            get("/byName/{taskName}") {
                val name = call.parameters["taskName"] ?: ""

                val task = repository.taskByName(name)
                if (task == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }

                call.respond(task)
            }

            get("/byPriority/{priority}") {
                val prio = call.parameters["priority"]

                if (prio.isNullOrEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, "Prio not specified in url")
                    return@get
                }

                try {
                    val ePrio = Prio.valueOf(prio.capFirstLetter())
                    val tasks = repository.taskByPrio(ePrio)

                    if (tasks.isEmpty()) {
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(tasks)
                } catch (e: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
            }

            post {
                try {
                    val task: Task = call.receive<Task>()
                    repository.addTask(task)
                    call.respond(HttpStatusCode.NoContent)
                } catch (e: BadRequestException) {
                    println(e.message)
                    e.printStackTrace()
                    call.respond(HttpStatusCode.BadRequest, "Did you forgot to specify 'Prio' with first capital letter ?")
                    return@post
                } catch (e: Exception) {
                    println("something wrong with adding a task")
                    println(e.message)
                    e.printStackTrace()
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
            }

            delete("/{name}") {
                val name = call.parameters["name"]

                if (name.isNullOrEmpty() || name.isBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                } else {
                    if (repository.removeTask(name)) {
                        call.respond(HttpStatusCode.NoContent)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
                return@delete
            }
        }
    }
}

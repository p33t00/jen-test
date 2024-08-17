package example.com

import example.com.model.PostgresTaskRepository
import example.com.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.jetty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureDatabases()
//    val repository = FakeTaskRepository()
    val repository = PostgresTaskRepository()
    configureSerialization(repository)
    configureRouting()
}
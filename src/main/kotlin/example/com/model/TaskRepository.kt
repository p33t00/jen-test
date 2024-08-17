package example.com.model


interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun taskByPrio(prio: Prio): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(name: String): Boolean
}
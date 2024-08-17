package example.com.model

class FakeTaskRepository(): TaskRepository {
    private val tasks = mutableListOf(
        Task("cleaning", "Clean the house", Prio.Low),
        Task("gardening", "Mow the lawn", Prio.Mid),
        Task("shopping", "Buy the groceries", Prio.High),
        Task("painting", "Paint the fence", Prio.Mid)
    )

    suspend fun getFirst(): Task { return tasks.first() }

    override suspend fun allTasks(): List<Task> {
        return tasks
    }

    override suspend fun taskByPrio(prio: Prio): List<Task> {
        return tasks.filter { it.prio == prio }
    }

    override suspend fun taskByName(name: String): Task? {
        return tasks.find { it.name == name }
    }

    override suspend fun addTask(task: Task) { tasks.add(task) }

    override suspend fun removeTask(name: String): Boolean {
        try {
            val taskId = tasks.indexOfFirst { it.name == name }
            tasks.removeAt(taskId)
            return true
        } catch (e: IndexOutOfBoundsException) {
            return false;
        } catch (e: Exception) {
            print("Other shit happen !!!")
            return false
        }
    }
}
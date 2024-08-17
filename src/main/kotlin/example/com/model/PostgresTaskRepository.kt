package example.com.model

import example.com.db.TaskDAO
import example.com.db.TaskTable
import example.com.db.TaskTable.varchar
import example.com.db.daoToModel
import example.com.db.suspendTransaction

class PostgresTaskRepository() : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskDAO.all().map(::daoToModel)
    }

    override suspend fun taskByPrio(prio: Prio): List<Task> = suspendTransaction {
        TaskDAO.find { (TaskTable.prio eq prio.toString()) }
            .map(::daoToModel)
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskDAO
            .find { varchar("name", 50) eq name}
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun addTask(task: Task) {
        TaskDAO.new {
            task.name
            task.describtion
            task.prio.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
//        val rowsDeleted = TaskTable.deleteWhere {
//            TaskTable.name eq name
//        }
//        rowsDeleted == 1
        true
    }

}
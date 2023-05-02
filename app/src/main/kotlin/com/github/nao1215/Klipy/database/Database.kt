package com.github.nao1215.Klipy.database

import java.io.File
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {
    private val databaseFile = File("example.db").absoluteFile
    private val driver = "org.sqlite.JDBC"
    private val url = "jdbc:sqlite:${databaseFile.path}"

    init {
        // TODO: exception handling
        Database.connect(url, driver)
        transaction { SchemaUtils.create(ClipBoards) }
    }

    object ClipBoards : Table() {
        val id: Column<Int> = integer("id").autoIncrement()
        val content: Column<String> = varchar("content", length = 2000)
        override val primaryKey = PrimaryKey(id, name = "PK_ClipBoards_ID")
    }

    data class ClipBoard(val id: Int, val content: String)

    fun addClipBoard(content: String) {
        transaction { ClipBoards.insert { it[ClipBoards.content] = content } }
    }

    fun getClipBoards(): List<ClipBoard> {
        return transaction {
            ClipBoards.selectAll().map { ClipBoard(it[ClipBoards.id], it[ClipBoards.content]) }
        }
    }
}

package ch.abwesend.privatecontacts.infrastructure.repository

import ch.abwesend.privatecontacts.domain.util.injectAnywhere
import ch.abwesend.privatecontacts.infrastructure.room.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class RepositoryBase {
    private val database: AppDatabase by injectAnywhere()

    protected suspend fun <T> withDatabase(
        query: suspend (AppDatabase) -> T
    ): T = withContext(Dispatchers.IO) {
        database.ensureInitialized()
        query(database)
    }
}

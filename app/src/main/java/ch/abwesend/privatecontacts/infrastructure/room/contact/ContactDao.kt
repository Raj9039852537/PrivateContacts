/*
 * Private Contacts
 * Copyright (c) 2022.
 * Florian Gubler
 */

package ch.abwesend.privatecontacts.infrastructure.room.contact

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ContactDao {
    @Query("SELECT * FROM ContactEntity")
    fun getAllAsFlow(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM ContactEntity")
    fun getAll(): List<ContactEntity>

    @Query(
        """
        SELECT * 
        FROM ContactEntity
        WHERE (
            (:query != '' AND fullTextSearch LIKE '%' || :query || '%') OR 
            (:phoneNumberQuery != '' AND fullTextSearch LIKE '%' || :phoneNumberQuery || '%')
        )
    """
    )
    fun searchAsFlow(query: String, phoneNumberQuery: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM ContactEntity ORDER BY firstName, lastName, id LIMIT :loadSize OFFSET :offsetInRows")
    suspend fun getPagedByFirstName(loadSize: Int, offsetInRows: Int): List<ContactEntity>

    @Query("SELECT * FROM ContactEntity ORDER BY lastName, firstName, id LIMIT :loadSize OFFSET :offsetInRows")
    suspend fun getPagedByLastName(loadSize: Int, offsetInRows: Int): List<ContactEntity>

    @Query(
        """
        SELECT * 
        FROM ContactEntity
        WHERE (
            fullTextSearch LIKE '%' || :query || '%' OR 
            (:phoneNumberQuery != '' AND fullTextSearch LIKE '%' || :phoneNumberQuery || '%')
        )
        ORDER BY firstName, lastName, id 
        LIMIT :loadSize 
        OFFSET :offsetInRows 
    """
    )
    suspend fun searchPagedByFirstName(
        query: String,
        phoneNumberQuery: String,
        loadSize: Int,
        offsetInRows: Int
    ): List<ContactEntity>

    @Query(
        """
        SELECT * 
        FROM ContactEntity
        WHERE (
            fullTextSearch LIKE '%' || :query || '%' OR 
            (:phoneNumberQuery != '' AND fullTextSearch LIKE '%' || :phoneNumberQuery || '%')
        )
        ORDER BY lastName, firstName, id 
        LIMIT :loadSize 
        OFFSET :offsetInRows 
    """
    )
    suspend fun searchPagedByLastName(
        query: String,
        phoneNumberQuery: String,
        loadSize: Int,
        offsetInRows: Int
    ): List<ContactEntity>

    @Query("SELECT COUNT(1) FROM ContactEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM ContactEntity WHERE id IN (:ids)")
    suspend fun findByIds(ids: Collection<UUID>): List<ContactEntity>

    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    suspend fun findById(id: UUID): ContactEntity?

    @Query("SELECT id FROM ContactEntity WHERE id IN (:ids)")
    suspend fun filterForExisting(ids: Collection<UUID>): List<UUID>

    @Query("SELECT id FROM ContactEntity WHERE importId IN (:ids)")
    suspend fun getExistingIdsByImportIds(ids: Collection<UUID>): List<UUID>

    @Update
    suspend fun update(contact: ContactEntity)

    @Insert
    suspend fun insert(contact: ContactEntity)

    @Insert
    suspend fun insertAll(contacts: List<ContactEntity>)

    @Delete
    suspend fun delete(contact: ContactEntity)

    @Query("DELETE FROM ContactEntity WHERE id = :contactId")
    suspend fun delete(contactId: UUID)

    @Query("DELETE FROM ContactEntity WHERE id IN (:contactIds)")
    suspend fun delete(contactIds: Collection<UUID>)
}

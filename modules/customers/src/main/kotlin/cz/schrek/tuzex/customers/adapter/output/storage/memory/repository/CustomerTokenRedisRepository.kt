package cz.schrek.tuzex.customers.adapter.output.storage.memory.repository

import cz.schrek.tuzex.customers.adapter.output.storage.memory.entry.CustomerTokenRedisEntry
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CustomerTokenRedisRepository : CrudRepository<CustomerTokenRedisEntry, UUID>

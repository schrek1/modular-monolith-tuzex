package cz.schrek.tuzex.customers.adapter.input.contract

import cz.schrek.tuzex.common.annotations.Adapter
import cz.schrek.tuzex.common.utils.ThreadUtils
import cz.schrek.tuzex.common.utils.ThreadUtils.awaitAll
import cz.schrek.tuzex.common.utils.ThreadUtils.getOrThrow
import cz.schrek.tuzex.contracts.models.customer.Customer
import cz.schrek.tuzex.contracts.models.customer.CustomerId
import cz.schrek.tuzex.contracts.modules.customers.CustomerInfoProvider
import cz.schrek.tuzex.customers.appliacation.model.AddressType
import cz.schrek.tuzex.customers.appliacation.service.CustomerService
import cz.schrek.tuzex.customers.appliacation.usecase.GetCustomerInfoUseCase

@Adapter
class CustomerInfoProviderAdapter(
    private val customerService: CustomerService,
    private val getCustomerInfoUseCase: GetCustomerInfoUseCase
) : CustomerInfoProvider {

    override fun getCustomerInfo(customerId: CustomerId): Customer? {
        if (!customerService.isCustomerExist(customerId.id)) return null

        // simulation of gathering customer information from different sources

        val personalInfoFuture = ThreadUtils.asyncSupply(operation = "get customer personal info") {
            getCustomerInfoUseCase.getCustomerPersonalInfo(customerId.id)
        }

        val primaryAddressFuture = ThreadUtils.asyncSupply(operation = "get customer primary address") {
            getCustomerInfoUseCase.getCustomerAddress(customerId.id, AddressType.PRIMARY)
        }

        val contactsFuture = ThreadUtils.asyncSupply(operation = "get customer contacts") {
            getCustomerInfoUseCase.getCustomerContacts(customerId.id)
        }

        listOf(personalInfoFuture, primaryAddressFuture, contactsFuture).awaitAll()

        val personalInfo = personalInfoFuture.getOrThrow()
        val primaryAddress = primaryAddressFuture.getOrThrow()
        val customerContacts = contactsFuture.getOrThrow()

        return Customer(
            id = customerId,
            name = personalInfo.name,
            surname = personalInfo.surname,
            email = customerContacts.email,
            phoneNumber = customerContacts.phone,
            address = primaryAddress
        )
    }
}

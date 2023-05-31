package com.example.myapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    val itemData = mutableMapOf<Int, List<Any>>()

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    init {
        inititemData()
    }

    private fun inititemData() {
        itemData[1] = listOf("Item 1", "2021-01-01", 2)
        itemData[2] = listOf("Item 1", "2021-01-01", 4)
        itemData[3] = listOf("Item 1", "2021-01-01", 8)
        itemData[4] = listOf("Item 1", "2021-01-01", 1)
        itemData[5] = listOf("Item 1", "2021-01-01", 5)
        itemData[6] = listOf("Item 1", "2021-01-01", 2)
        itemData[7] = listOf("Item 1", "2021-01-01", 1)
        itemData[8] = listOf("Item 1", "2021-01-01", 9)
        itemData[9] = listOf("Item 1", "2021-01-01", 6)
        itemData[10] = listOf("Item 1", "2021-01-01", 8)
        itemData[11] = listOf("Item 1", "2021-01-01", 4)
        itemData[12] = listOf("Item 1", "2021-01-01", 5)
        itemData[13] = listOf("Item 1", "2021-01-01", 7)
        itemData[14] = listOf("Item 1", "2021-01-01", 3)
        itemData[15] = listOf("Item 1", "2021-01-01", 9)
    }

    fun getAll(): MutableMap<Int, List<Any>> {
        return itemData
    }

    fun fetchitem(specName: String): List<Any> {
        var tempName: List<Any>
        var tempId: Int
        var itemCharacteristics: List<Any> = emptyList()
        for (item in itemData) {
            tempName = item.value
            tempId = item.key
            if (tempName[0] == specName) {
                itemCharacteristics = tempName
                itemCharacteristics += tempId
            }
        }
        return itemCharacteristics
    }
    fun getitemDate(): ArrayList<String> {
        var datesList: ArrayList<String> = arrayListOf<String>()
        var tempItem: List<Any>
        var daate: String
        for (item in itemData) {
            tempItem = item.value
            daate = tempItem[1] as String
            datesList.add(daate)
        }
        return datesList
    }

    fun addItem(itemToAdd: List<Any>) {
        val numberItem = itemData.size
        val nextId = numberItem + 2
        itemData.put(nextId, itemToAdd)
    }

    fun setCurrentItem(selectedItem: String) {
        var itemCharacteristics: List<Any>
        var itemName: String
        // Update the LiveData values based on the selected item
//        itemCharacteristics = item.value
//        itemName = itemCharacteristics[0].toString()
//        if (itemName == name) {
//            Log.d(tag: "==> set name to Detail", name )
//        }
//
//        _name.value = itemCharacteristics[0].toString()
//        _date.value = item.key.toString().toInt()
//        _email.value = itemCharacteristics[1].toString()
//        _location.value = itemCharacteristics[2].toString().toInt()

//        _date.value = // TODO: Set the date value based on the selected item
//        _email.value = // TODO: Set the email value based on the selected item
//        _location.value = // TODO: Set the location value based on the selected item
    }

    fun updateItem(id: String, name: String, date:String, email:String, location:String) {
        for (item in itemData) {
            if (item.key.toString() == id) {
                itemData[item.key] = listOf(name, date, email, location)
            }
        }
    }



}

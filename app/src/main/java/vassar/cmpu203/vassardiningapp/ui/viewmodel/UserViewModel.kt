package vassar.cmpu203.vassardiningapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vassar.cmpu203.vassardiningapp.model.DietaryRestriction
import vassar.cmpu203.vassardiningapp.model.MealtimeItem
import vassar.cmpu203.vassardiningapp.model.User

class UserViewModel : ViewModel() {
    // The User instance that the ViewModel will manage
    private val user: User = User()

    // The LiveData that represents the user data.
    private val _userData = MutableLiveData<User>().apply { value = user }
    val userData: LiveData<User> get() = _userData

    fun inFavorites(item: MealtimeItem): Boolean {
        return item in user.favorites
    }

    // Switches the favorite status of an item
    fun switchFavoriteStatus(item: MealtimeItem) {
        user.switchFavoriteStatus(item)
        _userData.value = user // Update LiveData with new user data
    }

    // Switches the status of a restriction
    fun switchRestrictionStatus(restriction: DietaryRestriction) {
        user.switchRestrictionStatus(restriction)
        _userData.value = user // Update LiveData with new user data
    }

    fun toggleFavoriteFilter() {
        user.toggleFavoriteFilter()
        _userData.value = user
    }

    fun toggleRestrictionFilter() {
        user.toggleRestrictionFilter()
        _userData.value = user
    }
}
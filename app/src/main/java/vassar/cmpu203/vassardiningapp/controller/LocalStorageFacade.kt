package vassar.cmpu203.vassardiningapp.controller

import android.util.Log
import vassar.cmpu203.vassardiningapp.model.User
import java.io.*

private const val FILENAME = "DiningUser"

class LocalStorageFacade(private val directory: File) {

    /**
     * Issues a user save operation.
     * @param user the user to be saved.
     */
    fun saveUser(user: User) {
        val outFile = File(directory, FILENAME)
        try {
            ObjectOutputStream(FileOutputStream(outFile)).use { it.writeObject(user) }
        } catch (e: FileNotFoundException) {
            Log.e("VassarDiningApp", "Couldn't find file: ${outFile.absolutePath}")
            e.printStackTrace()
        } catch (e: IOException) {
            Log.e("VassarDiningApp", "I/O error writing to $outFile")
            e.printStackTrace()
        }
    }

    /**
     * Issues a user retrieval operation.
     * @return the retrieved user, null if none available.
     */
    fun retrieveLedger(): User? {
        val inFile = File(directory, FILENAME)
        var user: User? = null // null to begin with for negative outcome
        if (inFile.isFile) { // must check that the file actually exists
            try {
                ObjectInputStream(FileInputStream(inFile)).use { user = it.readObject() as User }
            } catch (e: IOException) {
                Log.e("VassarDiningApp", "Couldn't find file: $inFile")
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                Log.e("VassarDiningApp", "Can't find class of object from $inFile")
                e.printStackTrace()
            }
        }
        return user
    }
}

package vassar.cmpu203.vassardiningapp.view

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import vassar.cmpu203.vassardiningapp.controller.MainActivity
import java.lang.reflect.InvocationTargetException

class FragFactory(private val controller: MainActivity) : FragmentFactory() {

    // package where all all fragments reside
    companion object { private val VIEW_PACKAGE = FragFactory::class.java.getPackage()?.name }

    /**
     * Method used by fragment manager/transaction to instantiate fragments.
     * @param classLoader object to use to load fragment class
     * @param className name of fragment class to instantiate
     * @return instantiated fragment
     */
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        // convert from class name to class
        val fragClass = loadFragmentClass(classLoader, className)

        // is this fragment in our view package? if so, it must be one of ours!
        if (fragClass.getPackage().name == VIEW_PACKAGE) {
            try {
                val fcons = fragClass.constructors // get all the constructors
                assert(fcons.size > 0) { "Fragment class does not have a constructor" }
                return fcons[0].newInstance(controller) as Fragment // go with first constructor
            } catch (e: IllegalAccessException) {
                val emsg = String.format("Can't instantiate %s: ensure it's concrete and " +
                        "has a public constructor with a ControllerActivity parameter", fragClass)
                Log.e("NextGenPos", emsg)
                e.printStackTrace()
            } catch (e: InstantiationException) {
                val emsg = String.format("Can't instantiate %s: ensure it's concrete and " +
                        "has a public constructor with a ControllerActivity parameter", fragClass)
                Log.e("NextGenPos", emsg)
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                val emsg = String.format("Can't instantiate %s: ensure it's concrete and " +
                        "has a public constructor with a ControllerActivity parameter", fragClass)
                Log.e("NextGenPos", emsg)
                e.printStackTrace()
            }
        }

        // default is to delegate to superclass
        return super.instantiate(classLoader, className)
    }
}

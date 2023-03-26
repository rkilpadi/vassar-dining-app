```plantuml
class TextUI {
-controller : Controller
-out : PrintStream
-scanner : Scanner
--
run()
pickCafe() : String
pickMealtime() : String
}

class Controller {
-currentUser : User
--
+findMenu(String, String) : void
+setCurrentMenu(Menu) : void
+getMenu() : Menu
+main(String[]) : void
}

class Menu {
-cafe : String
-mealtime : String
--
+toString()
}

class MenuItem {
-name : String
-description : String
-dietaryRestrictions : Set<DietaryRestriction>
-favoriteStatus : boolean
}

class DietaryRestriction {
-restriction: String
-imagePath: String
--
+getRestriction()
+getImagePath()
}

class Data {
-name : String
-preferredCafes : Set<String>
-preferredMealtimes : Set<String>
-dietaryRestrictions : Set<DietaryRestriction>
--
+addToFav()
+removeFromFav()
+getFavList()
}

class User {
-username : String
-data : Data
--
+addToFav()
+removeFromFav()
+getFavList()
}

Controller -> "(1..*)\n dietaryFilter \n{List}" DietaryRestriction : \t\t\t\t
Menu -> "(1..*)\n menuItems \n{Set}" MenuItem: \t\t\t\t
MenuItem -> "(1..*)\n dietaryRestrictions\n{Set}" DietaryRestriction: \t\t\t\t
Data -> "(1..*)\n favorites \n{Set}" MenuItem: \t\t\t\t

```
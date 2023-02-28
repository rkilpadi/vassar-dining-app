```plantuml
class Controller{
-date : TimeStamp
-cafe : Vendor
--
sendMenu() 
sendFav()
}

class Menu{
-date : String 
-time : Timestamp
--
pullMenuFile()
filterMenu()
getMenu()
}

class MenuItem{
-foodName : String
-description : String
--
getItem()
}

class DietaryRestriction {
-restriction: String
-image: String
--
getRestriction()
}

class User{
-favorites : Set<MenuItem>
--
addToFav()
removeFromFav()
getFavList()
}

Controller -> "(1..*)\n dietaryFilter \n{List}" DietaryRestriction : \t\t\t\t
Menu -> "(1..*)\n menuItems \n{List}" MenuItem: \t\t\t\t
MenuItem -> "(1..*)\n dietaryRestrictions\n{Set}" DietaryRestriction: \t\t\t\t
User -> "(1..*)\n favorites \n{Set}" MenuItem: \t\t\t\t
```
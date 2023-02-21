@startuml
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
}

class MenuItem{
-foodName : String
-description : String
-favoriteStatus : bool
}

class DietaryRestriction {
-restriction: String
-image: String
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
@enduml
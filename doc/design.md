```plantuml
class TextUI {
    -controller: Controller
    -out: PrintStream
    -scanner: Scanner

    +run(): void
    -pickCafe(): String
    -pickMealtime(): String
}

class Controller {
    -currentUser: User
    +findMenu(String, String): void
    +setCurrentMenu(Menu): void
    +getMenu(): Menu
    +main(String[]): void
}

class Menu {
    -cafe: String
    -mealtime: String
    -menuItems: Set<MenuItem>

    +toString(User): String
}

class MenuItem {
    -name: String
    -description: String
    -dietaryRestrictions: Set<DietaryRestriction>
    -favoriteStatus: boolean
}

class DietaryRestriction {
    -restriction: String
    -imagePath: String

    +getRestriction(): String
    +getImagePath(): String
}

class Data {
    -name: String
    -preferredCafes: Set<String>
    -preferredMealtimes: Set<String>
    -dietaryRestrictions: Set<DietaryRestriction>
    -favorites: Set<MenuItem>

    +addToFav(MenuItem): void
    +removeFromFav(MenuItem): void
    +getFavList(): Set<MenuItem>
}

class User {
    -username: String
    -data: Data

    +addToFav(MenuItem): void
    +removeFromFav(MenuItem): void
    +getFavList(): Set<MenuItem>
}

Controller -> "(1) currentUser" User
Controller -> "(1) findMenu" Data
Controller -> "(1) setCurrentMenu" Menu
Controller -> "(1) getMenu" Menu
TextUI -> "(1) controller" Controller
Menu -> "(1..*) menuItems" MenuItem
MenuItem -> "(1..*) dietaryRestrictions" DietaryRestriction
Data -> "(1..*) favorites" MenuItem
User -> "(1) data" Data
User -> "(1..*)" MenuItem
DietaryRestriction -> "(0..*)" MenuItem

```
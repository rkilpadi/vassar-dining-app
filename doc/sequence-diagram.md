```plantuml
actor User as user
participant " : TextUI" as textUI
participant ": Controller" as controller
participant ": Data" as data
participant ": Menu" as menu
participant ": MenuItem" as menuItem
participant ": User" as userObj

activate textUI
user -> textUI : User starts app

textUI -> controller : run()

activate controller
controller -> textUI : pickCafe()
textUI -> user : Select a cafe: deece
user -> textUI : deece
controller -> textUI : pickMealtime()
textUI -> user : Select a mealtime: breakfast, lunch
user -> textUI : breakfast
controller -> data : findMenu(cafe, mealTime)

activate data
data -> menu : new(cafe, mealtime, menuItems)
menu --> data
deactivate data

controller -> textUI : printMenu(menu)
textUI -> menu : toString(userObj)

activate menu
menuItem -> dietaryRestriction : dietaryRestrictions()
dietaryRestriction --> menuItem
menuItem --> menu
menu --> textUI
deactivate menu

textUI -> user : Select an item to favorite or unfavorite, or type skip: eggs, bacon, pasta, salad
user -> textUI : eggs
controller -> userObj : switchFavoriteStatus(item)

textUI -> user : Switched favorite status for eggs. Favorited items appear with a *.
textUI -> controller : doNext()

deactivate controller
deactivate textUI

```
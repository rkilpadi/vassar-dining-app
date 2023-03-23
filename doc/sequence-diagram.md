```plantuml
actor User as user
participant " : TextUI" as textUI
participant ": Controller" as controller
participant ": Menu" as menu
participant ": Data" as data

activate textUI
user -> textUI : User starts app

textUI -> user : Requests cafe
user -> textUI : Inputs cafe
textUI -> user : Requests mealtime
user -> textUI : Inputs mealtime

textUI -> controller : findMenu(cafe, mealTime)

activate controller
controller -> menu : new(cafe, mealtime, menuItems)

activate menu
menu --> controller
deactivate menu
controller --> textUI : getMenu()

textUI -> menu : toString()

textUI -> user : Prints menu

textUI -> user : Requests item 
user -> textUI : Inputs item user wants to favorite

textUI -> controller : addToFav(itemNumber)

activate data
controller -> data : addToFav(itemNumber)

textUI -> user : Item added to favorites

deactivate data
deactivate controller
deactivate textUI

```
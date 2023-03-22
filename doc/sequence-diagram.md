```plantuml
actor User as user
participant " : TextUI" as textUI
participant ": Controller" as controller
participant ": Menu" as menu

activate textUI
user -> textUI : User starts app


textUI -> textUI : run()
textUI -> textUI : pickCafe()
textUI -> textUI : pickMealtime()

textUI -> controller : findMenu(cafe, mealTime)

activate controller
controller -> menu : new(cafe, mealtime, menuItems)

activate menu
menu --> controller
deactivate menu
controller --> textUI : getMenu()
deactivate controller 

textUI -> menu : toString()
deactivate textUI
```
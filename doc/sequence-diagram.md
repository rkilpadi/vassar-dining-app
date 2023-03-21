```plantuml
actor User as user
participant " : TextUI" as textUI
participant ": Controller" as controller
participant ": Menu" as menu

user -> textUI : User starts app


textUI -> textUI : run()
textUI -> textUI : pickCafe()
textUI -> textUI : pickMealtime()

textUI -> controller : findMenu(cafe, mealTime)

controller -> menu : new(cafe, mealtime, menuItems)

menu --> controller

controller --> textUI : getMenu()


textUI -> menu : toString()

```
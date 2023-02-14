## Add Favorite

User can add dishes to a favorites list and may opt in to be notified if the item is available.

@startuml
start
skinparam activityDiamondBackgroundColor lightgreen

if (User clicks the heart icon) then 
    #pink:Checks if already favorited;
    #pink :Remove from favorites;
    stop
endif
    #pink:Adds to favorites database;
stop



@enduml
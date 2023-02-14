## View Menu

User should be able to view current, future, and past menus using data from the Vassar Dining API. Dietary restrictions and descriptions should be listed next to menu items. Menus should include all campus dining areas including the Retreat, Gordon Commons, and Express. User should be able to filter by dietary requirements and by meal time.

@startuml
skinparam activityDiamondBackgroundColor lightgreen
start
#lightgreen :Open App;

#pink :Display Menu;
repeat
    switch (Changing Dining Criteria)
    case ( )
    #lightgreen :Dining Area;
    case ()
    #lightgreen :Date;
    case ()
    #lightgreen :Dietary Restrictions;  
    case ( )
    #lightgreen :Meal Time;

    endswitch

    #pink :Display Updated Menu;

repeat while (Changes Menu Options) is (yes)
-> no;




stop
@enduml

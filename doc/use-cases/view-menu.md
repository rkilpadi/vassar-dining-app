## View Menu

User should be able to view current, future, and past menus using data from the Vassar Dining API. Dietary restrictions and descriptions should be listed next to menu items. Menus should include all campus dining areas including the Retreat, Gordon Commons, and Express. User should be able to filter by dietary requirements and by meal time.

```plantuml
'green is for human actor and pink is for the program'

skinparam activityDiamondBackgroundColor pink
start
#lightgreen :Open App;

repeat
#pink :Request cafe;  

#lightgreen : Input cafe;
 
#pink :Request mealTime;
     
#lightgreen :Input mealTime;

#pink :Print Menu;

repeat while () is (yes)
-> no;




stop
```

## Leave Review

User should be able to leave a review using a 5-star scale. Reviews should be entered into a database for dining staff and other students to view. Reviews could be used to present users with a top meal of the day/week.
```plantuml


skinparam activityDiamondBackgroundColor lightgreen

start
if (User clicks the chat icon) then
    #pink:Already reviewed;
    #pink :Edit review;
    stop
endif
#pink:System adds to review database;
stop
```
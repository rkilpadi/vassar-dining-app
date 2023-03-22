# Project Vision

### Target Audience: Vassar students, employees, and dining staff.

### Business Case: Improves quality of student life. Allows for feedback on menu items.


## Main Features:
- Displaying the menu
- Creating a list of favorite dishes
- Notifications for specific dishes
- Leaving reviews
- Recommending meals
- Filtering by dietary restriction

# Human Actors
- Consumer: View menu, Save lists, Receive notifications, Leave Reviews, Post Recommendations, and Filter by dietary restrictions

# System Actors
- Vassar Dining API: Return menu


```plantuml

' human actors
actor "Consumer" as consumer

' system actors 
actor "Vassar Dining API" <<system>> as vassarDiningAPI

' list all use in cases in package
package "Campus Dining App" {
    usecase "View Menu" as viewMenu
    usecase "Leave  Review" as leaveReview
    usecase "Add favorite dishes" as addFavorite
    usecase "Submit a recommendation" as submitRec
}

' list relationships between actors and use cases

consumer --> viewMenu
consumer --> leaveReview
consumer --> addFavorite
consumer --> submitRec
viewMenu --> vassarDiningAPI
```
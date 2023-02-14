# Project Vision

### Target Audience: Vassar students, employees, and dining staff.

### Business Case: Improves quality of student life. Allows for better stock/labor management.

## Main Features:
- Displaying the menu
- Creating a list of favorite dishes
- Notifications for specific dishes
- Leaving reviews
- Recommending meals

# Human Actors
- Consumer: View menu, Save lists, Receive notifications, Leave Reviews, and Post Recommendations

# System Actors
- Vassar Dining API: Return menu
- Database: Hold user’s favorite dishes, reviews, and meal recommendations
- Interface: Display menu, other user’s reviews, and personal account details (username, favorites list)


@startuml
' human actors
actor "Consumer" as consumer
actor "Dining Staff" as diningStaff

' system actors 
actor "Vassar Dining API" <<system>> as vassarDiningAPI
actor "Database" <<system>> as database
actor "Interface" <<system>> as interface

' list all use in cases in package
package "Campus Dining App" {
    usecase "View Menu" as viewMenu
    usecase "Leave  Review" as leaveReview
    usecase "Add favorite dishes" as addFavorite
    usecase "Submit a recommendation" as submitRec
    usecase "View Feedback" as viewFeedback
}

' list relationships between actors and use cases

consumer --> viewMenu
consumer --> leaveReview
consumer --> addFavorite
consumer --> submitRec
diningStaff --> viewFeedback
viewMenu --> vassarDiningAPI
@enduml
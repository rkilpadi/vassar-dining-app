# Non-functional Specification
## Functional
Users should be able to view menu items along with their descriptions, dietary descriptions, and reviews.  
Users should be able to leave reviews and favorite items to get notified when it is available.

## Usability
App should be simple and intuitive. Should directly open to current menu.  
We will use familiar icons for leaving reviews and ratings.

## Reliability
Menu will be displayed whenever the dining API is active.  
Can use local storage to allow for offline viewing and faster startup times when the app has been opened recently. 

## Performance
Loading times and throughput should be minimal due to the simplicity of the app.  
Can improve load time by using local storage.

## Supportability
Need to rely on accurate XML data feed from BonAppetit.  
Does not have to be Vassar specific to allow for potential expansion in other contexts.

## Implementation
Development is currently in Java targeted for Android. Creating a database to store user information such as favorites and reviews. Pulling XML data using a Heroku app.


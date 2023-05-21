# Non-functional Specification

## Usability
App should be simple and intuitive. Should directly open to current menu.  
We will use familiar icons for leaving reviews and ratings.

## Reliability
Menu will be displayed whenever the dining API is active.  
Can use local storage to allow for offline viewing and faster startup times when the app has been opened recently.  
Should ensure that data is not lost as result of a crash.

## Performance
Loading times and throughput should be minimal due to the simplicity of the app.  
Can improve load time by using local storage.

## Supportability
Need to rely on accurate XML data feed from BonAppetit.  
Does not have to be Vassar specific to allow for potential expansion in other contexts.

## Implementation
Development is currently in Java targeted for Android. Creating a database to store user information such as favorites and reviews. Pulling XML data using a Heroku app.


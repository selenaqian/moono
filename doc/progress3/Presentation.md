# The Ninth Planet Final Project: Sprint 2 Progress

### Presentation "Game Plan":
* Run a demo
* Walk through data files
* Walk through some testing
    * JUnit
    * TestFX
* Everyone describes their contributions
    * Selena
    * Mary
    * Suomo
    * Tess
* What we've learned/going forward discussion (bullets below)

### What we learned during this Sprint and the implementation plan for the next Sprint:

##### Completed planned features:
* special cards
    * skip
    * reverse
    * draw 2
    * wild draw 4
    * wild
* change color of play (wild cards)
* update ClassicRules to include special cards
* implement Deadly Uno rule - immediately lose if can't play
* multiple rounds of gameplay with score calculation
* display scores for all players
* rules selection
* special cards selection
* color theme selection

##### Features original planned for Sprint 2 that are now moved to Complete
* Drafted classes to save/load a game but we need to integrate them with the rest of the project
* Game area editor

##### What helped or impeded progress:
* Helped:
* We have been meeting frequently over Zoom which makes discussion very easy.
* Watching Duvall’s lecture videos and doing the readings have bestowed new useful knowledge upon us.
* We have been leaving comments on our code for our teammates so it’s easy to read. Also, when we’ve changed other people’s code, noting who changed it and why has been a very helpful “breadcrumbs trail.”
* Impeded:
* We were slow to start coding this sprint. We spent a couple days more focused on discussion and planning which was useful but we should have done so more efficiently. We have felt rushed with the actual implementation of this sprint.	
* Not pushing updates to master has slowed down the group overall. Some of our classes weren’t compiling which made it hard to move forward on some classes and although team members have done the work to fix it, we were slow to push and get everyone on the same page.

##### What worked, what did not, and something specific that is planned for improvement next Sprint:
* What worked:
	* Our project was broken up well enough where we could all make significant progress on individual parts. The challenges arise when we need to piece it all together.
* What didn’t work:
	* Sticking to our original design plans/interfaces. There have been complications getting different parts of the project to properly communicate with each other and we’ve had to make adjustments to minimize dependencies.
* Plan for improvement:
	* We will start our next sprint with a refactoring session so we can get on more solid ground and then be able to finish up our features.

##### A significant event we learned from:
* Reading and learning about observers
    * very useful for communication between the different modules and updating the visuals based on changes to other things
    * required a lot of communication about how this will work
    * required refactoring to include this design pattern - it’s better design but was difficult to incorporate because didn’t originally know about how to do this and weren’t planning for it

##### Features planned to be completed in Complete (with who will work on them):
* Saving and loading games from XML files - Tess and Mary
* Include a JavaFX timeline so that the human player can also catch AI players on Uno - Mary
* Visual feedback for events (e.g. showing the winner of a round, graphics for when uno is called) - Selena
* Different visual skins with images - Selena
* Ability for players to give themselves names / have custom names - Suomo
* Additional special cards - Tess and Suomo
* “Select all” checkbox for special card selection - Selena
* Ascending uno rules - Tess
* Game area editor/preferences selection - Selena

##### “Reach” features we would like to implement in Complete but are lower priority. (Completed by whomever gets their features done first)
* player profiles
* display and allow changes to player profiles
* social center
* social center UI
* save game data to web
* dynamic game aspect changes
* music + sound effects

##### Any concerns that may complicate the Complete plan:
* We think there are more features than we can realistically implement in one week. If there were more time, we are certain that we could accomplish everything, but with the given time we will have to prioritize features.
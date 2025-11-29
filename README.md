# Fridge Inventory

## Why is this project of interest to you?

This project is of interest to me because I recently started cooking for myself this semester and I often find myself struggling to remember what ingredients I have and when I should eat them by. This leads to perishables sitting in the back of my fridge longer than they should, causing fruit and veg to become mushy and creating more food waste than wanted. A fridge inventory application can help solve this problem by reminding me of what I already have and further inspiring meal choices. There is also the benefit of being easily accessbile while away from home, allowing the user to keep an eye on the items in the fridge while physically outside and can help with creating shopping lists. 

## Who will use it?

This application is designed for anyone who struggles with keeping track of what perishables are in their fridge and using them before they spoil. Speficially, this application can be of help for new cooks, such as students, who want to better plan their meals, reduce food waste, and manage their produce.

## User Stories

 - As a user, I want to be able to add multiple items to my digital fridge
 - As a user, I want to see what is already in my digital fridge
 - As a user, I want to use up items in my digital fridge
 - As a user, I want to generally categorize the items in my digital fridge (frozen, dairy, fruits, etc)

# Phase 2

 - As a user I want the choice to save my fridge with all food items remaining in the same state to file
 - As a user I want the option to load my fridge and see my fridge in the same state from file

 # Instructions for End User

 - You can view the panel that displays the Xs that have already been added to the Y by: to view the food that has been added to the fridge/freezer, click on the respective tabs
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by: clicking on the button to add food
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by: clicking on the button to remove food or change  the expiry date
- You can locate my visual component by: the background of the start page and the background of the tabs
- You can save the state of my application by: closing the application will prompt the user to save the fridge or not
- You can reload the state of my application by: by pressing the start button, the app will prompt the user to either load or start a new fridge.

# Phase 4

## Task 2

`Sample Event Log Output`

Thu Nov 27 20:33:40 PST 2025
Added food: milk Quantity: 2
Thu Nov 27 20:33:51 PST 2025
Added food: ice cube Quantity: 10
Thu Nov 27 20:34:10 PST 2025
Changed expiry date of ice cube by 20
Thu Nov 27 20:34:21 PST 2025
Changed quantity of milk by 1
Thu Nov 27 20:36:20 PST 2025
Saved.
Thu Nov 27 20:36:20 PST 2025
Saved.

## Task 3

  If I had more time for my project I would have designed the fruit and frozen classes to have more functionality. They simply do not do much other than letting the user categorize their items between a fridge, freezer, and whether it is a fruit or not. Given more time I would have liked to implement features regarding the ripeness and expiry date and having them effect each others state. I also would add more food types. Furthermore, if I had more subclasses, I think my food class should have been redesigned into an abstract food class. My fridge class could have also been split into a functioanlity and a collection class. Currently I feel that my fridge class is unorganized and messy and dividing the functions into two seperate classes would not only improve the aesthetics but also make any further additions easier.


# UI Layout and Controls #

  * Player HUD system.  This is a display located in the north-west corner of the screen that displays the player's statistics as follows:
    * class symbol such as a sword and shield for a Holy Knight.
    * the players health/hitpoints in a bar form (bar is red).
    * the players mana/magic points n a bar form (bar is blue).

  * Chat.  The chat system consists of multiple GUI elements melded into one.  A tabbed pane holds a message box (scrollable), a text input, and a send button.  Each tab on a tabbed pane is a channel.  An example of this would could be a tab for general chat, one for combat output, one for group chat, etc.  The user can split these up into multiple tabs **OR** can combine them **OR** a combination of both.  Messages broadcasted will follow the color standards (Cyan when talking in group chat, etc.).  The send button is also accessed by the enter/return key when the input box has focus.

  * Player skill bar(s).  This is a bar containing slots to assign skills/abilities to a location in the bar.  This bar will contain 12 slots per bar.  Each bar will be given a keyboard shortcut {1,2,3,4,5,6,7,8,9,0,-,=} for the default bar and then shift + the shortcut set, lastly followed by crtl + the shortcut set. Any additional bars will have no keyboard shortcuts.  Alternatively, you can mouse click a skill in a bar to activate that skill (if the skill is applicable to be used). The mouse click is the only way to activate skills on bars more than 3 (the ones with no shortcuts).

  * Window Buttons.  These are clickable buttons that produce various windows to aid users.  The available buttons (explaned in the windows section in more detail) will be:  Options, Persona, Skills/Abilities, Inventory, Equipment, Map, Guild, Social, Quests.  These buttons will be toggle buttons and textured with only an icon.  These windows might have successor windows.  Each of the 9 parent windows will have a keyboard shortcut's:
    * options = esc
    * (P)ersona = p
    * S(k)ills = k
    * (I)nventory / Equipment = i
    * (M)ap = m
    * G(u)ild = u
    * S(o)cial = o
    * Quest (J)ournal = j
    * Console = F1 (this if for testing only).

  * Mini-map.  Spherical radius of player using standard colored dots to represent a top-down view of immediate surroundings.


# Windows #

  1. All windows will be transparent

  * Options:  The options window will be a parent window for multiple successor windows.  The root window entitled "Options"  will consist of 4 buttons leading to successors. The buttons are: Game(for Audio/Video), Help(In-game bug reporting and simple tutorial), Logout (brings user to character screen), Exit (Fully quits the application).

  * Persona: The persona window will be a spec sheet for the player.  This window will display all attributes (Strength, dexterity, block percent, etc.), players name, level, race, class, etc.  At the bottom of the persona window the players biography is displayed in an editable box where the player can update their bio at anytime.

  * Skills: This is a simple list of skills and abilities the player has learned or acquired.  Each skill/ability will be listed as an icon the name of the skill.  (For example: Holy Knights have a shield bash, the icon would depict this.  On the skills window it would appear as  { ICON }  Shield Bash).  Hovering the mouse over each skill icon will bring a tooltip up to describe the skill in detail.  Furthermore, the icons are DnD (Drag and Drop) capable.  To utilize a skill, you drag the icon that skill to your skill bar.  Dragging a skill from the skill window to your bar still leaves a copy in the skills window.

  * Inventory: The inventory window will consist of up to multiple windows.  Each window is a graphical representation of the players bags/packs/etc.  If the player has 2 bags of items, clicking the inventory button (or shortcut) would bring up 2 windows consisting of slot-like spots depending on the number of slots the players bags have.  Each item, which is represented by an icon and tooltip for detail, is DnD capable in the inventory window(s) to allow the user to move items and organize their inventory easily.

  * Equipment: The equipment window will serve as graphical representation of currently equipped or open equipment armor spots.  The window will consist of open item slots in front of a background of a warrior.  The open slots would be over the area where you would equip it. (For example, the chest slot would be over the chest of the warrior in the background.  The slots will be the same as the inventory slots of a bag, however they only accept equipment items whose requirements are met by the player trying to equip it.

  * Map: The map will not show any world objects (enemies, harvest nodes, NPCs, etc.) but will show players, landmarks, cities, mountains, forests, etc. The map will be displayed as the least transparent window (possibly none) and will feature a transparent grid over the map. Functions of the map will include updating the player's position on the map and scroll to ensure the player stays viewable.  The map will also display an (x,y) coordinate in the north-west corner of the map as the mouse hovers over the map.  The map can be right-clicked to save locations on. When saving locations the user may label each pin location as they choose.

  * Guild: The guild window will consist of guild details (name, guild level, and a message of the day), a list of member names and detail subscript information like the Friends tab, and a guild control buttons enabled/disabled depending on user privileges.  More detail on this window when the guild concept is complete

  * Social: The social window will be a tabbed pane.  The Tabs will be: { Friends, Ignore, Raid }.  The Friends tab be a simple list of names and a buttons to add and remove other players from. Each name in the list will have a subscript that gives details (Level, class, and location) of that friend. Ignore will be the same as the Friends tab, however, the ignore tab will not have any subscript detail. The Raid tab will be an organized list grouping each member of the individual group together. The leader of the Raid will have the ability to drag members from one group to another in order to easily organize the raid.

  * Quests: The Quest window will consist of 2 total windows, but only 1 will appear when you click the Quests button (or use the keyboard shortcut).  The first window is a list of active quests, color coated based on the suggested level versus the players current level.  The window will be organized in sections by areas of the quest locations. Each quest in the Quest window will also display the suggest level of the quest next to the quest's name.  If a user clicks a quest in the list, a second window is then displayed next to the original one to explain the the quest in detail.

  * Console: allows cheats to be administered, such as, adding quests/items/skills increasing stats/xp/skills.  This will be our easy way of testing the game environment manually where automated testing will not function for us.

## On to Design ##
Windows are setup as simple UI layout objects that delegate the work down to a presenter.   Presenters contain the logic to; navigate the next window and handle interaction with the model.  This is the common [Passive View Pattern](http://martinfowler.com/eaaDev/PassiveScreen.html).  All windows are defined by an interface known as the _View_.  This view is passed into the presenter upon startup and is used to initialize the view and presenter together.    Each View is implemented by a _Window_ which is responsible for setting up the default look and arranging any setup of controls and special keys.  To help facilitate some of the setup and handling of key bindings a _Window_ can be extended by a _BaseWindow_ which provides these functions (such as activation, deactivation of a _Window_ along with the handling of key bindings).

To help facilitate with testing a simple, and very much incomplete set of widget operators has been created to simulate button presses, enter of text into text fields, as well as selection of items within a combo box.  This can be found in the _com.aether.gbui_ and _com.aether.gbui.operators_ packages.  It is expected that as we continue forward developing more widgets that new operators will be created to help support testing purposes.  Most of the views/windows have been wrapped into a _Page_ object, which comes from the [Page Object Pattern](http://code.google.com/p/webdriver/wiki/PageObjects), to help simplify testing.  For an example of how to use these _Page_ objects or the _Operators_ please refer to the functional tests respectively.

  * _Note:_Page_objects can be found in the_com.aether.presenter.state_package._

# World Object Interaction #

  * World objects such as Doors, Quest Objects, NPCs, Harvestable nodes, etc. will be double click or right clickable.   A right click will bring up a drop down menu (also transparent) with the actions available.  A double click will automatically take the first action available.  Each object needs to be within a certain range of the player to be able to interact with it.
import java.util.Scanner;
/**
 * Text Game Template
 * @author   Brandon Bettes
 * @version  1.0
 * 
 * 
 * Write the title and description of your game here.
 * 
 * 
 * @authors (your names) 
 * @version (a version number or a date)
 */
public class Copy
{
    /** 
     * 
     * Field variables Go Up Here
     * 
     */
    // Declare any field, aka world variables that you need here.
    // For example, you could assign a number to each room so that we know where the player
    // is currently located. Uses key word static in declaration.
    // Example:
    static int currentRoom;

    // If you want the player to have character stats, declare them here as a static variable.
    // Examples:
    static String playerName;
    static int playerHealth;
    static int playerStamina;

    // If you want to have items that the player can find in game and add to their 'inventory',
    // declare item variables here. We can use booleans to represent if the player has the item
    // in their inventory or not.
    // Examples:
    
    /** Corn
     * field
     *  taken name: Corn
     */
    static boolean corn;
    static int cornLocation; // Keep track of item location.
    
    /** Pitchfork
     * 
     *  field
     *  taken name: Pitchfork, Fork
     *  useable: fork
     */
    static boolean pitchfork;
    static int pitchforkLocation;
    
    static boolean grenade;
    static int grenadeLocation;
    
    static boolean cure;
    static int cureLocation;
    // Keep track of the turns taken by player:
    static int turnsTaken;
    
    
    
    
    // End of field variables

    /** Prompts the user to play game or quit.
     */
    public static void main(String[] args)
    {
        boolean run = true;
        do
        {
            Scanner sc = new Scanner(System.in);

            System.out.print("Begin game or quit: ");
            String response = sc.next().toLowerCase();

            if( response.equals("begin") )
            {
                startGame();
                run = false; // after our game is over, exit the loop
            }
            else if( response.equals("quit") )
            {
                run = false;
            }
            else
            {
                System.out.println("ERROR.");
            }

        }while(run);
    }

    /** Displays the introduction to the game.  Includes any instructions you may want to give
     *  the user.
     */
    public static void displayIntro()
    {
        System.out.println("You are in a abondened field in the middle of nowhere. All around you see mangled corpses. ");
        System.out.println("Your goal is to find a way to survive in anyway posible.");
        System.out.println("For now, all commands are limited to one word at a time.");

        System.out.println();
        System.out.println();
    }

    /** Displays a description of the room where the character is currently located.
     */
    public static void displayCurrentRoom()
    {
        switch( currentRoom )
        {
            case 1: // starting room
            {
                System.out.println("You are in a corn field .");
                System.out.println("To the west you see a abandoned building that has flashing lights emerging from it.");
                System.out.println("To the east you can make out a crop circle that looks oldly strange.");
                if( !corn )
                {
                    System.out.println("You see a cob of corn on the ground in front of you.");
                }
                break;
            }
            case 2: // north of starting room
            {
                System.out.println("You walk into the middle of the crop circle and see a strange light above you. ");
                System.out.println("You have now been abducted by aliens and will die from alien scientists experiments.");
                
                System.exit(0);
                
                break;
            }
            case 3: //Computer room
            {
                System.out.println("You enter a room full of flashing lights coming off of the computers. ");
                System.out.println("In the back of the room you see a huge man gaurding a button that reads \"Dangerous Do Not Touch\".");
            }
            default:
            {
                break;
            }
        }
    }

    /** Displays all items in player's inventory.
     */
    public static void displayInventory()
    {
        if( corn )
        {
            System.out.println("A cob of corn.");
        }   

        if( pitchfork )
        {
            System.out.print("A simple pitchfork.  It is nice and sharp and seems dangerous. ");
        }
        
        if( grenade)
        {
            System.out.print("A grenande. You feel the power inside of the ball.");   
        }
        
        if(cure)
        {
            System.out.print("A vial or medician. Possibly a cure.");
        }
    }

    /** Initializes static variables
     */
    public static void init()
    {
        /** 
         * Player creation
         */
        // Prompt user for character name.
        System.out.print("What is your name? ");
        Scanner kb = new Scanner(System.in);
        playerName = kb.next();
        // Player stats
        playerHealth = 5;
        playerStamina = 5;
        
        
        /** 
         * Starting room number:
         */
        currentRoom = 1;

        /** 
         * Items:
         * Set all items that we don't want the user to start with to false,
         * and if we want them to start with an item set to true.
         */
        corn = false;
        cornLocation = 1;  // Elven sword will be located in room 2

        pitchfork = false; // starting with oil lamp in inventory
        pitchforkLocation = 1;
        
        grenade = false;
        grenadeLocation = 3;
        
        cure = false;
        cureLocation= 5;
    }

    /** Starts a new game
     */
    public static void startGame()
    {
        displayIntro();  // Display the game's intro!

        init(); // Initialize game variables

        displayCurrentRoom();  // Display the starting room description

        boolean menu = true;
        do
        {
            Scanner kb = new Scanner(System.in);

            String getAction = kb.next().toLowerCase();

            switch( getAction )
            {
                case "inventory":
                {
                    displayInventory();
                    break;
                }
                case "look":
                {
                    displayCurrentRoom();
                    break;
                }
                case "take":
                case "get":
                {
                    takeItem();
                    break;
                }
                case "n":
                case "north":
                case "s":
                case "south":
                case "e":
                case "east":
                case "w":
                case "west":
                case "down":
                case "d":
                case "u":
                case "up":
                {
                    move( getAction );
                    break;
                }
                
                case "go":
                case "walk":
                case "run":
                case "jog":
                {
                    System.out.println("Which direction do you want to " + getAction + "?");
                    break;
                }
                
                case "quit":
                {
                    menu = false;
                    break;
                }
                
                default:
                {
                    System.out.println("Error message here, action not defined");
                    break;
                }

            }
            
            System.out.println(); // print a space between attempted actions

        }while( menu );
    }

    /** 'Move' command
     * 
     * Moves the player in a given direction.
     * 
     * @param direction - the direction the player wants to go.
     */
    public static void move(String direction)
    {
        if( canMove(direction) )
        {
            switch(currentRoom)
            {
                case 1: // starting room
                {
                    if( direction.equals("e") || direction.equals("east") )
                    {
                        currentRoom = 3;
                    }
                    else if (direction.equals("w")|| direction.equals("west"))
                    {
                        currentRoom = 2;   
                    }
                    break;
                }
                case 2: // cave entrance
                {
                    break;
                }
                case 3://computer room
                {
                    if ( direction.equals("e")||direction.equals("east"))
                    {
                        currentRoom = 4;
                    }
                    else if (direction.equals("w")||direction.equals("west"))
                    {
                        currentRoom = 1;
                    }
                }
                case 4:
                {
                    if ( direction.equals("w")||direction.equals("west"))
                    {
                        currentRoom = 3;
                    }
                    else if (direction.equals("d")||direction.equals("down"))
                    {
                        currentRoom = 5;
                    }
                }
                case 5:
                {
                    if ( direction.equals("u")||direction.equals("up"))
                    {
                        currentRoom = 4;
                    }
                }
                default:
                {
                    break;
                }
            }
        }
        else
        {
            System.out.println("You cannot move in that direction.");
        }
        
        displayCurrentRoom();
    }

    /** @param direction - the direction the player wants to go.
     * 
     *  @return true if the player's chosen direction is allowed from their current location,
     *          false otherwise.
     */
    public static boolean canMove( String direction )
    {
        switch( currentRoom )
        {
            case 1: // Can only move north from this room
            {
                if(direction.equals("w") || direction.equals("west"))
                    return true;
                    else if ( direction.equals("e")||direction.equals("east"))
                    return true;
                else
                    return false;
            }
            
            case 3:
            {
                if(direction.equals("w") || direction.equals("west"))
                    return true;
                    else if ( direction.equals("e")||direction.equals("east"))
                    return true;
                else
                    return false;
            }
            case 4:
            {
                if(direction.equals("d") || direction.equals("down"))
                    return true;
                    else if ( direction.equals("e")||direction.equals("east"))
                    return true;
                else
                    return false;
            }
            case 5:
            {
                if(direction.equals("u") || direction.equals("up"))
                    return true;
                else
                    return false;
            }
            default:
            {
                return false;
            }
        }
    }

    /** 'Take' command
     * 
     * Takes an item from the ground
     * 
     * @Preconditions: Player must be in the same room as item
     * 
     * @Postconditions: If item is found, item placed in player's pack
     */
    public static void takeItem()
    {
        System.out.print("What item would you like to take? ");
        Scanner kb = new Scanner(System.in);
        String itemToGet = kb.next().toLowerCase();

        switch( itemToGet )
        {
            case "corn":
            case "Corn":
            {
                if( currentRoom == cornLocation && !corn )
                {
                    System.out.println("You grab the cob of corn and put it in your pack.");
                    corn = true;
                }
                else
                {
                    System.out.println("I don't see that here.");
                }
                break;
            }
            
            case "pitchfork":
            case "fork":
            if( currentRoom == pitchforkLocation && !pitchfork )
            {
                System.out.println("You grab the cob of corn and put it inside of your pack.");
                break;
            }
            
            default:
            {
                System.out.println("I don't see that here.");
                break;
            }
        }
    }
    
    /** 'Use' command
     * 
     * Use an item.
     * 
     * @Preconditions: Item must be in player pack.
     */
    public static void useItem()
    {
        System.out.print("What item would you like to use from inventory? ");
        Scanner kb = new Scanner(System.in);
        String itemToUse = kb.next().toLowerCase();

        switch( itemToUse )
        {
            default:
            {
                System.out.println("I don't see how to use that.");
                break;
            }
        }
    }
}

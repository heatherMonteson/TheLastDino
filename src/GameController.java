package src;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

/*
 * GameController: main control center for the game. Handles the game thread, changing levels, and game loop which works
 * to activate all the rendering and updating of items/pieces in the game
 *
 * Citations: https://www.youtube.com/watch?v=1gir2R7G9ws and https://dewitters.com/dewitters-gameloop/
 * used to help with setting up the window, threading and game loop (backend setup)
 */

public class GameController extends Canvas implements Runnable{
    @Serial
    private static final long serialVersionUID = 341232633641429922L;

    //game window dimension
    public static final int width = 840, height= width/12*9; //game window size

    private Thread thread;
    private boolean running= false;

    public static Level level;
    private int levelSwitch;//counter used to track time between levels

    private final GamePieceHandler handler=GamePieceHandler.getHandler();
    private final Player player = Player.getPlayer();

    //Update if player dies, controller will end game
    public static boolean playerDied;

    //counters to track where player is in a level and to display opening game image
    public static int levelLength = 1000;//level timer
    private final int levelDisplaySet = -100;//use for timer to display start of level graphics before beginning level


    public GameController(){
        this.addKeyListener(new KeyInput());
        level=new Level1();
        new GameWindow(width, height,  this);
        levelSwitch=levelDisplaySet;
        playerDied = false;
    }

    /*
     * start:entry point to the thread. with Runnable implemented start calls run() method once
     *
     * @param nothing
     * @return nothing
     */
    public synchronized void start() {

        //starting pop up windows display before thread
        CallsToPopUps.popup(Enums.Popup.Signup);
        CallsToPopUps.popup(Enums.Popup.Instructions);

        level.activate();

        thread= new Thread(this);
        thread.start();
        running=true;
    }

    //stops thread
    public synchronized void stop() {
        try{
            thread.join();
            running=false;
        }
        catch (Exception e){
            System.out.println("Error stopping thread from Game Controller");
            e.printStackTrace();
        }
    }

    /*
     * run: starts the game loop, stops when player dies. Auto called from the start method via threading from Runner
     *
     * @param nothing
     * @return nothing
     *
     * Game Loop:
     * https://www.youtube.com/watch?v=1gir2R7G9ws
     * https://dewitters.com/dewitters-gameloop/
     */
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks= 60.0; //frames per second
        double ns = 1000000000/amountOfTicks;
        double delta =0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta = delta+ (now-lastTime)/ns;
            lastTime=now;
            //1. updates information about character position
            while (delta>=1){
                tick();
                delta--;
            }
            //2. update visuals from position update
            if(running)
                render();
            frames=frames+1;
            if(System.currentTimeMillis()-timer>1000){
                timer= timer+1000;
                System.out.println("FPS"+frames);
                frames=0;
            }
        }
    }

    /*
     * switching: handles all level switching
     *
     * @param nothing
     * @return nothing
     *
     */
    private void switching(){
            if(level.getLevel()== Enums.Level.L1){//go from level 1 to 2
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                level=new Level2();
                level.activate();//level setup
                levelSwitch=levelDisplaySet;
            }
            else if (level.getLevel()== Enums.Level.L2){//go from level 2 to 3
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                level = new Level3();
                level.activate();//level setup
                levelSwitch=levelDisplaySet;
            }
            else if(level.getLevel()== Enums.Level.L3 && !playerDied){ //go from level 3 to end game
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                CallsToPopUps.popup(Enums.Popup.GameOver);
                stop();
            }
            else if(playerDied){ //abrupt end game if player dies
                Broker.getBroker().event(Enums.Event.PlayerDied);
                CallsToPopUps.popup(Enums.Popup.GameOver);
                stop();
            }
    }

    /*
     * tick: tracks the time passed in the level and calls switch when level ends
     * also handles call to the game piece handler to go through all the game pieces and update tick (tick==update position)
     *
     * @param nothing
     * @return nothing
     *
     */
    private void tick(){
        levelSwitch+=1;

        // if(levelSwitch==levelLength){//level over
        //     switching();
        // }
        //updates objects positions
        //levelSwitch>0 as opening level graphics will display for negative value
        if(levelSwitch>0)
            handler.tick();
    }

    /*
     * render: handles calls to the game piece handler player and level
     * to go through all items and update their graphics also handles graphics/buffer
     *
     * @param nothing
     * @return nothing
     *
     */
    private void render(){

        //BufferStrategy: organize complex memory on the window/canvas
        BufferStrategy buffer= this.getBufferStrategy();

        if(buffer==null){
            this.createBufferStrategy(3); //create 3 buffers, just standard
            return;
        }
        Graphics2D graphics = (Graphics2D) buffer.getDrawGraphics();

        //calls for opening level graphics
        if(levelSwitch<=0){
            level.startRender(graphics);
        }
        //updates game piece, level and player stats displays with changes from all internal class tick methods
        else{
            level.render(graphics);
            handler.render(graphics);
            player.render(graphics, levelSwitch);
        }
        graphics.dispose();
        buffer.show();
    }

    //set if player dies, game will then end
    public static void playerDied(){
        playerDied=true;
    }

}
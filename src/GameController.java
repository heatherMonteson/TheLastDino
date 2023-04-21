package src;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;

/*
citation: https://www.youtube.com/watch?v=1gir2R7G9ws
used videos to help with setting up the window, threading and game loop (backend setup)
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

    //handles all game pieces that move
    private final GamePieceHandler handler=GamePieceHandler.getHandler();

    //tracks player score, lives, level and time. Using to render from render method
    private final Player player = Player.getPlayer();

    //Accessed by the Player class if player dies
    public static boolean playerDied;

    public static int levelLength = 500;//level timer
    private int levelDisplaySet = -100;//use for timer to display start of level graphics before beginning


    public GameController(){
        this.addKeyListener(new KeyInput());
        level=new Level1();
        new GameWindow(width, height,  this);
        levelSwitch=levelDisplaySet;
        playerDied = false;
    }

    //entry point from window to the thread
    public synchronized void start() {

        popup(Enums.Popup.Signup);
        popup(Enums.Popup.Instructions);

        //activates level 1 game piece creation
        level.activate();

        thread= new Thread(this);
        //with Runnable calls run() method once
        thread.start();
        running=true;
    }

    //stops game thread
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

    //implementing from Runnable
    //Game loop citation:
    //https://www.youtube.com/watch?v=1gir2R7G9ws
    //https://dewitters.com/dewitters-gameloop/
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
            //1. update information about character position
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

    private void tick(){
        levelSwitch+=1;

        if(levelSwitch==levelLength){
            if(level.getLevel()== Enums.Level.L1){//go from level 1 to 2
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                level=new Level2();
                level.activate();
                levelSwitch=levelDisplaySet;
            }
            else if (level.getLevel()== Enums.Level.L2){//go from level 2 to 3
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                level = new Level3();
                level.activate();
                levelSwitch=levelDisplaySet;
            }
            else if(level.getLevel()== Enums.Level.L3 && !playerDied){ //go from level 3 to end game
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                popup(Enums.Popup.GameOver);
                stop();
            }
            else if(playerDied){ //abrupt end game if player dies
                Broker.getBroker().event(Enums.Event.PlayerDied);
                popup(Enums.Popup.GameOver);
                stop();
            }
        }
        //updates objects positions
        //triggers when not in the opening level window
        if(levelSwitch>0)
            handler.tick();
    }

    private void render(){

        //BufferStrategy: organize complex memory on the window/canvas
        BufferStrategy buffer= this.getBufferStrategy();

        if(buffer==null){
            this.createBufferStrategy(3); //create 3 buffers
            return;
        }
        Graphics2D graphics = (Graphics2D) buffer.getDrawGraphics();

        if(levelSwitch<=0){ //openening level graphics
            level.startRender(graphics);
        }
        //updates game piece, level and player stats displays with changes from all internal class tick methods
        //triggers when not in the opening level window
        else{
            level.render(graphics);
            handler.render(graphics);
            player.render(graphics, levelSwitch);
        }

        graphics.dispose();
        buffer.show();
    }

    public static void playerDied(){
        playerDied=true;
    }

    //trigger all game popup windows
    private void popup(Enums.Popup type) {
        PopUp pop=null;
        if(type==Enums.Popup.Signup)
            pop =new PlayerSignUp();
        else if(type==Enums.Popup.Instructions)
            pop=new Instructions();
        else if(type==Enums.Popup.GameOver)
            pop=new EndOfGame();
        try{
            assert pop != null;
            pop.pop();
        }catch (Exception e){
            System.out.println("popup error check type " + type);
        }
    }

}
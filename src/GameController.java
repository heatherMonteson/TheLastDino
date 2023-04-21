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

    public static final int width = 840, height= width/12*9; //game window size

    private Thread thread;
    private boolean running= false;

    public static Level level;
    private int levelSwitch;//counter used to track time between levels

    private final GamePieceHandler handler=GamePieceHandler.getHandler();
    private final Player player = Player.getPlayer();
    public static boolean playerDied;//Accessed by the Player class if player dies
    public static int levelLength = 500;


    public GameController(){
        this.addKeyListener(new KeyInput());
        level=new Level1();
        new GameWindow(width, height,  this);
        levelSwitch=0;
        playerDied = false;
    }

    //entry point from window to start the thread
    public synchronized void start() {

        //starting popup windows
        popup(Enums.Popup.Signup);
        popup(Enums.Popup.Instructions);

        //activates level 1 graphics and game pieces
        level.activate();

        thread= new Thread(this);
        //with Runnable call run() method once the thread is started
        thread.start();
        running=true;
    }

    //stop game thread
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
    //Game loop:
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
        //TODO: change to desired value when ready to run, just set at a -1 so that we can work on other stuff and not switch levels
        // Post testing use if(levelSwitch%6000==0 ) for ~2 minute levels
        if(levelSwitch==levelLength){
            levelSwitch=0;
            if(level.getLevel()== Enums.Level.L1){
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                level=new Level2();
                level.activate();
            }
            else if (level.getLevel()== Enums.Level.L2){
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                level = new Level3();
                level.activate();
            }
            else if(level.getLevel()== Enums.Level.L3 && !playerDied){
                Broker.getBroker().event(Enums.Event.LevelCompleted);
                popup(Enums.Popup.GameOver);
                stop();
            }
            else if(playerDied){
                popup(Enums.Popup.GameOver);
                stop();
            }
        }
        //updates objects positions
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

        level.render(graphics);
        handler.render(graphics);
        player.render(graphics, levelSwitch);

        graphics.dispose();
        buffer.show();
    }

    public static void playerDied(){
        playerDied=true;
    }

    //TODO: turn on popups, turned off for testing
    private void popup(Enums.Popup type) {
//        PopUp pop=null;
//        if(type==Enums.Popup.Signup)
//            pop =new PlayerSignUp();
//        else if(type==Enums.Popup.Instructions)
//            pop=new Instructions();
//        else if(type==Enums.Popup.GameOver)
//            pop=new EndOfGame();
//        try{
//            assert pop != null;
//            pop.pop();
//        }catch (Exception e){
//            System.out.println("popup error check type " + type);
//        }
    }

}
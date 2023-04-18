package src;

import javax.swing.*;
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
    //Measurements for game window
    public static final int width = 840, height= width/12*9;
    public static GameWindow window;
    private Thread thread;
    private boolean running= false;
    public static boolean playerDied=false;
    public static Level level;

    //frames/sec
    public static int frames;

    private final GamePieceHandler handler;

    public GameController(){
        handler=GamePieceHandler.getHandler();
        this.addKeyListener(new KeyInput());
        level=new Level1();
        //create new window for the game to run in
        window =new GameWindow(width, height,  this);

    }

    //entry point from window to start the thread
    public synchronized void start() {

        if(level.getLevel()==Enums.Level.L1)//start of game need to have the instructions and player register run
            startPopup();

        //will create all the game pieces
        level.activate();
        Dino dino = Dino.getDino();
        dino.resetDinoPosition();
        thread= new Thread(this);
        //with Runnable call run() method once the thread is started
        thread.start();
        running=true;
    }

    private void startPopup() {
//        PopUp signUp=new PlayerSignUp();
//        signUp.pop();
//
//        PopUp instructions = new Instructions();
//        instructions.pop();

    }

    private void endPopUp(){}

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

        //switch levels
        if(level.getLevel()== Enums.Level.L1){
            Broker.getBroker().event(Enums.Event.LevelCompleted);
            level=new Level2();
            start();
        }
        else if (level.getLevel()== Enums.Level.L2){
            level = new Level3();
            start();
        }
        else if(level.getLevel()== Enums.Level.L3)
            endPopUp();
        else if(playerDied)
            endPopUp();
    }

    public static void playerDied(){
        playerDied=true;

        //TODO: Handel stopping game if player dies
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
        frames = 0;
        //continue game loop while thread is still running
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
        stop();
    }

    private void tick(){
        //updates objects
        handler.tick();
    }

    private void render(){
        //BufferStrategy: organize complex memory on the window/canvas
        //TODO: change to render the different levels
        BufferStrategy buffer= this.getBufferStrategy();

        if(buffer==null){
            this.createBufferStrategy(3); //create 3 buffers
            return;
        }
        Graphics graphics = buffer.getDrawGraphics();

        level.render(graphics);
        handler.render(graphics);

        graphics.dispose();
        buffer.show();
    }

}
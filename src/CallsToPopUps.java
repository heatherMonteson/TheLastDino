package src;

/*
 * CallsToPopUps: Handles the creation and popping up of the popup windows
 * sudo factory =]

 */
public class CallsToPopUps {

    /*
     * popup: creates and pops all popup windows for the game based on the selected popup type
     * Types of popups: {Signup, Instructions, GameOver}
     *
     * @param Enums.Popup
     * @return nothing
     */
    public static void popup(Enums.Popup type) {
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

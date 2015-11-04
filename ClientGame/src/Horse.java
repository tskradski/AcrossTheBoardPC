/**
 * Created by thomas on 11/2/15.
 */
public class Horse {

    private int horseNumber;
    private String name;
    private String description;
    private int price;
    private Globals.HORSE_TYPE type;
    private Globals.HORSE_STATUS status;

    public Horse(int horseNumber){
        this.horseNumber = horseNumber;
        description = "Desc holder";
        status = Globals.HORSE_STATUS.AVAILABLE;
        switch (horseNumber){
            case 2:
                name = "Ariel";
                price = 50;
                type = Globals.HORSE_TYPE.LONGSHOT;
                break;
            case 3:
                name = "Short Fellow";
                price = 50;
                type = Globals.HORSE_TYPE.LONGSHOT;
                break;
            case 4:
                name = "Effervescence";
                price = 100;
                type = Globals.HORSE_TYPE.FIELDHORSE;
                break;
            case 5:
                name = "Bills Irish";
                price = 100;
                type = Globals.HORSE_TYPE.FIELDHORSE;
                break;
            case 6:
                name = "Vaudeville";
                price = 200;
                type = Globals.HORSE_TYPE.FAVORITE;
                break;
            case 7:
                name = "Be Careful";
                price = 200;
                type = Globals.HORSE_TYPE.FAVORITE;
                break;
            case 8:
                name = "Vamoose";
                price = 200;
                type = Globals.HORSE_TYPE.FAVORITE;
                break;
            case 9:
                name = "Ben Kid Boy Hay";
                price = 100;
                type = Globals.HORSE_TYPE.FIELDHORSE;
                break;
            case 10:
                name = "Boulder Joe";
                price = 100;
                type = Globals.HORSE_TYPE.FIELDHORSE;
                break;
            case 11:
                name = "Reissue";
                price = 50;
                type = Globals.HORSE_TYPE.LONGSHOT;
                break;
            case 12:
                name = "Donny Brook";
                price = 50;
                type = Globals.HORSE_TYPE.LONGSHOT;
                break;

        }
    }

}

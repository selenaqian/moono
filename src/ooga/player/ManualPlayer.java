package ooga.player;

import java.util.List;
import java.util.Scanner;


public class ManualPLayer extends Player {

    private final int ManID=1;

    @Override
    String manualplayerName() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }



    @Override
    List AIplayerName(int numaiplayers) {
        return null;
    }

}

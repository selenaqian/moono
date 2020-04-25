package ooga.player;

import java.util.List;
import java.util.Scanner;

public class ManualPlayer extends Player {
    final int Id =1;

    @Override
    String manualplayerName() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }

}

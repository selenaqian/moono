package ooga.player;

import java.util.Scanner;

public class ManualPlayer extends Player {

    @Override
    String playerName() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }

}

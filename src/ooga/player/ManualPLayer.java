package ooga.player;

import java.util.Scanner;

public class ManualPLayer extends Player {

    @Override
    String playerName() {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        return name;
    }

}

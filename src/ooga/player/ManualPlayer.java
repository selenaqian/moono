package ooga.player;

import ooga.cards.Card;

import java.util.List;
import java.util.Scanner;

public class ManualPlayer implements Player {

    @Override
    public Card cardBeingPlayed(List<Card> hand, Card card) {
        return playcard(hand,card);
    }


    @Override
    public String playerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String manualplayername = scanner.next();

        return manualplayername;
    }

    @Override
    public Card playcard(List<Card> hand, Card card) {
        Card chosen;
        return chosen;
    }


}

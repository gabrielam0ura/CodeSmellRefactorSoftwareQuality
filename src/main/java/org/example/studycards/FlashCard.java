package org.example.studycards;

import java.util.List;
import java.util.Random;

public class FlashCard extends StudyMethod{
    public FlashCard(String methodName) {
        super(methodName);
    }

    @Override
    public String getMethodName() {
        return "";
    }

    @Override
    void setMethodName(String methodName) {

    }

    public int randomFlashCard(){
        List<Card> cards = cardManager.getCards();
        if (cards.isEmpty()) {
            System.out.println("No cards found");
            return -1;
        }

        Random random = new Random();

        return random.nextInt(cards.size() - 1 + 1) + 1;
    }
}

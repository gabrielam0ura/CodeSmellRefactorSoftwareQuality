package org.example.studycards;

public abstract class StudyMethod {
    String methodName = "";
    CardManager cardManager = null;
    public StudyMethod(String methodName) {
        this.methodName = methodName;
        this.cardManager = CardManager.getCardManager();
    }
    public abstract String getMethodName();
    abstract void setMethodName(String methodName);
}

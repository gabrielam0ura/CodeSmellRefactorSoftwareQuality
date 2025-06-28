package org.example.studymaterial;

public class TextReference extends Reference{
    private int wordCount;
    private String format;

    public TextReference(String title, String language, int wordCount, String format, String accessRights) {
        super(title, null, null, language, false); // aqui passa os parâmetros obrigatórios para Reference
        this.wordCount = wordCount;
        this.format = format;
        this.setAccessRights(accessRights);
    }

    public void editAccess(String accessRights, String format, int wordCount) {
        this.setAccessRights(accessRights);
        this.format = format;
        this.wordCount = wordCount;
    }

    public boolean handleTextAccess(){
        if(getAccessRights() != "Public"){
            return false;
        } else if (this.format != "pdf"){
            return false;
        } else if (this.wordCount == 0){
            return false;
        }
        return true;
    }

}

package common;


public enum Hobby {

    SPORTS("Sports"),
    READING("Reading"),
    MUSIC("Music");

    private final String text;

    Hobby(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

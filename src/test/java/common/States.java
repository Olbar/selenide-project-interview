package common;


public enum States {

    NCR(1, "NCR"),
    UTTAR(2, "Uttar Pradesh"),
    HARYANA(3, "Haryana"),
    RAJASTHAN(4, "Rajasthan");

    private String name;
    private int id;

    States(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

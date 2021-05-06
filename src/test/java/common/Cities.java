package common;


import static common.States.NCR;

public enum Cities {

    DELHI(NCR.getId(),"Delhi"),
    GURGAON(NCR.getId(),"Gurgaon"),
    NOIDA(NCR.getId(),"Noida");

    private String name;
    private int stateId;

    Cities(int stateId, String name) {
        this.name = name;
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public int getStateId() {
        return stateId;
    }

}

package sg.edu.rp.c346.id21025446.datagov;

public class Carpark {

    private String carparkNo;
    private int totalLots;
    private char lotType;
    private int lotsAvail;

    public Carpark(String carparkNo, int totalLots, char lotType, int lotsAvail) {
        this.carparkNo = carparkNo;
        this.totalLots = totalLots;
        this.lotType = lotType;
        this.lotsAvail = lotsAvail;
    }

    public String getCarparkNo() {
        return carparkNo;
    }

    public void setCarparkNo(String carparkNo) {
        this.carparkNo = carparkNo;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public char getLotType() {
        return lotType;
    }

    public void setLotType(char lotType) {
        this.lotType = lotType;
    }

    public int getLotsAvail() {
        return lotsAvail;
    }

    public void setLotsAvail(int lotsAvail) {
        this.lotsAvail = lotsAvail;
    }

    @Override
    public String toString(){
        return "CARPARK INFO" + "\nCarpark number: " + getCarparkNo() + "\nTotal lots: " + getTotalLots() + "\nLot type: " + getLotType() + "\nLots available: " + getLotsAvail();
    }
}

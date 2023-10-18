public class EquipementOffensif {
    private String offensiveType;
    private int value;
    private String name;

    public EquipementOffensif(String pType){
        if (pType.equalsIgnoreCase("guerrier")){
            this.offensiveType = "sword";
            this.value = 4;
            this.name = "Epee";
        }
        else {
            this.offensiveType = "spell";
            this.value = 4;
            this.name = "Popo magique";
        }
    }

    @Override
    public String toString() {
        return "-----" +
                "\nNom : " + name +
                "\nType : " + offensiveType +
                "\nStat : " + value;
    }
}
public class EquipementDefensif {
    private String defensiveType;
    private int value;
    private String name;
    public EquipementDefensif(String pType){
        if (pType.equalsIgnoreCase("guerrier")){
            this.defensiveType = "shield";
            this.value = 4;
            this.name = "Bouclier arverne";
        }
        else {
            this.defensiveType = "philtre";
            this.value = 4;
            this.name = "Miroir miroir";
        }
    }
    @Override
    public String toString() {
        return "-----" +
                "\nNom : " + name +
                "\nType : " + defensiveType +
                "\nValeur : " + value;
    }
}

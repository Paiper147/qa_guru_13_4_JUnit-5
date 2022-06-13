package guru.qa;

public enum Sex {
    MALE("Мужчина"), FEMALE("Женщина");

    public final String descript;

    Sex(String descript){
        this.descript = descript;
    }

}

import java.util.List;

public class Monster {
    private String name;
    private List<String> spells;
    private int level;
    private String description;

    public Monster(String name, List<String> spells, int level, String description) {
        this.name = name;
        this.spells = spells;
        this.level = level;
        this.description = description;
    }

    public Monster(String name, List<String> spells) {
        this.name = name;
        this.spells = spells;
    }

    public Monster(String name, List<String> spells, int level) {
        this.name = name;
        this.spells = spells;
        this.level = level;
    }

    public Monster(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", spells=" + spells +
                ", level=" + level +
                ", description='" + description + '\'' +
                '}';
    }

    public Monster() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSpells() {
        return spells;
    }

    public void setSpells(List<String> spells) {
        this.spells = spells;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

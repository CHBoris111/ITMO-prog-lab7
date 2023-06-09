package src.BaseObjects;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class SpaceMarine implements Comparable<SpaceMarine>, Serializable
{
    private Long id;
    private String name;
    private Coordinates coordinates;
    private final ZonedDateTime creationDate;
    private float health;
    private AstartesCategory category;
    private Weapon weaponType;
    private MeleeWeapon meleeWeapon;
    private Chapter chapter;
    private String createdBy;

    public SpaceMarine(String name, Coordinates coordinates,
                       float health, AstartesCategory category,
                       Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarine(Long id, String name, Coordinates coordinates,
                       float health, AstartesCategory category,
                       Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarine(Long id, String name, Coordinates coordinates,
                       ZonedDateTime creationDate, float health, AstartesCategory category,
                       Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter, String createdBy) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}

    public String getCreatedBy() {return this.createdBy;}

    @Override
    public int compareTo(SpaceMarine o) {
        if (this.id - o.id > 0) {
            return 1;
        }
        else if (this.id - o.id < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public boolean validateID() {
        if (this.getId() != null) {
            if (0 <= this.getId() || this.getId() < Long.MAX_VALUE) {
                return true;
            }
            else {
                System.out.println("The ID must be positive, within the Long format");
                return false;
            }
        }
        else {
            System.out.println("The ID must be a non-zero number of type Long.");
            return false;
        }
    }

    public boolean validateName() {
        if (this.getName() != null) {
            if (!Objects.equals(this.getName(), "")) {
                return true;
            }
            else {
                System.out.println("The name should not be empty");
                return false;
            }
        }
        else {
            System.out.println("The name must not be null");
            return false;
        }
    }

    public boolean validateCoordinates() {
        if (this.getCoordinates() != null) {
            if (this.getCoordinates().getX() != null) {
                return true;
            }
            else {
                System.out.println("The X coordinate cannot be null");
                return false;
            }
        }
        else {
            System.out.println("Coordinates cannot be null");
            return false;
        }
    }

    public boolean validateCreationDate() {
        if (this.getCreationDate() != null) {
            return true;
        }
        else {
            System.out.println("The creation date cannot be null");
            return false;
        }
    }

    public boolean validateHealth() {
        if (this.getHealth() > 0) {
            return true;
        }
        else {
            System.out.println("The health indicator must be greater than 0");
            return false;
        }
    }

    public boolean validateCategory() {
        if (this.getCategory() != null) {
            return true;
        }
        else {
            System.out.println("The category cannot be null");
            return false;
        }
    }

    public boolean validateMeleeWeapon() {
        if (this.getMeleeWeapon() != null) {
            return true;
        }
        else {
            System.out.println("Melee weapons cannot be null");
            return false;
        }
    }
}


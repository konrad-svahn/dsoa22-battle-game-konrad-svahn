public class Weapon {
    
    private String name;
    private int damage;
    private int attackVariance;
    private boolean equiped;
    private Attacks attack1;
    private Attacks attack2;
    
    public Weapon(String name, int damage, int attackVariance, Attacks attack1, Attacks attack2) {
        this.name = name;
        this.damage = damage;
        setAttackVariance(attackVariance);
        this.attack1 = attack1;
        this.attack2 = attack2;
    }

    public boolean isEquiped() {
        return equiped;
    }

    public void setEquiped(boolean equiped) {
        this.equiped = equiped;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Attacks getAttack1() {
        return attack1;
    }

    public void setAttack1(Attacks attack1) {
        this.attack1 = attack1;
    }

    public Attacks getAttack2() {
        return attack2;
    }

    public void setAttack2(Attacks attack2) {
        this.attack2 = attack2;
    }

    public int getAttackVariance() {
        return attackVariance;
    }

    public void setAttackVariance(int attackVariance) {
        if (attackVariance >= 0){
            this.attackVariance = attackVariance;
        } else {
            this.attackVariance = 0;
        }  
    }
}

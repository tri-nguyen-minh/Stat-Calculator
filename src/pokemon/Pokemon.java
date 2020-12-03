
package pokemon;


public class Pokemon {
    String Name,form,status;
    int atk,def,satk,sdef,spd,hp;
    public Pokemon(String Name,String form,int hp,int atk,int def,int satk,int sdef,int spd) {
        this.Name=Name;
        this.form=form;
        this.hp=hp;
        this.atk=atk;
        this.def=def;
        this.satk=satk;
        this.sdef=sdef;
        this.spd=spd;
    }
    public Pokemon(String Name,int hp,int atk,int def,int satk,int sdef,int spd, String status) {
        this.Name=Name;
        this.status=status;
        this.hp=hp;
        this.atk=atk;
        this.def=def;
        this.satk=satk;
        this.sdef=sdef;
        this.spd=spd;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getName() {
        return Name;
    }
    public String getForm() {
        return form;
    }
    public int getHp() {
        return hp;
    }
    public int getAtk() {
        return atk;
    }
    public int getDef() {
        return def;
    }
    public int getSatk() {
        return satk;
    }
    public int getSdef() {
        return sdef;
    }
    public int getSpd() {
        return spd;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setAtk(int atk) {
        this.atk = atk;
    }
    public void setDef(int def) {
        this.def = def;
    }
    public void setSatk(int satk) {
        this.satk = satk;
    }
    public void setSdef(int sdef) {
        this.sdef = sdef;
    }
    public void setSpd(int spd) {
        this.spd = spd;
    }
    @Override
    public String toString() {
        return Name+" ["+form+"] ,"+hp+","+atk+","+def+","+satk+","+sdef+","+spd;
    }
}

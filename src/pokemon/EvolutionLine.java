
package pokemon;


public class EvolutionLine {
int stage;
String name, prior;
    public EvolutionLine(String name, int stage, String prior) {
        this.stage = stage;
        this.name = name;
        this.prior = prior;
    }
    public int getStage() {
        return stage;
    }
    public String getName() {
        return name;
    }
    public String getPrior() {
        return prior;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrior(String prior) {
        this.prior = prior;
    }
@Override
    public String toString() {
    return name+","+stage+","+prior;
}
}

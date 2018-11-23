package lesson1;

public class Team {
    String name;
    Competitor[] competitors;

    public Team(String name, Competitor[] competitors) {
        this.name = name;
        this.competitors = competitors;

    }

    public void showResults() {
        for (Competitor c : competitors) {
            c.showResult();
        }
    }


}

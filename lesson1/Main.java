package lesson1;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Course c = new Course(new Obstacle[]{new Cross(400), new Wall(2), new Water(1)});
        Team team = new Team("team1", new Competitor[]{new Human("Боб"), new Cat("Барсик"),
                new Dog("Бобик")});
        c.doIt(team);
        team.showResults();

    }
}

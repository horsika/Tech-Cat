package cat;

public class Cat {
    private int id;
    private String breed;
    private String color;

    public Cat() {
    }

    public Cat(int id, String breed, String color) {
        this.id = id;
        this.breed = breed;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

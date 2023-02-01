package pl.javastart.task;

enum Option {
    EXIT(0, "Koniec programu"),
    ADD(1, "Dodaj nowy pojazd do kolejki"),
    TAKE(2, "Wykonaj przegląd kolejnemu pojazdowi z kolejki");

    private int id;
    private String description;

    Option(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }

    static Option createFromInt(int option) {
        return values()[option];
    }
}
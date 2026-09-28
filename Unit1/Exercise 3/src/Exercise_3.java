public class Exercise_3 {

    class Author {
        private String name;
        private String nationality;

        public Author(String name, String nationality){
            this.name = name;
            this.nationality = nationality;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }
    }

    class Piece{
        private String title;
        private Author author;
        private Museum museum;
        private Room room;

        public Piece(String title, Author author, Museum museum, Room room){
            this.title = title;
            this.author = author;
            this.museum = museum;
            this.room = room;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Museum getMuseum() {
            return museum;
        }

        public void setMuseum(Museum museum) {
            this.museum = museum;
        }

        public Room getRoom() {
            return room;
        }

        public void setRoom(Room room) {
            this.room = room;
        }
    }

    class Paint extends Piece{
        enum PaintType{
            Oil,
            Pastel,
            Watercolor
        }
        private PaintType type;
        private String format;

        public Paint(String title, Author author, PaintType type, String format, Museum museum, Room room) {
            super(title, author, museum, room);
            this.type = type;
            this.format = format;
        }

        public PaintType getType() {
            return type;
        }

        public void setType(PaintType type) {
            this.type = type;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }
    }

    class Sculpture extends Piece{
        enum Materials{
            Bronze,
            Iron,
            Marble
        }
        enum Styles{
            Neoclassical,
            GrecoRoman,
            Cubist
        }
        private Materials materials;
        private Styles styles;

        public Sculpture(String title, Author author, Materials materials, Styles styles, Museum museum, Room room){
            super(title, author, museum, room);
            this.materials = materials;
            this.styles = styles;
        }

        public Materials getMaterials() {
            return materials;
        }

        public void setMaterials(Materials materials) {
            this.materials = materials;
        }

        public Styles getStyles() {
            return styles;
        }

        public void setStyles(Styles styles) {
            this.styles = styles;
        }
    }

    class Room{
        private String name;

        public Room (String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Museum{
        private String name;
        private String address;
        private String city;
        private String country;

        public Museum(String name, String address, String city, String country){
            this.name = name;
            this.address = address;
            this.city = city;
            this.country = country;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}

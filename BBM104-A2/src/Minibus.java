class Minibus extends Bus {
    public Minibus(String tür, int Id, String boardingPlace, String landingPlace, int row, double price) {
        this.Type = "Minibus";
        this.Id = Id;
        this.boardingPlace = boardingPlace;
        this.landingPlace = landingPlace;
        this.row = row;
        this.price = price;
        this.seatsOfNumber = 2 * row;
        this.Seatsofanybus = new String[seatsOfNumber];
        this.Revenue = 0;
        for (int i = 0; i < Seatsofanybus.length; i++) {
            Seatsofanybus[i] = "*";
        }
    }

}
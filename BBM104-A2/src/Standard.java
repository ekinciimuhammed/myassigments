public class Standard extends Bus {
    public Standard(String tür, int Id, String boardingPlace, String landingPlace, int row, double price,
            double refundcut) {
        this.Type = "Standard";
        this.Id = Id;
        this.boardingPlace = boardingPlace;
        this.landingPlace = landingPlace;
        this.row = row;
        this.price = price;
        this.refundcut = refundcut;
        this.seatsOfNumber = 4 * row;
        this.Seatsofanybus = new String[seatsOfNumber];
        this.Revenue = 0;
        this.deductionAmount = (this.price * this.refundcut) / 100;
        for (int i = 0; i < Seatsofanybus.length; i++) {
            Seatsofanybus[i] = "*";
        }
    }
}
public class Premium extends Bus {
    public Premium(String tür, int Id, String boardingPlace, String landingPlace, int row, double price,
            double refundcut, double premiumFee) {
        this.Type = "Premium";
        this.Id = Id;
        this.boardingPlace = boardingPlace;
        this.landingPlace = landingPlace;
        this.row = row;
        this.price = price;
        this.refundcut = refundcut;
        this.premiumFee = premiumFee;
        this.premiumPrice = price + (price * (this.premiumFee) / 100);
        this.Revenue = 0;
        this.seatsOfNumber = 3 * row;
        this.Seatsofanybus = new String[seatsOfNumber];
        this.deductionAmount = (this.price * this.refundcut) / 100;
        this.premiumdeductionAmount = this.premiumPrice * this.refundcut / 100;
        for (int i = 0; i < Seatsofanybus.length; i++) {
            Seatsofanybus[i] = "*";
        }
    }

}
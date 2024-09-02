package by.my.onlineShop.entity;

public class BankCard implements Identifiable {
    private long id;
    private long cardNumber; // номер карты
    private int expirationMonth; // месяц истечения срока действия
    private int expirationYear; // год истечения срока действия
    private String cardholderName;// имя владельца карты
    private double balance; // баланс карты
    private int cvv; // CVV карты

    public BankCard() { }

    public BankCard(long id, long userInformationId, long cardNumber, int expirationMonth, int expirationYear, String cardholderName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cardholderName = cardholderName;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setcardholderName(String name) {
        this.cardholderName = name;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        BankCard bankCard = (BankCard) o;
        return id == bankCard.id &&
                cardNumber == bankCard.cardNumber &&
                expirationMonth == bankCard.expirationMonth &&
                expirationYear == bankCard.expirationYear &&
                cardholderName.equals(bankCard.cardholderName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) id;
        result = prime * result + (int) cardNumber;
        result = prime * result + expirationMonth;
        result = prime * result + expirationYear;
        result = prime * result + ((cardholderName == null) ? 0 : cardholderName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("BankCard{");
        result.append("id=").append(id);
        result.append(", cardNumber=").append(cardNumber);
        result.append(", expirationMonth=").append(expirationMonth);
        result.append(", expirationYear=").append(expirationYear);
        result.append(", cardOwner='").append(cardholderName).append('\'');
        result.append('}');
        return result.toString();
    }
}

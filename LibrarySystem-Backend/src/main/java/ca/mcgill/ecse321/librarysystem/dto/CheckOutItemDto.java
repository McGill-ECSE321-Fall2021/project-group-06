package ca.mcgill.ecse321.librarysystem.dto;

public class CheckOutItemDto extends MediaDto {

    private boolean isCheckedOut;
    private boolean isReserved;
    private int borrowingPeriod;
    
	public CheckOutItemDto() {
	}
    
	public CheckOutItemDto(boolean isCheckedOut, boolean isReserved, int borrowingPeriod) {
        this.isCheckedOut = isCheckedOut;
        this.isReserved = isReserved;
        this.borrowingPeriod = borrowingPeriod;
	}

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public int getBorrowingPeriod() {
        return borrowingPeriod;
    }
    
}
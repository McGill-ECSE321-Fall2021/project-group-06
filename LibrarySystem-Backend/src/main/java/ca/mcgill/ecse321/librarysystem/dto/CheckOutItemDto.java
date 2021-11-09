package ca.mcgill.ecse321.librarysystem.dto;

public class CheckOutItemDto extends MediaDto {

    private boolean isCheckedOut;
    private boolean isReserved;
    private int borrowingPeriod;
    private Date startDate;
    
	public CheckOutItemDto() {
	}
    public CheckOutItemDto(int mediaID){
        super(mediaID);
        this.borrowingPeriod = 3;
        this.isCheckedOut = false;
        this.isReserved = false;

    }
	public CheckOutItemDto(int mediaID, boolean isCheckedOut, boolean isReserved, int borrowingPeriod, Date startDate) {
        super(Item.Book, mediaID);
        this.isCheckedOut = isCheckedOut;
        this.isReserved = isReserved;
        this.borrowingPeriod = borrowingPeriod;
        this.startDate = startDate;
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

    public Date getStartDate(){
        return this.startDate;
    }
    
}
package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

public class CheckOutItemDto extends MediaDto {

    private boolean isCheckedOut;
    private boolean isReserved;
    private int borrowingPeriod;
    
	public CheckOutItemDto() {
	}
    public CheckOutItemDto(int mediaID){
        super(mediaID);
        this.borrowingPeriod = 3;
        this.isCheckedOut = false;
        this.isReserved = false;

    }
	public CheckOutItemDto(int mediaID, boolean isCheckedOut, boolean isReserved, int borrowingPeriod) {
        super(Item.Book, mediaID);
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
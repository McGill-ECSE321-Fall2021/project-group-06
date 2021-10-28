package ca.mcgill.ecse321.librarysystem.dto;

import java.sql.Date;
import java.sql.Time;

public class CheckOutItemDto extends MediaDto {

    private boolean isCheckedOut;
    private boolean isReserved;
    private int borrowingPeriod;
    
	public CheckOutItemDto() {
	}

	// public CheckOutItemDto(String name) {
	// 	this(name, Date.valueOf("1971-01-01"), Time.valueOf("00:00:00"), Time.valueOf("23:59:59"));
	// }

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
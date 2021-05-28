package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();
		System.out.print("Check-in (dd/mm/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		System.out.print("Check-Out (dd/mm/yyyy): ");
		Date checkOut = sdf.parse(sc.next());

		//VALID CHECK-OUT AFTER CHECK-IN
		if(!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: check-out date must be after check-in date.");
		}else {
			Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
			System.out.println(reservation.toString());
			
			System.out.println();
			System.out.println("Enter date to update the reservation: ");
			System.out.print("Update check-in (dd/mm/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Update check-Out (dd/mm/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			Date now =  new Date();
			
			if(checkIn.before(now) || checkOut.before(now)) {
				System.out.println("Error: Reservation dates for update must be future dates");
			}else if(!checkOut.after(checkIn)) {
				System.out.println("Error in reservation: check-out date must be after check-in date.");				
			}else {
				reservation.updateDates(checkIn, checkOut);
				System.out.println(reservation.toString());
			}
		}
		
		sc.close();
	}

}

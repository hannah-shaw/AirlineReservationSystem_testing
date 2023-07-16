package Codebase;

import java.util.ArrayList;

public class TicketCollection {
	
	public static ArrayList<Ticket> tickets = new ArrayList<>();

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void addTickets(ArrayList<Ticket> tickets_db) {
		// Check whether add exist ticket
		ArrayList<Integer> ticketTempList = new ArrayList<Integer>();
		for(Ticket tempTicket: tickets_db) {
			if( tempTicket!=null){
				if (ticketTempList.contains(tempTicket.getTicket_id()))
					throw new IllegalArgumentException( "ID:" + tempTicket.getTicket_id() + " ticket was add twice");
				else {
					ticketTempList.add(tempTicket.getTicket_id());
					for (Ticket existingTicket : tickets) {
						if (existingTicket.getTicket_id() == tempTicket.getTicket_id())
							throw new IllegalArgumentException( "ID:" + tempTicket.getTicket_id() + " ticket is already exist");
					}
				}
			}
			else {
				throw new IllegalArgumentException("Invalid null ticket in list");
			}
		}
		TicketCollection.tickets.addAll(tickets_db);
	}
	
	public static ArrayList<Ticket> getAllTickets() {
    	//display all available tickets from the Ticket collection
		for (Ticket ticket : tickets){
			System.out.println(ticket.toString()+"\n");
		}
		return getTickets();
    }
	public static Ticket getTicketInfo(int ticket_id) {
    	//SELECT a ticket where ticket id = ticket_id
		for (Ticket ticket : tickets){
			if((ticket.getTicket_id() == ticket_id) && (ticket.ticketStatus() == false)){
				return ticket;
			}
		}
    	return null;
    }
	

}

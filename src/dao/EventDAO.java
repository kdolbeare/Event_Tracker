package dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import entities.Event;

@Transactional
public class EventDAO
{

	@PersistenceContext
	private EntityManager em;
	
	public Event getEventById (int id) {
		String query = "select e from Event e where e.id = " +id;
		Query q = em.createQuery(query, Event.class);
		Event event = (Event)q.getSingleResult();
		return event;
	}
	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		String query = "select e from Event e";
		Query q = em.createQuery(query, Event.class);
		List<Event> events = (List<Event>)q.getResultList();
		return events;
	}
	public Event getEventByDate (Date d) {		
		
		String query = "select e from Event e where e.eventDate = '" +d + "'";
		Query q = em.createQuery(query, Event.class);
		Event event = (Event)q.getSingleResult();
		return event;
	}
	public void deleteEvent(int id)
	{
		Event e = em.find(Event.class, id);
		em.remove(e);
	}
	public void createEvent (Event event)
	{
		Event newEvent;
		newEvent = event;
		em.persist(newEvent);
	}
}

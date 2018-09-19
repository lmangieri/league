package leandroportfolio.league.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import leandroportfolio.league.model.Player;

public class PlayerDAO {
		SessionFactory sessionFactory = null;
		Session session = null;
		
		public PlayerDAO() {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
			this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		
		public List<Player> getAllPlayers() {
			List<Player> list = new ArrayList<Player>();
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				System.out.println("Before session.createQuery");
				Query query = session.createQuery("select new Player(p.id ,p.name ,p.nick ,p.email) from Player p");
				list = query.list();
				System.out.println("After query.list");
			} catch (Exception e) {
				System.out.println("Exception.... no getAllPlayers");
				e.printStackTrace();
			} finally {
				session.close();
			}
			return list;
		}
		
	
}

package netload.Database;

import netload.model.Day;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

/**
 * Copyright (C) 01/02/17 martijn
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Stats {
    private static SessionFactory factory;

    public Stats() {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public int addDay(Day day) throws Exception {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id = null;

        @SuppressWarnings("deprecation")
        Day lastDay = (Day) session.createQuery("FROM Day ORDER BY datum DESC")
                .setMaxResults(1)
                .uniqueResult();

        if (day.getDatum().after(lastDay.getDatum())) {
            try {
                tx = session.beginTransaction();
                id = (Integer) session.save(day);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            return id;
        }
        throw new Exception("Day already added");
    }
}
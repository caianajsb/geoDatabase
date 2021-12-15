package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import org.locationtech.jts.geom.Point;

@Entity
public class Alert {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private Date date;

        private Point location;

        public Alert() {
        }

        public Long getId() {
            return id;
        }

        private void setId(Long id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Point getLocation() {
            return this.location;
        }

        public void setLocation(Point location) {
            this.location = location;
        }

    @Override
    public String toString() {
        return "Alert{" + "id=" + id + ", title=" + title + ", date=" + date + ", location=" + location + '}';
    }
        
        
}
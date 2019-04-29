package pl.rkulikowski.Restaurant.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "bill_item",
            joinColumns = { @JoinColumn(name = "bill_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") }
    )
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    private Place place;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}

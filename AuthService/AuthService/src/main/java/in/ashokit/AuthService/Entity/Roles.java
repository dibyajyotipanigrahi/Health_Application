package in.ashokit.AuthService.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;


    @ManyToMany(mappedBy = "roles")
    private List<User> user;


    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return roleName;
    }

    public void setName(String name) {
        this.roleName = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "name='" + roleName + '\'' +
                '}';
    }
}

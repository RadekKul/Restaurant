package pl.rkulikowski.Restaurant.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // czy nie musi byc gdzies opisane validate + binding result jesli robimy cos hibernatem?

    @NotBlank(message = "{username.notempty}")
    @Size(min = 5)
    @Size(max = 15)
    //@Min(value = 5)   - to dziala do liczb a nie do dlugosci znakow
    //@Max(value = 15)
    //@UniqueElements(message = "Wpisana nazwa użytkownika jest już zajęta")
    private String username;

    @NotBlank
    //@Min(value = 8, message ="Podane hasło powinno mieć conajmniej 8 znaków")
    //@Max(value = 25, message ="Podane hasło może mieć maksymalnie 25 znaków")
    private String password;
    @Transient
    private String passwordConfirm;
    private String email;
    @Transient
    private String emailConfirm;
    @Column(name = "enabled")
    private boolean enabled;

   /* @OneToMany(mappedBy = "user")
    private Set<Bill> orders = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    private String pesel;
    private String firstName;
    private String lastName;
    private String sex;

    public User() {
        this.enabled=false;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConfirm() {
        return emailConfirm;
    }

    public void setEmailConfirm(String emailConfirm) {
        this.emailConfirm = emailConfirm;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

   /* public Set<Bill> getBills() {
        return orders;
    }

    public void setBills(Set<Bill> orders) {
        this.orders = orders;
    }*/
}

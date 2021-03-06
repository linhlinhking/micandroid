package anni.asecurity.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import anni.core.grid.LongGridBean;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


/**
 * Role generated by Lingo.
 *
 * @author Lingo
 * @since 2007年08月18日 下午 20时18分45秒0
 */
@Entity
@Table(name = "A_SECURITY_ROLE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role extends LongGridBean {
    /** * serial. */
    static final long serialVersionUID = 0L;

    /** * id. */
    private Long id;

    /** * name. */
    private String name;

    /** * descn. */
    private String descn;

    /** * users. */
    private Set<User> users = new HashSet<User>(0);

    /** * resources. */
    private Set<Resource> resources = new HashSet<Resource>(0);

    /** * menus. */
    private Set<Menu> menus = new HashSet<Menu>(0);

    // ----------------------------------------------------
    // transient fields
    // ----------------------------------------------------
    /** * 是否授权. */
    private Boolean authorized;

    /** * 构造方法. */
    public Role() {
    }

    /** * @return id. */
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    /** * @param id id. */
    public void setId(Long id) {
        this.id = id;
    }

    /** * @return name. */
    @Column(name = "NAME", length = 50, unique = true, nullable = false)
    public String getName() {
        return name;
    }

    /** * @param name name. */
    public void setName(String name) {
        this.name = name;
    }

    /** * @return descn. */
    @Column(name = "DESCN", length = 200)
    public String getDescn() {
        return descn;
    }

    /** * @param descn descn. */
    public void setDescn(String descn) {
        this.descn = descn;
    }

    /** * @return users. */
    @ManyToMany(cascade =  {
        CascadeType.PERSIST, CascadeType.MERGE}
    , fetch = FetchType.LAZY)
    @JoinTable(name = "A_SECURITY_ROLE_USER", joinColumns =  {
        @JoinColumn(name = "ROLE_ID")
    }
    , inverseJoinColumns =  {
        @JoinColumn(name = "USER_ID")
    }
    )
    public Set<User> getUsers() {
        return users;
    }

    /** * @param users users. */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /** * @return resources. */
    @ManyToMany(cascade =  {
        CascadeType.PERSIST, CascadeType.MERGE}
    , fetch = FetchType.LAZY)
    @JoinTable(name = "A_SECURITY_RESOURCE_ROLE", joinColumns =  {
        @JoinColumn(name = "ROLE_ID")
    }
    , inverseJoinColumns =  {
        @JoinColumn(name = "RESOURCE_ID")
    }
    )
    public Set<Resource> getResources() {
        return resources;
    }

    /** * @param resources resources. */
    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    /** * @return menus. */
    @ManyToMany(cascade =  {
        CascadeType.PERSIST, CascadeType.MERGE}
    , fetch = FetchType.LAZY)
    @JoinTable(name = "A_SECURITY_MENU_ROLE", joinColumns =  {
        @JoinColumn(name = "ROLE_ID")
    }
    , inverseJoinColumns =  {
        @JoinColumn(name = "MENU_ID")
    }
    )
    public Set<Menu> getMenus() {
        return menus;
    }

    /** * @param menus menus. */
    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    // ----------------------------------------------------
    // transient methods
    // ----------------------------------------------------
    /** * @return is authorized. */
    @Transient
    public Boolean getAuthorized() {
        return authorized;
    }

    /** * @param authorized is authorized. */
    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }
}

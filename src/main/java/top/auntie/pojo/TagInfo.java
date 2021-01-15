package top.auntie.pojo;

import javax.persistence.*;

@Table(name = "tag_info")
public class TagInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;

    /**
     * 心情：dic_info
     */
    private String mode;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取心情：dic_info
     *
     * @return mode - 心情：dic_info
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置心情：dic_info
     *
     * @param mode 心情：dic_info
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
}
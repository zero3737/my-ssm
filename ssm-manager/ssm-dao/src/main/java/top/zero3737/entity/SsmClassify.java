package top.zero3737.entity;

public class SsmClassify {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer isParentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsParentId() {
        return isParentId;
    }

    public void setIsParentId(Integer isParentId) {
        this.isParentId = isParentId;
    }
}